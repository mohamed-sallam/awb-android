package io.github.mohamed.sallam.awb.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.TypeConverters;

import java.sql.Timestamp;
import java.util.UUID;

@Entity(tableName = "detoxPeriod_table",
		foreignKeys = {@ForeignKey(entity = Group.class,
		parentColumns = {"uuid"},
		childColumns = {"groupUuid"}, onUpdate = ForeignKey.CASCADE,
		onDelete = ForeignKey.CASCADE)})
@TypeConverters({TimestampConverter.class})
public class DetoxPeriod extends DetoxSettings {

	private Timestamp endDate;
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
