package io.github.mohamed.sallam.awb.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.TimestampConverter;
import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.repo.IAggregateRoot;

@TypeConverters({UuidConverter.class, TimestampConverter.class})
@Entity(tableName = "detox_periods_table")
public class DetoxPeriod extends DetoxSettings implements IAggregateRoot {
	public Timestamp endDate = new Timestamp(0);

	public DetoxPeriod() {
	}

	@Ignore
	public DetoxPeriod(Integer id, UUID groupUuid, long period) {
		super(id, groupUuid);
		this.setPeriod(period);
	}

	@Ignore
	public DetoxPeriod(Integer id, UUID groupUuid, Timestamp endDate) {
		super(id, groupUuid);
		this.endDate = endDate;
	}

	@Ignore
	public DetoxPeriod(@NonNull DetoxPeriod detoxPeriod) {
		this(detoxPeriod.id, detoxPeriod.groupUuid, detoxPeriod.endDate);
	}

	public void setPeriod(long period) {
		if (period < 0)
			throw new IllegalArgumentException("No Negative Period is Allowed!");
		endDate.setTime(new Date().getTime() + period);
	}
}
