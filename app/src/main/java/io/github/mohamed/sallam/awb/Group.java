package io.github.mohamed.sallam.awb;
import java.util.ArrayList;
import java.util.UUID;

	/**
	 * This class contains the groups of apps which the user wants to block them
	 * @author Mohamed Sherif
	 */

public class Group {

	// Fields

	private ArrayList<String> softwares;
	private ArrayList<String> websites;
	private String name;
	private UUID uuid;

	// Constructor
	public Group () { }

	// Methods

	// Mutator Methods
	/**
	 * Adds software
	 * @param software the new blocked software
	 */
	public void addSoftware(String software) {
		this.softwares.add(software);
	}

	/**
	 * Adds website
	 * @param website the new blocked website
	 */
	public void addWebsite(String website) {
		this.websites.add(website);
	}

	/**
	 * Sets the value of uuid
	 * @param newVar the value of uuid
	 */
	public void setUuid(UUID newVar) {
		this.uuid = newVar;
	}

	// Accessor Methods

	/**
	 * Gets the value of softwares
	 * @return array of the blocked softwares
	 */
	public ArrayList<String> getSoftwares() {
		return this.softwares;
	}

	/**
	 * Gets the value of websites
	 * @return array of the blocked websites
	 */
	public ArrayList<String> getWebsites() {
		return this.websites;
	}

	/**
	 * Renames the group
	 * @param newName the new value of group name
	 */
	public void rename(String newName) {
		this.name = newName;
	}

	/**
	 * Gets group name
	 * @return the value of name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the value of uuid
	 * @return the value of uuid
	 */
	public UUID getUuid() {
		return this.uuid;
	}

	// Other Methods

	/**
	 * Makes a copy of the current group
	 * @return newGroup
	 */
	public Group clone() {
		Group newGroup = new Group();
		newGroup.softwares = this.getSoftwares();
		newGroup.websites = this.getWebsites();
		newGroup.setUuid(this.uuid);
		newGroup.rename(this.name);
		return newGroup;
	}

	/**
	 * Deletes Software
	 * @param index of the software desired to be deleted
	 */
	public void deleteSoftware(int index) {
		this.softwares.remove(index);
	}

	/**
	 * Deletes Website
	 * @param index of the website desired to be deleted
	 */
	public void deleteWebsite(int index) {
		this.websites.remove(index);
	}
}
