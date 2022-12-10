package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;

public interface IGroupRepository extends IRepository<Group, UUID> {
    void rename(UUID uuid, String name);
    LiveData<List<Group>> getAllByDevice(UUID deviceUuid);
    LiveData<List<GroupWithBlockedApps>> getAllWithBlockedAppsByDevice(UUID deviceUuid);
    void insertBlockedApp(BlockedApp blockedApp);
    void deleteBlockedApp(Integer idBlockedApp);  // TODO: declared while working on RecyclerView
    LiveData<List<BlockedApp>> getAllBlockedAppsByGroupUuid(UUID groupUuid);
    void clone(UUID sourceGroupUuid, String newGroupName);
}
