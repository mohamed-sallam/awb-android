package io.github.mohamed.sallam.awb;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class contains the groups of apps which the user wants to block them
 * @author Mohamed Sherif
 */
@Entity(tableName = "groups_table")
public class Group {
	// Fields
	private ArrayList<String> softwares;
	private ArrayList<String> websites;
	private String name;
	@PrimaryKey
	private UUID uuid;
	private UUID deviceUuid ;

	// Constructor
	public Group () { }

	
	// Methods

	// Mutator Methods


	/**
	 * Sets the value of UUID.
	 * @param newVar the value of UUID.
	 */
	public void setUuid(UUID newVar) {
		this.uuid = newVar;
	}

	// Accessor Methods

	/**
	 * Gets the value of softwares.
	 * @return array of the blocked softwares.
	 */
	public ArrayList<String> getSoftwares() {
		return this.softwares;
	}

	/**
	 * Gets the value of websites.
	 * @return array of the blocked websites.
	 */
	public ArrayList<String> getWebsites() {
		return this.websites;
	}

	/**
	 * Renames the group.
	 * @param newName the new value of group name.
	 */
	public void rename(String newName) {
		this.name = newName;
	} //DAO_Group ??

	/**
	 * Gets group name.
	 * @return the value of name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the value of UUID.
	 * @return the value of UUID.
	 */
	public UUID getUuid() {
		return this.uuid;
	}

	// Other Methods

	/**
	 * Makes a copy of the current group.
	 * @return A copy of `Group` object.
	 */
	public Group clone() {
		Group newGroup = new Group();
		newGroup.softwares = this.softwares;
		newGroup.websites  = this.websites;
		newGroup.uuid      = this.uuid;
		newGroup.name      = this.name;
		return newGroup;
	}

}
