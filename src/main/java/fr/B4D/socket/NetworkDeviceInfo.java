package fr.B4D.socket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Gets information about network interface given a jpcap device string, on Windows. Makes
 * use of WinRegistry class from https://stackoverflow.com/a/6163701/616460. This is tested
 * against jpcap 0.01.16, which is available for download at http://sourceforge.net/projects/jpcap/.
 * 
 * All getters return empty strings rather than null if the information is unavailable.
 * 
 * @author https://stackoverflow.com/users/616460/jason-c
 */
public class NetworkDeviceInfo {


    private static final int DRIVER_CLASS_ROOT = WinRegistry.HKEY_LOCAL_MACHINE;
    private static final String DRIVER_CLASS_PATH = "SYSTEM\\CurrentControlSet\\Control\\Class";
    private static final String NETCFG_INSTANCE_KEY = "NetCfgInstanceId";
    private static final int IFACE_ROOT = WinRegistry.HKEY_LOCAL_MACHINE;
    private static final String IFACE_PATH = "SYSTEM\\CurrentControlSet\\services\\Tcpip\\Parameters\\Interfaces";


    private final String jpcapDeviceName;
    private final String jpcapDisplayName;
    private final String guid;
    private final String driverName;
    private final String driverVendor;
    private final String interfaceAddress;
    private final String interfaceSubnetMask;


    /**
     * Construct from a jpcap device string.
     * @param jpcapDeviceString Device string from jpcap. 
     * @throws IllegalArgumentException If the device string could not be parsed.
     * @throws UnsupportedOperationException If the Windows registry could not be read.
     */
    public NetworkDeviceInfo (String jpcapDeviceString) throws IllegalArgumentException, UnsupportedOperationException {

        // extract jpcap device and display name, and guid, from jpcap device string

        String[] jpcapParts = jpcapDeviceString.split("\n", 2);

        jpcapDeviceName = (jpcapParts.length > 0) ? jpcapParts[0].trim() : "";
        jpcapDisplayName = (jpcapParts.length > 1) ? jpcapParts[1].replaceAll("\n", " ").trim() : "";

        Matcher matcher = Pattern.compile("\\{(\\S*)\\}").matcher(jpcapDeviceName);
        guid = matcher.find() ? matcher.group(1) : null;
        if (guid == null)
            throw new IllegalArgumentException("Could not parse GUID from jpcap device name '" + jpcapDeviceName + "'");

        try {

            // search registry for driver details:
            // Search all subkeys of subkeys in HKLM\SYSTEM\CurrentControlSet\Control\Class\. If a subkey
            // is found that contains a key NetCfgInstanceId whose value is {guid}, then the rest of the keys 
            // there will contain driver info - the nice display name, vendor info, etc.

            String theDriverName = "";
            String theDriverVendor = "";

            for (String driverClassSubkey : WinRegistry.readStringSubKeys(DRIVER_CLASS_ROOT, DRIVER_CLASS_PATH)) {
                for (String driverSubkey : WinRegistry.readStringSubKeys(DRIVER_CLASS_ROOT, DRIVER_CLASS_PATH + "\\" + driverClassSubkey)) {
                    String path = DRIVER_CLASS_PATH + "\\" + driverClassSubkey + "\\" + driverSubkey;
                    String netCfgInstanceId = WinRegistry.readString(DRIVER_CLASS_ROOT, path, NETCFG_INSTANCE_KEY);
                    if (netCfgInstanceId != null && netCfgInstanceId.equalsIgnoreCase("{" + guid + "}")) {
                        theDriverName = trimOrDefault(WinRegistry.readString(DRIVER_CLASS_ROOT, path, "DriverDesc"), "");
                        theDriverVendor = trimOrDefault(WinRegistry.readString(DRIVER_CLASS_ROOT, path, "ProviderName"), "");
                        // other interesting keys: DriverVersion, DriverDate
                        break;
                    }
                }
                if (!theDriverName.isEmpty())
                    break;
            }

            driverName = trimOrDefault(theDriverName, jpcapDisplayName);
            driverVendor = trimOrDefault(theDriverVendor, "Unknown");

            // read tcp/ip configuration details (HKLM\SYSTEM\CCS\services\Tcpip\Parameters\Interfaces\{guid})
            // there is an integer key EnableDHCP, but java.util.prefs.WindowsPreferences (and therefore 
            // WinRegistry) supports reading string keys only, therefore we'll have to hack it to decide on
            // DHCP vs. static IP address and hope it's correct.
            // also note the ip addresses are REG_MULTI_SZ, presumably to also hold ipv6 addresses. the results
            // here may not be quite correct, then. that's why I'm leaving addresses as strings instead of 
            // converting them to InetAddresses.

            String ifPath = IFACE_PATH + "\\{" + guid + "}";
            String dhcpIp = trimOrDefault(WinRegistry.readString(IFACE_ROOT, ifPath, "DhcpIPAddress"), "");
            String dhcpMask = trimOrDefault(WinRegistry.readString(IFACE_ROOT, ifPath, "DhcpSubnetMask"), "");
            // if static set, use it, otherwise use dhcp
            interfaceAddress = trimOrDefault(WinRegistry.readString(IFACE_ROOT, ifPath, "IPAddress"), dhcpIp);
            interfaceSubnetMask = trimOrDefault(WinRegistry.readString(IFACE_ROOT, ifPath, "SubnetMask"), dhcpMask);

        } catch (Exception x) {
            throw new UnsupportedOperationException("Information could not be read from the Windows registry.", x);
        }


    }


    /**
     * @param str A string.
     * @param def A default string.
     * @return Returns def if str is null or empty (after trim), otherwise returns str, trimmed.
     */
    private final static String trimOrDefault (String str, String def) {
        str = (str == null) ? "" : str.trim();
        return str.isEmpty() ? def : str;
    }


    /**
     * Gets the jpcap device name, which can be passed to PacketCapture.
     * @return Device name from jpcap. Pass this string to PacketCapture to specify this device.
     */
    public final String getJpcapDeviceName () {
        return jpcapDeviceName;
    }


    /**
     * Gets the jpcap display name. Usually this is pretty bland.
     * @return Display name from jpcap.
     */
    public final String getJpcapDisplayName () {
        return jpcapDisplayName;
    }


    /**
     * Gets the interface GUID.
     * @return Interface GUID.
     */
    public final String getGuid () {
        return guid;
    }


    /**
     * Get a nice display name for the interface driver. Display this in GUIs.
     * @return Interface driver name.
     */
    public final String getDriverName () {
        return driverName;
    }


    /**
     * Get the interface driver vendor name. Could be displayed in GUIs.
     * @return Interface driver vendor name.
     */
    public final String getDriverVendor () {
        return driverVendor;
    }


    /**
     * Get the interface's IP address.
     * @return Interface's IP address.
     * @bug This may not be correct for interfaces with multiple IP addresses. For this reason, it is
     *      left as a raw string rather than being converted to an InetAddress.
     */
    public final String getInterfaceAddress () {
        return interfaceAddress;
    }


    /**
     * Get the interface's subnet mask.
     * @return Interface's subnet mask.
     * @bug Same issue as getInterfaceAddress(). 
     */
    public final String getInterfaceSubnetMask () {
        return interfaceSubnetMask;
    }


    /**
     * Get a display string, for debugging.
     * @return Display string, for debugging.
     */
    @Override public String toString () {
        return String.format("%s (%s) {%s} @ %s/%s", driverName, driverVendor, guid, interfaceAddress, interfaceSubnetMask);
    }


}