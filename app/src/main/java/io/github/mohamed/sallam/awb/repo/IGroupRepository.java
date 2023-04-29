package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;

/**
 * {@link IRepository}
 *
 * `IGroupRepository` interface is responsible for handling and declaring
 * group operations (insert - update - delete - rename - clone - getters).
 * On class `GroupRepository` we add the full logic and implementation to execute
 * a specific operation by using DAOs methods.
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public interface IGroupRepository extends IRepository<Group> {

    /**
     * Renames a specific group of whitelisted apps
     *
     * @param uuid is the unique identifier for the group,same as id.
     * @param name new name of the group.
     */
    void rename(UUID uuid, String name);

    /**
     * Gets all groups from database for a specific device.
     *
     * @param deviceUuid is the unique identifier for the device to access it
     * on database.
     *
     * @return list of groups as live data.
     */
    LiveData<List<Group>> getAllByDevice(UUID deviceUuid);

    /**
     * Inserts an application on the database, Use it when you want to select
     * an application to be whitelisted on the database while the process of
     * creating a group.
     *
     * @param whitelistedApp
     */
    void insertWhitelistedApp(WhitelistedApp whitelistedApp);

    /**
     * Deletes a specific application on a group by using the application name
     * and the group id.
     *
     * @param groupUuid the unique identifier for a group to access it in database.
     * @param packageName is the whitelisted application name.
     */
    void deleteWhitelistedApp(UUID groupUuid, String packageName);  // TODO: declared while working on RecyclerView

    /**
     * Gets all whitelisted applications for a specific group from database.
     *
     * @param groupUuid the unique identifier for a group to access it in database.
     *
     * @return list of whitelisted applications as live data.
     */
    LiveData<List<WhitelistedApp>> getAllWhitelistedAppsByGroupUuid(UUID groupUuid);

    /**
     * Clones a group with its whitelisted applications to a new group. Use it
     * when you need to make a copy of a group so we can edit it without
     * touching the original group.
     *
     * @param sourceGroupUuid the unique identifier for the original group we
     * take a copy from.
     * @param newGroupName the name of the new copied group.
     */
    void clone(UUID sourceGroupUuid, String newGroupName);

    LiveData<List<Group>> getAllForThisDevice();
}
