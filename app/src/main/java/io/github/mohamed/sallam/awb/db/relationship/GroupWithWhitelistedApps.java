package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;

/**
 * `GroupWithWhitelistedApps` class is a relationship between two entities `Group`
 * and `WhitelistedApp` in order to query a group with its applications
 * from database by using DAO method.
 *
 * Source: https://developer.android.com/training/data-storage/room/relationships
 */
public class GroupWithWhitelistedApps {
    @Embedded
    public Group group;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "groupUuid"
    )
    public List<WhitelistedApp> whitelistedApps;
}
