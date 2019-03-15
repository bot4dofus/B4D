package fr.B4D.socket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Pure Java code for reading the Windows' registry, taken from http://stackoverflow.com/a/6163701/616460.
 * This is a hack and may not work forever. Original author is unknown. The only modification I have made
 * is to change the package name.
 */
public class WinRegistry {
    public static final int HKEY_CURRENT_USER = 0x80000001;
    public static final int HKEY_LOCAL_MACHINE = 0x80000002;
    public static final int REG_SUCCESS = 0;
    public static final int REG_NOTFOUND = 2;
    public static final int REG_ACCESSDENIED = 5;

    private static final int KEY_READ = 0x20019;
    private static Preferences userRoot = Preferences.userRoot();
    private static Preferences systemRoot = Preferences.systemRoot();
    private static Class<? extends Preferences> userClass = userRoot.getClass();
    private static Method regOpenKey = null;
    private static Method regCloseKey = null;
    private static Method regQueryValueEx = null;
    private static Method regEnumValue = null;
    private static Method regQueryInfoKey = null;
    private static Method regEnumKeyEx = null;
    private static Method regCreateKeyEx = null;
    private static Method regSetValueEx = null;
    private static Method regDeleteKey = null;
    private static Method regDeleteValue = null;

    static {
        try {
            regOpenKey = userClass.getDeclaredMethod("WindowsRegOpenKey",
                    new Class[] { int.class, byte[].class, int.class });
            regOpenKey.setAccessible(true);
            regCloseKey = userClass.getDeclaredMethod("WindowsRegCloseKey",
                    new Class[] { int.class });
            regCloseKey.setAccessible(true);
            regQueryValueEx = userClass.getDeclaredMethod(
                    "WindowsRegQueryValueEx", new Class[] { int.class,
                            byte[].class });
            regQueryValueEx.setAccessible(true);
            regEnumValue = userClass.getDeclaredMethod("WindowsRegEnumValue",
                    new Class[] { int.class, int.class, int.class });
            regEnumValue.setAccessible(true);
            regQueryInfoKey = userClass.getDeclaredMethod(
                    "WindowsRegQueryInfoKey1", new Class[] { int.class });
            regQueryInfoKey.setAccessible(true);
            regEnumKeyEx = userClass.getDeclaredMethod("WindowsRegEnumKeyEx",
                    new Class[] { int.class, int.class, int.class });
            regEnumKeyEx.setAccessible(true);
            regCreateKeyEx = userClass.getDeclaredMethod(
                    "WindowsRegCreateKeyEx", new Class[] { int.class,
                            byte[].class });
            regCreateKeyEx.setAccessible(true);
            regSetValueEx = userClass.getDeclaredMethod("WindowsRegSetValueEx",
                    new Class[] { int.class, byte[].class, byte[].class });
            regSetValueEx.setAccessible(true);
            regDeleteValue = userClass.getDeclaredMethod(
                    "WindowsRegDeleteValue", new Class[] { int.class,
                            byte[].class });
            regDeleteValue.setAccessible(true);
            regDeleteKey = userClass.getDeclaredMethod("WindowsRegDeleteKey",
                    new Class[] { int.class, byte[].class });
            regDeleteKey.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WinRegistry() {
    }

    /**
     * Read a value from key and value name
     * 
     * @param hkey
     * @param key
     * @param valueName
     * @return the value
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String readString(int hkey, String key, String valueName)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        if (hkey == HKEY_LOCAL_MACHINE) {
            return readString(systemRoot, hkey, key, valueName);
        } else if (hkey == HKEY_CURRENT_USER) {
            return readString(userRoot, hkey, key, valueName);
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    /**
     * Read the value name(s) from a given key
     * 
     * @param hkey
     * @param key
     * @return the value name(s)
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static List<String> readStringSubKeys(int hkey, String key)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        if (hkey == HKEY_LOCAL_MACHINE) {
            return readStringSubKeys(systemRoot, hkey, key);
        } else if (hkey == HKEY_CURRENT_USER) {
            return readStringSubKeys(userRoot, hkey, key);
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    // =====================

    private static String readString(Preferences root, int hkey, String key,
            String value) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
                new Integer(hkey), toCstr(key), new Integer(KEY_READ) });
        if (handles[1] != REG_SUCCESS) {
            return null;
        }
        byte[] valb = (byte[]) regQueryValueEx.invoke(root, new Object[] {
                new Integer(handles[0]), toCstr(value) });
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
        return (valb != null ? new String(valb).trim() : null);
    }

    private static List<String> readStringSubKeys(Preferences root, int hkey,
            String key) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        List<String> results = new ArrayList<String>();
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] {
                new Integer(hkey), toCstr(key), new Integer(KEY_READ) });
        if (handles[1] != REG_SUCCESS) {
            return null;
        }
        int[] info = (int[]) regQueryInfoKey.invoke(root,
                new Object[] { new Integer(handles[0]) });

        int count = info[0]; // Fix: info[2] was being used here with wrong
        // results. Suggested by davenpcj, confirmed by
        // Petrucio
        int maxlen = info[3]; // value length max
        for (int index = 0; index < count; index++) {
            byte[] name = (byte[]) regEnumKeyEx.invoke(root, new Object[] {
                    new Integer(handles[0]), new Integer(index),
                    new Integer(maxlen + 1) });
            results.add(new String(name).trim());
        }
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
        return results;
    }

    // utility
    private static byte[] toCstr(String str) {
        byte[] result = new byte[str.length() + 1];

        for (int i = 0; i < str.length(); i++) {
            result[i] = (byte) str.charAt(i);
        }
        result[str.length()] = 0;
        return result;
    }
}
