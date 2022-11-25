package io.github.mohamed.sallam.awb;
import java.util.UUID;

public class Group {
	private String softwares;
	private String websites;
	private String name;
	private UUID uuid;
	public Group () { }

	// Methods

	/**
	 * Set the value of softwares
	 * @param newVar the new value of softwares
	 */
	public void setSoftwares(String newVar) {
		softwares = newVar;
	}

	/**
	 * Get the value of softwares
	 * @return the value of softwares
	 */
	public String getSoftwares() {
		return softwares;
	}

	/**
	 * Set the value of websites
	 * @param newVar the new value of websites
	 */
	public void setWebsites(String newVar) {
		websites = newVar;
	}

	/**
	 * Get the value of websites
	 * @return the value of websites
	 */
	public String getWebsites() {
		return websites;
	}

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
	 * Set the value of uuid
	 * @param newVar the new value of uuid
	 */
	public void setUuid(UUID newVar) {
		uuid = newVar;
	}

	/**
	 * Get the value of uuid
	 * @return the value of uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 */
	public void clone()
	{
	}

	/**
	 * Deletes App
	 * @param index of the app desired to be deleted
	 */
	public void deleteApp( int index)
	{
	}

	/**
	 * Adds App
	 * @param app
	 */
	public void addApp(String app)
	{
	}

	/**
	 * Adds Website
	 * @param website
	 */
	public void addWebsite(String website)
	{
	}

	/**
	 * Deletes Website
	 * @param index of the app desired to be deleted
	 */
	public void deleteWebsite( int index)
	{
	}

	/**
	 * Renames the Group of Apps
	 */
	public void rename()
	{
	}
}
