package io.github.mohamed.sallam.awb.view.updategroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UpdateGroupViewModel extends AndroidViewModel{

    // GroupRepository constructor
    GroupRepository grouprepository;
    public UpdateGroupViewModel(@NonNull Application application) {
        super(application);
        grouprepository = new GroupRepository(application);
    }

    //GroupRepository methods
    public void insert(Group group) {
        grouprepository.insert(group);
    }

    public void rename(String name, UUID uuid) {
        grouprepository.rename(name, uuid);
    }

    public void delete(UUID groupUuid) {
        grouprepository.delete(groupUuid);
    }

    public LiveData<List<Group>> getAll(UUID deviceUuid) {
        return grouprepository.getAll(deviceUuid);
    }

    public LiveData<List<GroupWithBlockedApps>>
    getAllWithBlockedApps(UUID deviceUuid) {
        return grouprepository.getAllWithBlockedApps(deviceUuid);
    }

    public void insertBlockedApp(BlockedApp blockedApp) {
        grouprepository.insertBlockedApp(blockedApp);
    }

    public void deleteBlockedApp(BlockedApp blockedApp) {
        grouprepository.deleteBlockedApp(blockedApp);
    }

    public LiveData<List<BlockedApp>>
    getAllBlockedApps(UUID deviceUuid, UUID groupUuid) {
        return grouprepository.getAllBlockedApps(deviceUuid,groupUuid);
    }

    public void clone (UUID sourceGroupUuid, String groupName) {
        grouprepository.clone(sourceGroupUuid,groupName);
    }
}
