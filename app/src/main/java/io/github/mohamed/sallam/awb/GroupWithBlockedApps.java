package io.github.mohamed.sallam.awb;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GroupWithBlockedApps {
    @Embedded
    public Group group;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "groupUuid"
    )
    public List<BlockedApp> blockedApps;
}
