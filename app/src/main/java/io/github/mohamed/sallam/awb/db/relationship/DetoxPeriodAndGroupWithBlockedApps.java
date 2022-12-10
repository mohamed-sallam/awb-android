package io.github.mohamed.sallam.awb.db.relationship;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.BlockedApp;

public class DetoxPeriodAndGroupWithBlockedApps {
    @Embedded
    public DetoxPeriodAndGroup detoxPeriodAndGroup;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "groupUuid"
    )
    public LiveData<List<BlockedApp>> blockedApps;
}
