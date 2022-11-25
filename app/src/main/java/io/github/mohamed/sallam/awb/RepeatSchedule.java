package io.github.mohamed.sallam.awb;

/**
 * @author Mohamed Sallam
 */
public class RepeatSchedule {
	// Fields
	private Duration   duration   = new Duration.FOREVER();

	private Repetition repetition = NONE;
	// Methods

	// Accessor methods
	/**
	 * Gets the value of repetition
	 * @return the value of repetition
	 */
	public Repetition getRepetition() {
		return repetition;
	}

	/**
	 * Gets the value of duration
	 * @return the value of duration
	 */
	public Duration getDuration() {
		return duration;
	}

	// Mutator methods
	/**
	 * Sets the value of repetition
	 * @param newVar the new value of repetition
	 */
	public void setRepetition(Repetition newVar) {
		repetition = newVar;
	}

	/**
	 * Sets the value of duration
	 * @param newVar the new value of duration
	 */
	public void setDuration(Duration newVar) {
		duration = newVar;
	}
}
