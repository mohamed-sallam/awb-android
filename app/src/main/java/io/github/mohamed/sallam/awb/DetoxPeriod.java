package io.github.mohamed.sallam.awb; //comments //parenthesis //Timestamp
import java.sql.Timestamp;
public class DetoxPeriod extends DetoxSettings {
	private Timestamp endDate;
	public DetoxPeriod() {
		// Methods
	}

	/**
	 * Set the value of endDate
	 *
	 * @param newVar the new value of endDate
	 *
	 * @author Mohamed Yehia
	 */
	public void setEndDate (Timestamp newVar) {
		endDate = newVar;
	}

	/**
	 * Get the value of endDate
	 *
	 * @return the value of endDate
	 *
	 * @author Mohamed Yehia
	 */
	public Timestamp getEndDate () {
		return endDate;
	}

	/**
	 * @param period the period in which the phone is locked
	 *
	 * @author Mohamed Yehia
	 */
	public void setPeriod(Timestamp period) {
		//set enddate??
		endDate = period + ?? ;

	}

	/**
	 * lock the phone according to detox setting
	 *
	 * @author Mohamed Yehia
	 */
	public void lock() {
		// methods
	}
}
