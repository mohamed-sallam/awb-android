package io.github.mohamed.sallam.awb.view.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;
import io.github.mohamed.sallam.awb.repo.DetoxPeriodRepository;
import io.github.mohamed.sallam.awb.repo.DeviceRepository;
import io.github.mohamed.sallam.awb.repo.GroupRepository;

/**
 * HomeFragment ViewModel
 *
 * @author Mohamed Sallam
 * @author Yousef Ahmed
 */
public class HomeViewModel extends AndroidViewModel {
    private DeviceRepository      deviceRepository;
    private GroupRepository       groupRepository;
    private DetoxPeriodRepository detoxPeriodRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        deviceRepository = new DeviceRepository(application);
        groupRepository = new GroupRepository(application);
        detoxPeriodRepository = new DetoxPeriodRepository(application);
    }

    public LiveData<List<DeviceWithGroups>> getAllDevicesWithGroups() {
        return deviceRepository.getAllWithGroups();
    }

    public void insertGroup(String groupName, UUID deviceUuid) {
        Group group = new Group();
        group.name = groupName;
        group.deviceUuid = deviceUuid;
        groupRepository.insert(group);
    }

    public void renameGroup(UUID groupUuid, String groupName) {
        groupRepository.rename(groupUuid, groupName);
    }

    public void deleteGroup(UUID groupUuid) {
        groupRepository.delete(groupUuid);
    }

    public void insertDetoxPeriod(long period, UUID groupUuid) {
        DetoxPeriod detoxPeriod = new DetoxPeriod();
        detoxPeriod.setPeriod(period);
        detoxPeriod.groupUuid = groupUuid;
        detoxPeriodRepository.insert(detoxPeriod);
    }

    public void duplicateGroup(UUID groupUuid, String newGroupName) {
        groupRepository.clone(groupUuid, newGroupName);
    }
}
