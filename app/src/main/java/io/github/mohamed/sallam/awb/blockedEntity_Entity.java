package io.github.mohamed.sallam.awb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "blockedEntity_table")
public class blockedEntity_Entity{
    @PrimaryKey(autoGenerate = false)
    String blockedEntity ;

    public blockedEntity_Entity(String blockedEntity) {
        this.blockedEntity = blockedEntity;
    }
    public String getBlockedEntity() {
        return blockedEntity;
    }
}