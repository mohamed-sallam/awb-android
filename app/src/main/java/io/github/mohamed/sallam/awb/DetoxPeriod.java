package io.github.mohamed.sallam.awb;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.UUID;

import io.github.mohamed.sallam.awb.Device;
import io.github.mohamed.sallam.awb.Group;

@Entity(tableName = "detoxPeriod_table",
		foreignKeys = {@ForeignKey(entity = Group.class,
		parentColumns = {"uuid"},
		childColumns = {"groupUuid"}, onUpdate = ForeignKey.CASCADE,
		onDelete = ForeignKey.CASCADE)})
public class DetoxPeriod extends DetoxSettings {

	private long endDate;
	private UUID groupUuid;
	public DetoxPeriod () { };

	//
	// Methods
	//


	/**
	 * Set the value of endDate
	 * @param newVar the new value of endDate
	 */
	public void setEndDate (long newVar) {
		endDate = newVar;
	}

	/**
	 * Get the value of endDate
	 * @return the value of endDate
	 */
	public long getEndDate () {
		return endDate;
	}

	/**
	 */
	public void lock()
	{
	}

	/**
	 * @param        period
	 */
	public void setPeriod(long period)
	{
	}


}
