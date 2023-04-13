package io.github.mohamed.sallam.awb.db.entity;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.sql.Timestamp;
import java.util.Date;

import io.github.mohamed.sallam.awb.db.converter.TimestampConverter;
import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.repo.AggregateRoot;

@TypeConverters({UuidConverter.class, TimestampConverter.class})
@Entity(tableName = "detox_periods_table")
public class DetoxPeriod extends DetoxSettings implements AggregateRoot {
	// Fields
	public Timestamp endDate = new Timestamp(0);

	/**
	 * Sets the period for which the phone is locked.
	 *
	 * @param period the period in milliseconds in which the phone is locked.
	 *
	 * @author Mohamed Yehia
	 */
	public void setPeriod(long period) {
		endDate.setTime(new Date().getTime() + period);
	}
}
