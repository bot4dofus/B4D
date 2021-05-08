package fr.B4D.dofus.server;

import java.io.InputStream;
import java.util.Properties;

import com.google.gson.Gson;

import fr.B4D.bot.B4D;

/**
 * The {@code ServerEnum} class list all the dofus servers.
 * 
 * @author Lucas
 *
 */
public enum ServerEnum {

	//"netstat -oan" to list all the connections under Windows

	/**
	 * Julith server.
	 */

	/**
	 * Julith server.
	 */
	JULITH,

	/**
	 * Pandore server.
	 */
	PANDORE,

	/**
	 * Brumen server.
	 */
	BRUMEN,

	/**
	 * Merkator server.
	 */
	MERKATOR,

	/**
	 * Meriana server.
	 */
	MERIANA,

	/**
	 * Nidas server.
	 */
	NIDAS,

	/**
	 * Furye server.
	 */
	FURYE,

	/**
	 * Agride server.
	 */
	AGRIDE,

	/**
	 * Ush server.
	 */
	USH,

	/**
	 * Echo server.
	 */
	ECHO,

	/**
	 * Shadow server.
	 */
	SHADOW,

	/**
	 * Oto Mustam server.
	 */
	OTOMUSTAM,

	/**
	 * Thanatena server.
	 */
	THANATENA,

	/**
	 * Jahash server.
	 */
	JAHASH,

	/**
	 * Ilyzaelle server.
	 */
	ILYZAELLE,

	/**
	 * Rubilax server.
	 */
	RUBILAX,

	/**
	 * Atcham server.
	 */
	ATCHAM,

	/**
	 * Crocabulia server.
	 */
	CROCABULIA,

	/**
	 * Temporis I server.
	 */
	TEMPORIS1,

	/**
	 * Temporis II server.
	 */
	TEMPORIS2,

	/**
	 * Temporis III server.
	 */
	TEMPORIS3,

	/**
	 * Temporis IV server.
	 */
	TEMPORIS4,

	/**
	 * Temporis V server.
	 */
	TEMPORIS5,

	/**
	 * Temporis VI server.
	 */
	TEMPORIS6,

	/**
	 * Temporis VII server.
	 */
	TEMPORIS7,

	/**
	 * Temporis VIII server.
	 */
	TEMPORIS8,

	/**
	 * Temporis IX server.
	 */
	TEMPORIS9,

	/**
	 * Temporis X server.
	 */
	TEMPORIS10,

	/**
	 * Temporis XI server.
	 */
	TEMPORIS11,

	/**
	 * Temporis XII server.
	 */
	TEMPORIS12,

	/**
	 * Temporis XIII server.
	 */
	TEMPORIS13,

	/**
	 * Temporis XIV server.
	 */
	TEMPORIS14;
	
    private static final String PATH  = "/data/servers.properties";

    private Server value;
    
	/**
	 * Loads the value of the enumeration from a file.
	 */
	private void init() {
        try {
        	InputStream is = ServerEnum.class.getResourceAsStream(PATH);
			Properties properties = new Properties();
			properties.load(is);
			String stringValue = (String) properties.get(this.toString());
			is.close();
			value = new Gson().fromJson(stringValue, Server.class);
			value.setKey(this);
        }
        catch (Exception e) {
            B4D.logger.error(e);
        }
    }

    /**
     * Returns the value of of the enumeration.
     * @return Server of the enumeration.
     */
    public Server getValue() {
        if (value == null) {
            init();
        }
        return value;
    }
}
