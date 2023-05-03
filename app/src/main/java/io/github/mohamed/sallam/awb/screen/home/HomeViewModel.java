package io.github.mohamed.sallam.awb.screen.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.repo.DetoxPeriodRepository;
import io.github.mohamed.sallam.awb.repo.DeviceRepository;
import io.github.mohamed.sallam.awb.repo.GroupRepository;

/**
 * Home fragment ViewModel is responsible for preparing, holding and managing
 * the data used in Home fragment.
 * ViewModel uses Command Pattern to queue applications blocking and whitelisting
 * till user saves these command by clicking <save> or discard them by clicking
 * <cancel>.
 *
 * @author Mohamed Sallam
 * @author Yousef Ahmed
 */
public class HomeViewModel extends AndroidViewModel {
    private DeviceRepository      deviceRepository;
    private GroupRepository       groupRepository;
    private DetoxPeriodRepository detoxPeriodRepository;
    private LiveData<Device> thisDevice;

    /**
     * Instantiation of required repositories in order to access the
     * required methods to hande the data used in `HomeFragment`.
     *
     * @param application is the context where the `Application` class in Android
     * is the base class within an Android app that contains all other.
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);
        deviceRepository = new DeviceRepository(application);
        groupRepository = new GroupRepository(application);
        detoxPeriodRepository = new DetoxPeriodRepository(application);
        thisDevice = deviceRepository.getThisDevice();
    }

    public LiveData<List<Group>> getAllGroupsForThisDevice() {
        return groupRepository.getAllForThisDevice();
    }

    /**
     * Inserts a group of whitelisted applications in the database.
     *
     * @param groupName name of the group
     * @param deviceUuid device unique identifier for the group we want insert.
     */
    public void insertGroup(String groupName, UUID deviceUuid) {
        Group group = new Group();
        group.name = groupName;
        group.deviceUuid = deviceUuid;
        groupRepository.insert(group);
    }

    /**
     * Renames a specific group of whitelisted applications.
     *
     * @param groupUuid is the unique identifier for the group,same as id.
     * @param groupName new name of the group.
     */
    public void renameGroup(UUID groupUuid, String groupName) {
        groupRepository.rename(groupUuid, groupName);
    }

    /**
     * Deletes a specific group from group table.
     *
     * @param groupUuid the unique identifier for the group to access it on.
     * group table
     */
    public void deleteGroup(UUID groupUuid) {
        groupRepository.delete(groupUuid);
    }

    /**
     * Inserts a blocking period for a specific group of white listed applications.
     *
     * @param period the time of blocking
     * @param groupUuid the unique identifier for the group to access on database.
     */
    public void insertDetoxPeriod(long period, UUID groupUuid) {
        DetoxPeriod detoxPeriod = new DetoxPeriod();
        detoxPeriod.setPeriod(period);
        detoxPeriod.groupUuid = groupUuid;
        detoxPeriodRepository.insert(detoxPeriod);
    }

//    /**
//     * Copy a group with its whitelisted applications to a new group. Use it
//     * when you need to make a copy of a group so we can edit it without
//     * touching the original group.
//     *
//     * @param groupUuid the unique identifier for the group to access the
//     * group we want to copy from
//     * @param newGroupName the new group name, the copied one.
//     */
//    public void duplicateGroup(UUID groupUuid, String newGroupName) {
//        groupRepository.clone(groupUuid, newGroupName);
//    }

    public LiveData<Device> getThisDevice() {
        return thisDevice;
    }
}
