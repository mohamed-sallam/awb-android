package io.github.mohamed.sallam.awb.view.updategroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithWhitelistedApps;
import io.github.mohamed.sallam.awb.repo.GroupRepository;

/**
 * UpdateGroupFragment ViewModel. It uses Command Pattern to queue applications
 * blocking and whitelisting til user save these command by clicking <save> or
 * discard them by clicking <cancel>.
 *
 * @see <a href =”https://refactoring.guru/design-patterns/command”>Command</a>
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

    public LiveData<GroupWithWhitelistedApps> getGroupWithWhitelistedApps() {
        return groupRepository.getWithWhitelistedApps(groupUuid);
    }

    public void allowApp(String packageName) {
        AppCommand command = appCommands.get(packageName);
        if (command == null) {
            appCommands.put(packageName, new AppCommand(packageName, true) {
                @Override
                public void execute() {
                    WhitelistedApp whitelistedApp = new WhitelistedApp();
                    whitelistedApp.groupUuid = groupUuid;
                    whitelistedApp.packageName = packageName;
                    groupRepository.insertWhitelistedApp(whitelistedApp);
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
                    groupRepository.deleteWhitelistedApp(groupUuid, packageName);
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
