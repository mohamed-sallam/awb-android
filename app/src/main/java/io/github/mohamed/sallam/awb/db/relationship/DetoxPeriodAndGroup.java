package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Group;

/**
 * `DetoxPeriodAndGroup` class is a relationship between two entities `Group`
 * and `DetoxPeriod` in order to query a detox period with the corresponding group
 * from database by using DAO method. It's one-to-one relationships.
 *
 * @see <a href =”https://developer.android.com/training/data-storage/room/relationships”>
 * Define relationships between objects</a>
 */
public class DetoxPeriodAndGroup {
    @Embedded
    public DetoxPeriod detoxPeriod;
    @Relation(
            parentColumn = "groupUuid",
            entityColumn = "uuid"
    )
    public Group group;
}
