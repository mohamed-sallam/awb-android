package io.github.mohamed.sallam.awb;
import java.util.UUID;
abstract public class DetoxSettings {

	public static final String AWB_VERSION = "0.1.0v";
	private UUID groupGuid;
	public  DetoxSettings()	{
		// implementation
	}

	/**
	 * Sets the value of groupGuid.
	 *
	 * @param newVar the new value of groupGuid.
	 *
	 * @author Mohamed Yehia
	 */
	public void setGroupGuid(UUID newVar) {
		groupGuid = newVar;
	}

	/**
	 * Generates new value of groupGuid.
	 *
	 * @author Mohamed Yehia
	 */
	public void generateGroupGuid() {
		//implementation
	}

	/**
	 * Gets the value of groupGuid.
	 *
	 * @return the value of groupGuid.
	 *
	 * @author Mohamed Yehia
	 */
	public UUID getGroupGuid() {
		return groupGuid;
	}

	/**
	 * * Locks the phone according to detox setting.
	 *
	 * @author Mohamed Yehia
	 */
	abstract public void Lock();
}