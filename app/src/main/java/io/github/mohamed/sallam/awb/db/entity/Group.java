package io.github.mohamed.sallam.awb.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.repo.AggregateRoot;

/**
 * Class `Group` to define the structure of a Group object. It represents a table
 * on our database contains all Groups created on the application by the user.
 *
 * @author Mohamed Sherif
 * @author Mohamed Sallam
 */
@TypeConverters({UuidConverter.class})
@Entity(tableName = "groups_table")
public class Group implements AggregateRoot {
	// Fields
	@NonNull
	@PrimaryKey
	public UUID uuid = UUID.randomUUID();
	public String name;
	public UUID deviceUuid;

	@Override
	public boolean equals(Object o) {
		if (o instanceof Group) {
			Group group = (Group) o;
			return uuid.equals(group.uuid) &&
				   name.equals(group.name) &&
				   deviceUuid.equals(group.deviceUuid);
		}
		return false;
	}
}
