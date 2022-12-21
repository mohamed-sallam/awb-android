package io.github.mohamed.sallam.awb.db.entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.repo.IAggregateRoot;

/**
 * This class contains the groups of apps which the user wants to block them
 *
 * @author Mohamed Sherif
 * @author Mohamed Sallam
 */
@TypeConverters({UuidConverter.class})
@Entity(tableName = "groups_table")
public class Group implements IAggregateRoot {
	// Fields
	@NonNull
	@PrimaryKey
	public UUID uuid = UUID.randomUUID();
	// TODO make name final
	public String name;
	public UUID deviceUuid;
	private boolean isSelected;

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}


	public Group(String name) {
		this.name = name;
		this.isSelected = false;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Group) {
			Group group = (Group) o;
			return Objects.equals(name, group.name)
					&& Objects.equals(uuid, group.uuid)
					&& Objects.equals(deviceUuid, group.deviceUuid);
		}
		return false;
	}

	// TODO add constructor pass name
	// TODO add getter for the name
	// TODO write equal method to compare with items in group adapter [in diff call]
}
