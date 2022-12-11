package io.github.mohamed.sallam.awb.view.updategroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
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
    private final Map<String, AppCommand> appCommands;
    private final UUID groupUuid;

    public UpdateGroupViewModel(@NonNull Application application, UUID groupUuid) {
        super(application);
        groupRepository = new GroupRepository(application);
        appCommands = new LinkedHashMap<>();
        this.groupUuid = groupUuid;
    }

    public LiveData<GroupWithBlockedApps> getGroupWithBlockedApps() {
        return groupRepository.getWithBlockedApps(groupUuid);
    }

    public void allowApp(String packageName) {
        AppCommand command = appCommands.get(packageName);
        if (command == null) {
            appCommands.put(packageName, new AppCommand(packageName, true) {
                @Override
                public void execute() {
                    BlockedApp blockedApp = new BlockedApp();
                    blockedApp.groupUuid = groupUuid;
                    blockedApp.packageName = packageName;
                    groupRepository.insertBlockedApp(blockedApp);
                }
            });
            return;
        }
        if (!command.isAllowCommand)
            appCommands.remove(packageName);
    }

    public void blockApp(String packageName) {
        AppCommand command = appCommands.get(packageName);
        if (command == null) {
            appCommands.put(packageName, new AppCommand(packageName, false) {
                @Override
                void execute() {
                    groupRepository.deleteBlockedApp(groupUuid, packageName);
                }
            });
            return;
        }
        if (command.isAllowCommand)
            appCommands.remove(packageName);
    }

    public void save() {
        for (AppCommand appCommand: appCommands.values())
            appCommand.execute();
    }

    private abstract class AppCommand {
        protected String packageName;
        protected Boolean isAllowCommand;

        public AppCommand(String packageName, Boolean isAllowCommand) {
            this.packageName = packageName;
            this.isAllowCommand = isAllowCommand;
        }

        abstract void execute();
    }
}
