package io.github.mohamed.sallam.awb;

import java.util.ArrayList;
import java.util.UUID;

/**
 * User class has methods to generate a unique secret key for a user
 * and add devices in the devices list and this class uses
 * Singleton Design Pattern.
 * Sources: https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm
 *
 * @author Yousef Ahmed
 */
public class User {
	// Fields
	private ArrayList<Device> devices = new ArrayList<>();
	private String secretKey;

	// Creating an object of User class
	private static User instance;

	/// Make the constructor private so that this class cannot be instantiated
	private User () { }

	// Methods

	// Accessor Methods
	/**
	 * Gets the devices from device list.
	 *
	 * @author Yousef Ahmed
	 */
	public ArrayList<Device> getDevices () {
		return devices;
	}

	/**
	 * Gets the user's secret key.
	 *
	 * @return the user's secret key.
	 *
	 * @author Yousef Ahmed
	 */
	public String getSecretKey() {
		return secretKey;
	}


	// Mutator Method
	/**
	 * Generates new user's secret key.
	 *
	 * @author Yousef Ahmed
	 */
	public void generateSecretKey() {

	}

	/**
	 * Adds a new device to devices list.
	 *
	 * @param device to be added to the devices list.
	 *
	 * @author Yousef Ahmed
	 */
	public void addDevice(Device device) {
		devices.add(device);
	}


	// Other Methods
	/**
	 * Deletes a device from devices list using its UUID.
	 *
	 * @param deviceUuid the value of deleted device.
	 *
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
	 * Gets the only object available.
	 *
	 * @return the only instance of User class.
	 *
	 * @author Yousef Ahmed
	 */
	public static User getInstance() {
		if (instance == null)
			instance = new User();
		return instance;
	}
}
