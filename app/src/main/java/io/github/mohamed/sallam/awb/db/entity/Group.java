package io.github.mohamed.sallam.awb.db.entity;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

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
	public String name;
	public UUID deviceUuid;

}
