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
    private final DetoxPeriodRepository detoxPeriodRepository;
    private final GroupRepository groupRepository;
    private final LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
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
     * @param time Time to be added/subtracted from the detox period.
     *
     * @author Mohamed Sherif
     */
    public void updateDetoxPeriod(long time) {
        final DetoxPeriod detoxPeriod = Objects.requireNonNull(
                                            detoxPeriodAndGroupWithWhitelistedApps
                                            .getValue()
                                        ).detoxPeriodAndGroup.detoxPeriod;
        detoxPeriod.endDate.setTime(detoxPeriod.endDate.getTime() + time);
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
