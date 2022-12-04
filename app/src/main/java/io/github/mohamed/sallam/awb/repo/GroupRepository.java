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

public class GroupRepository {
    private GroupDao groupDao;
    private BlockedAppDao blockedAppDao;

    public GroupRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        groupDao = db.groupDao();
        blockedAppDao = db.blockedAppDao();
    }

    //GroupDao
    public void insertGroup(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.insert(group);
            }
        });
    }

    public void updateGroup(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.update(group);
            }
        });
    }

    public void deleteGroup(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.delete(group);
            }
        });
    }

    public LiveData<List<Group>> getAllGroup() {
        return groupDao.getAll();
    }

    public LiveData<List<GroupWithBlockedApps>> getAllGroupWithBlockedApps() {
        return groupDao.getAllWithBlockedApps();
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

    public LiveData<List<BlockedApp>> getAllBlockedApp() {
        return blockedAppDao.getAll();
    }

    public void cloneBlockedAppToGroup
            (UUID sourceGroupUuid, UUID destinationGroupUuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.clone(sourceGroupUuid, destinationGroupUuid);
            }
        });
    }
}
