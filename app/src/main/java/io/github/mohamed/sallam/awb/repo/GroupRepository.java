package io.github.mohamed.sallam.awb.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
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

    public LiveData<List<Group>> getAll(UUID deviceUuid) {
        return groupDao.getAllByDevice(deviceUuid);
    }

    public LiveData<List<GroupWithBlockedApps>>
    getAllWithBlockedApps(UUID deviceUuid) {
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

    public void deleteBlockedApp(int idBlockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.delete(idBlockedApp);
            }
        });
    }

    public LiveData<List<BlockedApp>>
    getAllBlockedApps(UUID groupUuid) {
        return blockedAppDao.getAllByGroup(groupUuid);
    }

    public void clone (UUID srcGroupUuid, String groupName) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run() {
                Group desGroup = new Group();
                desGroup.name = groupName;
                desGroup.deviceUuid = groupDao.get(srcGroupUuid).deviceUuid;
                blockedAppDao.clone(srcGroupUuid, desGroup.uuid);
                groupDao.insert(desGroup);
            }
        });
    }
}
