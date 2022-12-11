package io.github.mohamed.sallam.awb.view.updategroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;
import io.github.mohamed.sallam.awb.repo.GroupRepository;

/**
 * UpdateGroupFragment ViewModel. It uses Command Pattern to queue applications
 * blocking and whitelisting til user save these command by clicking <save> or
 * discard them by clicking <cancel>.
 * Source: refactoring.guru/design-patterns/command
 *
 * @author Mohamed Sallam
 */
public class UpdateGroupViewModel extends AndroidViewModel {
    private final GroupRepository groupRepository;
    private final List<AppCommand> appCommands;
    private final UUID groupUuid;

    public UpdateGroupViewModel(@NonNull Application application, UUID groupUuid) {
        super(application);
        groupRepository = new GroupRepository(application);
        appCommands = new ArrayList<>();
        this.groupUuid = groupUuid;
    }

    public LiveData<GroupWithBlockedApps> getGroupWithBlockedApps() {
        return groupRepository.getWithBlockedApps(groupUuid);
    }

    public void allowApp(String packageName) {
        appCommands.add(new AppCommand(packageName) {
            @Override
            public void execute() {
                BlockedApp blockedApp = new BlockedApp();
                blockedApp.groupUuid = groupUuid;
                blockedApp.packageName = packageName;
                groupRepository.insertBlockedApp(blockedApp);
            }
        });
    }

    public void blockApp(String packageName) {
        appCommands.add(new AppCommand(packageName) {
            @Override
            void execute() {
                groupRepository.deleteBlockedApp(groupUuid, packageName);
            }
        });
    }

    public void save() {
        for (AppCommand appCommand: appCommands)
            appCommand.execute();
    }

    private abstract class AppCommand {
        protected String packageName;

        public AppCommand(String packageName) {
            this.packageName = packageName;
        }

        abstract void execute();
    }
}
