import java.util.UUID;
import java.util.*;

public class RepeatSchedule {

	//
	// Fields
	//

	private Repetition repetition = NONE;
	private Duration duration = FOREVER;	
	public RepeatSchedule () { };
	
	//
	// Methods
	//


	//
	// Accessor methods
	//

	/**
	 * Set the value of repetition
	 * @param newVar the new value of repetition
	 */
	public void setRepetition (Repetition newVar) {
		repetition = newVar;
	}

	/**
	 * Get the value of repetition
	 * @return the value of repetition
	 */
	public Repetition getRepetition () {
		return repetition;
	}

	/**
	 * Set the value of duration
	 * @param newVar the new value of duration
	 */
	public void setDuration (Duration newVar) {
		duration = newVar;
	}

	/**
	 * Get the value of duration
	 * @return the value of duration
	 */
	public Duration getDuration () {
		return duration;
	}

}
