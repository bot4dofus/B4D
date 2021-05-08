package fr.B4D.dofus.server;

import java.io.Serializable;

/**
 * The {@code Server} class represents a Dofus server.
 * <br><br>
 * A server is defined by a name and an IP address.
 * 
 * @author Lucas
 *
 */
public class Server implements Serializable{

	private static final long serialVersionUID = -3337491068686582633L;

	/**
	 * Name of the server.
	 */
	private String name;
	
	/**
	 * IP address of the server.
	 */
	private String ip;
	
	/**
	 * Specifies the type of the server.
	 */
	private ServerType type;
	
	/**
	 * Server enumeration key.
	 */
	private ServerEnum key;
	/**
	 * Constructor of the {@code Server} enumeration.
	 * @param name - Name of the server.
	 * @param ip - IP address of the server (format x.x.x.x).
	 * @param type - Type of the server.
	 * @param key - Server enumeration key.
	 */
	public Server(String name, String ip, ServerType type) {
		this.name = name;
		this.ip = ip;
		this.type = type;
	}

	/**
	 * Returns the name of the server.
	 * @return Name of the server.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the IP address of the server.
	 * @return IP address of the server.
	 */
	public String getIp() {
		return ip;
	}

	/** 
	 * Returns the type of the server.
	 * @return Type of the server.
	 */
	public ServerType getType() {
		return type;
	}

	/** 
	 * Sets the enumeration of the server.
	 * @param key Enumeration of the server.
	 */
	public void setKey(ServerEnum key) {
		this.key = key;
	}

	/** 
	 * Returns the enumeration of the server.
	 * @return Enumeration of the server.
	 */
	public ServerEnum getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
