package io.github.mohamed.sallam.awb.screen.updategroup;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithWhitelistedApps;
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
    private final Application application;
    private final List<App> apps;
    MutableLiveData<Boolean> navigateBack = new MutableLiveData<Boolean>(false);

    public UpdateGroupViewModel(@NonNull Application application, UUID groupUuid) {
        super(application);
        this.application = application;
        this.groupUuid = groupUuid;
        groupRepository = new GroupRepository(application);
        appCommands = new LinkedHashMap<>();
        apps = getAllApps();
    }

    public void resetNavigation() {
        navigateBack = null;
    }

    public LiveData<GroupWithWhitelistedApps> getGroupWithWhitelistedApps() {
        return groupRepository.getWithWhitelistedApps(groupUuid);
    }

    public List<App> getAllApps() {
        @SuppressLint("QueryPermissionsNeeded")
        List<ApplicationInfo> appsData = application.getPackageManager()
                                                    .getInstalledApplications(
                                                            PackageManager
                                                            .GET_META_DATA
                                                    );
        List<App> apps = new ArrayList<>();
        for (ApplicationInfo appInfo : appsData) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
                App app = new App(appInfo.packageName,
                        application.getPackageManager().getApplicationIcon(appInfo));
                for (WhitelistedApp whitelistedApp: Objects.requireNonNull(
                        getGroupWithWhitelistedApps().getValue()).whitelistedApps) {
                    if (whitelistedApp.packageName.equals(app.getPackageName())) {
                        app.setSelected(true);
                        break;
                    }
                }
                apps.add(app);
            }
        }
        return apps;
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
        for (AppCommand appCommand : appCommands.values())
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
