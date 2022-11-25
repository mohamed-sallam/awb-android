
import java.util.*;

public class Device {

	//
	// Fields
	//

	private String name;
	private String operatingSystemName;
	private undef operatingSystemType = UNKNOWN;
	private String AWB_VERSION = "0.1.0v";
	private String ipAddressV4 = "127.0.0.1";
	private GUID guid;
	private Group groups_;	
	public Device () { };
	
	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Set the value of name
	 * @param newVar the new value of name
	 */
	public void setName (String newVar) {
		name = newVar;
	}

	/**
	 * Get the value of name
	 * @return the value of name
	 */
	public String getName () {
		return name;
	}

	/**
	 * Set the value of operatingSystemName
	 * @param newVar the new value of operatingSystemName
	 */
	public void setOperatingSystemName (String newVar) {
		operatingSystemName = newVar;
	}

	/**
	 * Get the value of operatingSystemName
	 * @return the value of operatingSystemName
	 */
	public String getOperatingSystemName () {
		return operatingSystemName;
	}

	/**
	 * Set the value of operatingSystemType
	 * @param newVar the new value of operatingSystemType
	 */
	public void setOperatingSystemType (undef newVar) {
		operatingSystemType = newVar;
	}

	/**
	 * Get the value of operatingSystemType
	 * @return the value of operatingSystemType
	 */
	public undef getOperatingSystemType () {
		return operatingSystemType;
	}

	/**
	 * Set the value of AWB_VERSION
	 * @param newVar the new value of AWB_VERSION
	 */
	public void setAWB_VERSION (String newVar) {
		AWB_VERSION = newVar;
	}

	/**
	 * Get the value of AWB_VERSION
	 * @return the value of AWB_VERSION
	 */
	public String getAWB_VERSION () {
		return AWB_VERSION;
	}

	/**
	 * Set the value of ipAddressV4
	 * @param newVar the new value of ipAddressV4
	 */
	public void setIpAddressV4 (String newVar) {
		ipAddressV4 = newVar;
	}

	/**
	 * Get the value of ipAddressV4
	 * @return the value of ipAddressV4
	 */
	public String getIpAddressV4 () {
		return ipAddressV4;
	}

	/**
	 * Set the value of guid
	 * @param newVar the new value of guid
	 */
	public void setGuid (GUID newVar) {
		guid = newVar;
	}

	/**
	 * Get the value of guid
	 * @return the value of guid
	 */
	public GUID getGuid () {
		return guid;
	}

	/**
	 * Set the value of groups_
	 * @param newVar the new value of groups_
	 */
	public void setGroups_ (Group newVar) {
		groups_ = newVar;
	}

	/**
	 * Get the value of groups_
	 * @return the value of groups_
	 */
	public Group getGroups_ () {
		return groups_;
	}

	//
	// Other methods
	//

	/**
	 */
	public void generateGuid()
	{
	}


	/**
	 * @param        groupGuid
	 */
	public void deleteGroup(GUID groupGuid)
	{
	}


	/**
	 * @param        group
	 */
	public void addGroup(Group group)
	{
	}


}
