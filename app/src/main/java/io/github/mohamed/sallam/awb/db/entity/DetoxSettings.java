package io.github.mohamed.sallam.awb.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.TimestampConverter;
import io.github.mohamed.sallam.awb.db.converter.UuidConverter;

@TypeConverters({UuidConverter.class, TimestampConverter.class})
@Entity
abstract public class DetoxSettings {
	@PrimaryKey(autoGenerate = true)
	public Integer id;
	public static final String AWB_VERSION = "0.1.0v";
	public UUID groupUuid;

	/**
	 * Locks the phone according to detox setting.
	 *
	 * @author Mohamed Yehia
	 */
	abstract public void Lock();
}