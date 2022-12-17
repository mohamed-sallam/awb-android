package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;

/**
 * `DetoxPeriodAndGroupWithWhitelistedApps` class is a relationship between three
 * entities `DetoxPeriod`, `Group` and `WhitelistedApps` in order to query a detox
 * period with the corresponding group including its whitelisted applications
 * from database by using DAO method. It's one-to-many relationship.
 *
 * Source: https://developer.android.com/training/data-storage/room/relationships
 */
public class DetoxPeriodAndGroupWithWhitelistedApps {
    @Embedded
    public DetoxPeriodAndGroup detoxPeriodAndGroup;
    @Relation(
            parentColumn = "groupUuid",
            entityColumn = "groupUuid"
    )
    public List<WhitelistedApp> whitelistedApps;
}
