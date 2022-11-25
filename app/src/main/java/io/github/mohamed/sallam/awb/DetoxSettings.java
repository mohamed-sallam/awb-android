
abstract public class DetoxSettings {

	static private String AWB_VERSION = "0.1.0v";
	private GUID groupGuid;	
	public DetoxSettings () { };
	
	//
	// Methods
	//


	/**
	 * Get the value of AWB_VERSION
	 * @return the value of AWB_VERSION
	 */
	public String getAWB_VERSION () {
		return AWB_VERSION;
	}

	/**
	 * Set the value of groupGuid
	 * @param newVar the new value of groupGuid
	 */
	public void setGroupGuid (GUID newVar) {
		groupGuid = newVar;
	}

	/**
	 * Get the value of groupGuid
	 * @return the value of groupGuid
	 */
	public GUID getGroupGuid () {
		return groupGuid;
	}

	//
	// Other methods
	//

	/**
	 */
	abstract public void lock();


}
