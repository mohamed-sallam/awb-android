package io.github.mohamed.sallam.awb;

public class DetoxPeriod extends DetoxSettings {
	private long endDate;
	public DetoxPeriod()
	{
		// Methods
	}

	/**
	 * Set the value of endDate
	 * @param newVar the new value of endDate
	 */
	public void setEndDate (long newVar)
	{
		endDate = newVar;
	}

	/**
	 * Get the value of endDate
	 * @return the value of endDate
	 */
	public long getEndDate ()
	{
		return endDate;
	}


	/**
	 * @param        period  the period in which the phone is locked
	 */
	public void setPeriod(long period)
	{
	}

	/**
	 * * lock the phone according to detox setting
	 */
	public void lock()
	{
		// methods
	}
}
