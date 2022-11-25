package io.github.mohamed.sallam.awb;

public class DetoxSchedule extends DetoxSettings {

	private String title = "Custom";
	private long startDate;
	private long endDate;
	private RepeatSchedule repeatSchedule;	
	public DetoxSchedule()
	{
		// Methods
	}

	/**
	 * Set the value of title
	 * @param newVar the new value of title
	 */
	public void setTitle(String newVar)
	{
		title = newVar;
	}

	/**
	 * Get the value of title
	 * @return the value of title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Set the value of startDate
	 * @param newVar the new value of startDate
	 */
	public void setStartDate(long newVar)
	{
		startDate = newVar;
	}

	/**
	 * Get the value of startDate
	 * @return the value of startDate
	 */
	public long getStartDate()
	{
		return startDate;
	}

	/**
	 * Set the value of endDate
	 * @param newVar the new value of endDate
	 */
	public void setEndDate(long newVar)
	{
		endDate = newVar;
	}

	/**
	 * Get the value of endDate
	 * @return the value of endDate
	 */
	public long getEndDate()
	{
		return endDate;
	}

	/**
	 * Set the value of repeatSchedule
	 * @param newVar the new value of repeatSchedule
	 */
	public void setRepeatSchedule(RepeatSchedule newVar)
	{
		repeatSchedule = newVar;
	}

	/**
	 * Get the value of repeatSchedule
	 * @return the value of repeatSchedule
	 */
	public RepeatSchedule getRepeatSchedule()
	{
		return repeatSchedule;
	}

	//
	// Other methods
	//

	/**
	 * * lock the phone according to detox setting
	 */
	public void lock()
	{
		// methods
	}
}
