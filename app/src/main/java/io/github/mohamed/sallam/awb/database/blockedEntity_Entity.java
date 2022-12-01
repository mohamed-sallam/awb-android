package io.github.mohamed.sallam.awb.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "blockedEntity_table",
        foreignKeys = {@ForeignKey(entity = Group.class,
        parentColumns = {"uuid"},
        childColumns = {"groupUuid"}, onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)})
public class blockedEntity_Entity{
    @PrimaryKey(autoGenerate = false)
    String blockedEntity ;
    private UUID groupUuid;

    public blockedEntity_Entity(String blockedEntity) {
        this.blockedEntity = blockedEntity;
    }
    public String getBlockedEntity() {
        return blockedEntity;
    }
}