package io.github.mohamed.sallam.awb;
import java.sql.Timestamp;
public class DetoxPeriod extends DetoxSettings {
	private Timestamp endDate;
	public DetoxPeriod() {
		// implementation
	}

	/**
	 * Sets the value of endDate
	 *
	 * @param newVar the new value of endDate
	 *
	 * @author Mohamed Yehia
	 */
	public void setEndDate (Timestamp newVar) {
		endDate = newVar;
	}

	/**
	 * Gets the value of endDate
	 *
	 * @return the value of endDate
	 *
	 * @author Mohamed Yehia
	 */
	public Timestamp getEndDate () {
		return endDate;
	}

	/**
	 * sets the period for which the phone is locked
	 *
	 * @param period the period in which the phone is locked
	 *
	 * @author Mohamed Yehia
	 */
	public void setPeriod(Timestamp period) {
		//set enddate??
		endDate = period + ?? ;

	}

	/**
	 * locks the phone according to detox setting
	 *
	 * @author Mohamed Yehia
	 */
	public void lock() {
		// implementation
	}
}
