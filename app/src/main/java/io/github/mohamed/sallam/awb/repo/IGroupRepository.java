package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithWhitelistedApps;

public interface IGroupRepository extends IRepository<Group> {
    void rename(UUID uuid, String name);
    LiveData<List<Group>> getAllByDevice(UUID deviceUuid);
    LiveData<GroupWithWhitelistedApps> getWithWhitelistedApps(UUID uuid);
    void insertWhitelistedApp(WhitelistedApp whitelistedApp);
    void deleteWhitelistedApp(UUID groupUuid, String packageName);  // TODO: declared while working on RecyclerView
    LiveData<List<WhitelistedApp>> getAllWhitelistedAppsByGroupUuid(UUID groupUuid);
    void clone(UUID sourceGroupUuid, String newGroupName);
}
