package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.WhitelistedAppDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithWhitelistedApps;

/**
 * {@inheritDoc}
 *
 * {@link IGroupRepository}
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public class GroupRepository implements IGroupRepository {
    private final GroupDao groupDao;
    private final WhitelistedAppDao whitelistedAppDao;

    /**
     * Instantiate an object from `GroupDao` and `WhitelistedAppDao` to access
     * DAOs methods.
     *
     * @param application is the context where The Application class in Android
     * is the base class within an Android app that contains all other
     * components such as activities and services.
     */
    public GroupRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        groupDao = db.groupDao();
        whitelistedAppDao = db.whitelistedAppDao();
    }

    //GroupDao
    public void insert(Group group) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> groupDao.insert(group)
        );
    }

    public void rename(UUID uuid, String name) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> groupDao.rename(uuid, name)
        );
    }

    public void delete(UUID groupUuid) {
        UserDatabase.databaseWriteExecutor.execute(() -> {
            groupDao.delete(groupUuid);
            whitelistedAppDao.deleteByGroupUuid(groupUuid);
        });
    }

    public LiveData<List<Group>> getAllByDevice(UUID deviceUuid) {
        return groupDao.getAllByDevice(deviceUuid);
    }

    public LiveData<GroupWithWhitelistedApps> getWithWhitelistedApps(UUID uuid) {
        return groupDao.getWithWhitelistedApps(uuid);
    }

    // WhitelistedAppDao
    public void insertWhitelistedApp(WhitelistedApp whitelistedApp) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> whitelistedAppDao.insert(whitelistedApp)
        );
    }

    public void deleteWhitelistedApp(UUID groupUuid, String packageName) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> whitelistedAppDao.delete(groupUuid, packageName)
        );
    }

    public LiveData<List<WhitelistedApp>>
    getAllWhitelistedAppsByGroupUuid(UUID groupUuid) {
        return whitelistedAppDao.getAllByGroupUuid(groupUuid);
    }

    public void clone(UUID sourceGroupUuid, String groupName) {
        UserDatabase.databaseWriteExecutor.execute(() -> {
            Group destinationGroup = new Group();
            destinationGroup.name = groupName;
            destinationGroup.deviceUuid = Objects
                                          .requireNonNull(
                                                  groupDao
                                                  .get(sourceGroupUuid)
                                                  .getValue())
                                          .deviceUuid;
            whitelistedAppDao.clone(sourceGroupUuid, destinationGroup.uuid);
            groupDao.insert(destinationGroup);
        });
    }
}
