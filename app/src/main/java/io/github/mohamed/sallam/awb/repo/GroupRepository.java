package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.BlockedAppDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;

public class GroupRepository implements IGroupRepository {
    private GroupDao groupDao;
    private BlockedAppDao blockedAppDao;

    public GroupRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        groupDao = db.groupDao();
        blockedAppDao = db.blockedAppDao();
    }

    //GroupDao
    public void insert(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.insert(group);
            }
        });
    }

    public void rename(UUID uuid, String name) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.rename(uuid, name);
            }
        });
    }

    public void delete(UUID groupUuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.delete(groupUuid);
                blockedAppDao.deleteByGroupUuid(groupUuid);
            }
        });
    }

    public LiveData<List<Group>> getAllByDevice(UUID deviceUuid) {
        return groupDao.getAllByDevice(deviceUuid);
    }

    public LiveData<List<GroupWithBlockedApps>>
    getAllWithBlockedAppsByDevice(UUID deviceUuid) {
        return groupDao.getAllWithBlockedAppsByDevice(deviceUuid);
    }

    // BlockedAppDao
    public void insertBlockedApp(BlockedApp blockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.insert(blockedApp);
            }
        });
    }

    public void deleteBlockedApp(Integer idBlockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.delete(idBlockedApp);
            }
        });
    }

    public LiveData<List<BlockedApp>>
    getAllBlockedAppsByGroupUuid(UUID groupUuid) {
        return blockedAppDao.getAllByGroupUuid(groupUuid);
    }

    public void clone(UUID sourceGroupUuid, String groupName) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run() {
                Group destinationGroup = new Group();
                destinationGroup.name = groupName;
                destinationGroup.deviceUuid = Objects.requireNonNull(groupDao.get(sourceGroupUuid).getValue()).deviceUuid;
                blockedAppDao.clone(sourceGroupUuid, destinationGroup.uuid);
                groupDao.insert(destinationGroup);
            }
        });
    }
}
