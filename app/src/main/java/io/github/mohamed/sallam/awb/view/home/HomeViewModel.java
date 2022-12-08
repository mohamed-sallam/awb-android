package io.github.mohamed.sallam.awb.view.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

//extend AndroidViewModel as video to pass context "application"
public class HomeViewModel extends AndroidViewModel {

    //DeviceRepository Constructor
    DeviceRepository devicerepository;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        devicerepository = new DeviceRepository(application);
    }

    // DeviceRepository methods
    public LiveData<List<DeviceWithGroups>> getAllDevicesWithGroups() {
        return devicerepository.getAllDevicesWithGroups();
    }

    //DetoxRepository Constructor
    DetoxPeriodRepository detoxperiodrepository;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        detoxperiodrepository = new DetoxPeriodRepository(application);
    }

    //DetoxRepository method
    public void insert(DetoxPeriod detoxPeriod) {
        detoxperiodrepository.insert(detoxPeriod);
    }

    public void update(DetoxPeriod detoxPeriod) {
        detoxperiodrepository.update(detoxPeriod);
    }

    public void delete(int id) {
        detoxperiodrepository.delete(id);
    }

    public LiveData<DetoxPeriod> get(int id) {
        return detoxperiodrepository.get(id);
    }

    public LiveData<DetoxPeriodAndGroupWithBlockedApps>
    getDetoxPeriodAndGroupWithBlockedApps(int id) {
        return detoxperiodrepository.getDetoxPeriodAndGroupWithBlockedApps(id);
    }


}
