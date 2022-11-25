
public class Group {

	private String software_;
	private String websites_;
	private String name;
	private GUID guid;	
	public Group () { }
	
	//
	// Methods
	//


	/**
	 * Set the value of software_
	 * @param newVar the new value of software_
	 */
	public void setSoftware_ (String newVar) {
		software_ = newVar;
	}

	/**
	 * Get the value of software_
	 * @return the value of software_
	 */
	public String getSoftware_ () {
		return software_;
	}

	/**
	 * Set the value of websites_
	 * @param newVar the new value of websites_
	 */
	public void setWebsites_ (String newVar) {
		websites_ = newVar;
	}

	/**
	 * Get the value of websites_
	 * @return the value of websites_
	 */
	public String getWebsites_ () {
		return websites_;
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

	//
	// Other methods
	//

	/**
	 */
	public void clone()
	{
	}


	/**
	 * @param        index
	 */
	public void deleteApp( int index)
	{
	}


	/**
	 * @param        app
	 */
	public void addApp(String app)
	{
	}


	/**
	 * @param        website
	 */
	public void addWebsite(String website)
	{
	}


	/**
	 * @param        index
	 */
	public void deleteWebsite( int index)
	{
	}


	/**
	 */
	public void rename()
	{
	}


}
