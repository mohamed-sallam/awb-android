package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;

public class GroupWithBlockedApps {
    @Embedded
    public Group group;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "groupUuid"
    )
    public List<BlockedApp> blockedApps;
}
