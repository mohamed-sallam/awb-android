package io.github.mohamed.sallam.awb;

/**
 * `RepeatSchedule` class responsible for hold repetition and duration settings.
 *
 * @author Mohamed Sallam
 */
public class RepeatSchedule {
	// Fields
	private Repetition repetition = new Repetition.NONE();
	private Duration   duration   = new Duration.FOREVER();

	/**
	 * Gets the value of repetition.
	 *
	 * @return the value of repetition.
	 */
	public Repetition getRepetition() {
		return repetition;
	}

	/**
	 * Gets the value of duration.
	 *
	 * @return the value of duration.
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * Sets the value of repetition. Used when you want to set a repetition
	 * for a schedule.
	 *
	 * @param newVar the new value of repetition.
	 */
	public void setRepetition(Repetition newVar) {
		repetition = newVar;
	}

	/**
	 * Sets the value of locking duration.
	 *
	 * @param newVar the new value of duration.
	 */
	public void setDuration(Duration newVar) {
		duration = newVar;
	}
}
