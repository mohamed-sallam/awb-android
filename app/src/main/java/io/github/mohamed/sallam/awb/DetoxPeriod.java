package io.github.mohamed.sallam.awb;

import java.util.Date;
import java.sql.Timestamp;

import io.github.mohamed.sallam.awb.DetoxSettings;

public class DetoxPeriod extends DetoxSettings {
	private Timestamp endDate;
	public DetoxPeriod() {
		// implementation
	}

	/**
	 * Sets the value of endDate.
	 *
	 * @param newVar the new value of endDate.
	 *
	 * @author Mohamed Yehia
	 */
	public void setEndDate(Timestamp newVar) {
		endDate = newVar;
	}

	/**
	 * Gets the value of endDate.
	 *
	 * @return the value of endDate.
	 *
	 * @author Mohamed Yehia
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Sets the period for which the phone is locked.
	 *
	 * @param period the period in minutes (i.e. 1 ---> 1minutes) in which the
	 * phone is locked.
	 *
	 * @author Mohamed Yehia
	 */
	public void setPeriod(long period) {
		endDate.setTime(new Date().getTime() + period * 60_000);
	}

	/**
	 * Locks the phone according to detox setting.
	 *
	 * @author Mohamed Yehia
	 */
	public void Lock() {
		// implementation
	}
}
