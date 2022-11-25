package io.github.mohamed.sallam.awb;
import java.util.UUID;
abstract public class DetoxSettings {

	public static final String AWB_VERSION = "0.1.0v";
	private UUID groupGuid;
	public  DetoxSettings()	{
		// Methods
	}

	/**
	 * Set the value of groupGuid
	 *
	 * @param newVar the new value of groupGuid
	 *
	 * @author Mohamed Yehia
	 */
	public void setGroupGuid (UUID newVar) {
		groupGuid = newVar;
	}

	/**
	 * Generates new value of groupGuid
	 *
	 * @author mohamed yehia
	 */
	public void generateGroupGuid () {

	}

	/**
	 * Get the value of groupGuid
	 *
	 * @return the value of groupGuid
	 *
	 * @author Mohamed Yehia
	 */
	public UUID getGroupGuid () {
		return groupGuid;
	}

	/**
	 * * lock the phone according to detox setting
	 * @author Mohamed Yehia
	 */
	abstract public void lock();
}