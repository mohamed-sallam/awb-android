package io.github.mohamed.sallam.awb;

import java.util.UUID;

abstract public class DetoxSettings {

	public static final String AWB_VERSION = "0.1.0v";
	private UUID groupUuid;
	public  DetoxSettings()	{
		// implementation
	}

	/**
	 * Sets the value of groupUuid.
	 *
	 * @param newVar the new value of groupUuid.
	 *
	 * @author Mohamed Yehia
	 */
	public void setGroupUuid(UUID newVar) {
		groupUuid = newVar;
	}

	/**
	 * Generates new value of groupUuid.
	 *
	 * @author Mohamed Yehia
	 */
	public void generateGroupUuid() {
		//implementation
	}

	/**
	 * Gets the value of groupUuid.
	 *
	 * @return the value of groupUuid.
	 *
	 * @author Mohamed Yehia
	 */
	public UUID getGroupUuid() {
		return groupUuid;
	}

	/**
	 * Locks the phone according to detox setting.
	 *
	 * @author Mohamed Yehia
	 */
	abstract public void Lock();
}