package io.github.mohamed.sallam.awb;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class User that has methods to generate a unique secret key for every user
 * and add devices in the devices list using Singleton Design Pattern.
 * Sources: https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm
 *
 * @author Yousef Ahmed
 */
public class User {
	// Fields
	private ArrayList<Device> devices = new ArrayList<>();
	private String secretKey;

	// Creating an object of User
	private static User instance;

	/// Make the constructor private so that this class cannot be instantiated
	private User () { }

	// Methods

	// Accessor Methods
	/**
	 * Gets the devices from device list
	 * @author Yousef Ahmed
	 */
	public ArrayList<Device> getDevices () {
		return devices;
	}

	/**
	 * Gets the secret key
	 * @return the value of secret key
	 * @author Yousef Ahmed
	 */
	public String getSecretKey() {
		return secretKey;
	}


	// Mutator Method
	/**
	 * Generates the secret key
	 * @author Yousef Ahmed
	 */
	public void generateSecretKey() {

	}

	/**
	 * Adds a new device
	 * @param device the value of Device
	 * @author Yousef Ahmed
	 */
	public void addDevice(Device device) {
		devices.add(device);
	}


	// Other Methods
	/**
	 * Deletes the device in device list
	 * @param deviceUuid the value of deleted device
	 * @author Yousef Ahmed
	 */
	public void deleteDevice(UUID deviceUuid) {
		for (Device list : devices) {
			if (list.getUuid() == deviceUuid) {
				devices.remove(list);
				break;
			}
		}
	}

	/**
	 * Get the only object available
	 * @return the only instance of User class
	 * @author Yousef Ahmed
	 */
	public static User getInstance() {
		if (instance == null)
			instance = new User();
		return instance;
	}
}
