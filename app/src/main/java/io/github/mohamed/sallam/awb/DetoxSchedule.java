package io.github.mohamed.sallam.awb;
import java.sql.Timestamp;
public class DetoxSchedule extends DetoxSettings {
	private String title = "Custom";
	private Timestamp startDate;
	private Timestamp endDate;
	private RepeatSchedule repeatSchedule;	
	public DetoxSchedule() {
		// Methods
	}

	/**
	 * Set the value of title
	 *
	 * @param newVar the new value of title
	 *
	 * @author Mohamed Yehia
	 */
	public void setTitle(String newVar) {
		title = newVar;
	}

	/**
	 * Get the value of title
	 *
	 * @return the value of title
	 *
	 * @author Mohamed Yehia
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the value of startDate
	 *
	 * @param newVar the new value of startDate
	 *
	 * @author Mohamed Yehia
	 */
	public void setStartDate(Timestamp newVar) {
		startDate = newVar;
	}

	/**
	 * Get the value of startDate
	 *
	 * @return the value of startDate
	 *
	 * @author Mohamed Yehia
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Set the value of endDate
	 *
	 * @param newVar the new value of endDate
	 *
	 * @author Mohamed Yehia
	 */
	public void setEndDate(Timestamp newVar) {
		endDate = newVar;
	}

	/**
	 * Get the value of endDate
	 *
	 * @return the value of endDate
	 *
	 * @author Mohamed Yehia
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Set the value of repeatSchedule
	 *
	 * @param newVar the new value of repeatSchedule
	 *
	 * @author Mohamed Yehia
	 */
	public void setRepeatSchedule(RepeatSchedule newVar) {
		repeatSchedule = newVar;
	}

	/**
	 * Get the value of repeatSchedule
	 *
	 * @return the value of repeatSchedule
	 *
	 * @author Mohamed Yehia
	 */
	public RepeatSchedule getRepeatSchedule() {
		return repeatSchedule;
	}

	/**
	 * Lock the phone according to detox setting
	 *
	 * @author Mohamed Yehia
	 */
	public void lock() {
		// methods
	}
}
