import java.util.UUID;

public class User {

	private Device devices_;
	private String secretKey;	
	public User () { };
	
	//
	// Methods
	//


	/**
	 * Set the value of devices_
	 * @param newVar the new value of devices_
	 */
	public void setDevices_ (Device newVar) {
		devices_ = newVar;
	}

	/**
	 * Get the value of devices_
	 * @return the value of devices_
	 */
	public Device getDevices_ () {
		return devices_;
	}

	/**
	 * Set the value of secretKey
	 * @param newVar the new value of secretKey
	 */
	public void setSecretKey (String newVar) {
		secretKey = newVar;
	}

	/**
	 * Get the value of secretKey
	 * @return the value of secretKey
	 */
	public String getSecretKey () {
		return secretKey;
	}

	//
	// Other methods
	//

	/**
	 */
	public void generateSecretKey()
	{
	}


	/**
	 * @param        deviceGuid
	 */
	public void deleteDevice(GUID deviceGuid)
	{
	}


	/**
	 * @param        device
	 */
	public void addDevice(Device device)
	{
	}


}
