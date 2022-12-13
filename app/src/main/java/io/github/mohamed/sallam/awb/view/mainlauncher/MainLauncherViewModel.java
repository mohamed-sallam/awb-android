package io.github.mohamed.sallam.awb.view.mainlauncher;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
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
    private DetoxPeriodRepository detoxPeriodRepository;
    private final GroupRepository groupRepository;
    private LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
            detoxPeriodAndGroupWithWhitelistedApps;

    public MainLauncherViewModel(@NonNull Application application, UUID groupUuid) {
        super(application);
        detoxPeriodRepository = new DetoxPeriodRepository(application);
        groupRepository = new GroupRepository(application);
        detoxPeriodAndGroupWithWhitelistedApps = detoxPeriodRepository
                                                 .getAndGroupWithWhitelistedApps();
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
        final DetoxPeriod detoxPeriod = Objects.requireNonNull(
                                            detoxPeriodAndGroupWithWhitelistedApps
                                            .getValue()
                                        ).detoxPeriodAndGroup.detoxPeriod;
        detoxPeriod.endDate.setTime(detoxPeriod.endDate.getTime() + additionalTime);
        detoxPeriodRepository.update(detoxPeriod);
    }

    public LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getDetoxPeriodAndGroupWithWhitelistedApps() {
        return detoxPeriodAndGroupWithWhitelistedApps;
    }

    public void deleteWhitelistedApp(String packageName) {
        groupRepository.deleteWhitelistedApp(
                Objects.requireNonNull(
                        detoxPeriodAndGroupWithWhitelistedApps
                        .getValue()
                ).detoxPeriodAndGroup.group.uuid,
                packageName
        );
    }
}
