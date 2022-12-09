package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;

public interface IGroupRepository extends IRepository<Group, UUID> {
    void rename(UUID uuid, String name);
    LiveData<List<Group>> getAll(UUID deviceUuid);
    LiveData<List<GroupWithBlockedApps>> getAllWithBlockedApps(UUID deviceUuid);
    void insertBlockedApp(BlockedApp blockedApp);
    void deleteBlockedApp(int idBlockedApp);  // TODO: declared while working on RecyclerView
    LiveData<List<BlockedApp>> getAllBlockedApps(UUID groupUuid);
    void clone
            (UUID sourceGroupUuid, String newGroupName);
}
