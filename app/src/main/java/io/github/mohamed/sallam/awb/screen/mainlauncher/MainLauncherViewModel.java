package io.github.mohamed.sallam.awb.screen.mainlauncher;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;
import io.github.mohamed.sallam.awb.repo.DetoxPeriodRepository;
import io.github.mohamed.sallam.awb.repo.GroupRepository;

/**
 * MainLauncherActivity ViewModel.
 *
 * @author Mohamed Sallam
 * @author Mohamed Yehia
 */
public class MainLauncherViewModel extends AndroidViewModel {
    private final DetoxPeriodRepository detoxPeriodRepository;
    private final GroupRepository groupRepository;
    private final LiveData<DetoxPeriod> detoxPeriod;
    private final LiveData<List<WhitelistedApp>> whitelistedApps;

    public MainLauncherViewModel(@NonNull Application application) {
        super(application);
        detoxPeriodRepository = new DetoxPeriodRepository(application);
        groupRepository = new GroupRepository(application);
        detoxPeriod = detoxPeriodRepository.get();
        whitelistedApps = detoxPeriodRepository.getWhitelistedAppsOfCurrentDetoxPeriod();
    }

    /**
     * Updates `DetoxPeriod` by increasing/decreasing the period.
     *
     * @param additionalTime Time to be added from the detox period.
     *
     * @author Mohamed Sherif
     */
    public void increaseDetoxPeriod(long additionalTime) {
        additionalTime = Math.abs(additionalTime);

        Objects.requireNonNull(detoxPeriod.getValue()).endDate.setTime(detoxPeriod.getValue().endDate.getTime() + additionalTime);
        detoxPeriodRepository.update(detoxPeriod.getValue());
    }

    public void deleteWhitelistedApp(String packageName) {
        groupRepository.deleteWhitelistedApp(
                Objects.requireNonNull(
                        detoxPeriod
                        .getValue()
                ).groupUuid,
                packageName
        );
    }

    public LiveData<DetoxPeriod> getDetoxPeriod() {
        return detoxPeriod;
    }

    public LiveData<List<WhitelistedApp>> getWhitelistedApps() {
        return whitelistedApps;
    }
}
