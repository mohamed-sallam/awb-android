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

    public void rename(String name, UUID uuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.rename(name, uuid);
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
        return groupDao.getAll(deviceUuid);
    }

    public LiveData<List<GroupWithBlockedApps>>
    getAllWithBlockedApps(UUID deviceUuid) {
        return groupDao.getAllWithBlockedApps(deviceUuid);
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

    public void deleteBlockedApp(BlockedApp blockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.delete(blockedApp);
            }
        });
    }

    public LiveData<List<BlockedApp>>
    getAllBlockedApps(UUID deviceUuid, UUID groupUuid) {
        return blockedAppDao.getAll(deviceUuid, groupUuid);
    }

    public void clone
            (UUID sourceGroupUuid, String groupName) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                Group destinationGroup = new Group(groupName);
                blockedAppDao.clone(sourceGroupUuid, destinationGroup.uuid);
            }
        });
    }
}
