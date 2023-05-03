package io.github.mohamed.sallam.awb.util;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;

public class TestUtil {
    public static final UUID TEST_UUID_1 = UUID.randomUUID();
    public static final UUID TEST_UUID_2 = UUID.randomUUID();

    public static final UUID TEST_UUID_1_DEVICE = UUID.randomUUID();
    public static final Device TEST_DEVICE_1 = new Device(
            UUID.randomUUID(), "Device 1", true,
            "Android", Device.Os.ANDROID,
            "192.168.1.100", "secret1"
    );
    public static final Device TEST_DEVICE_2 = new Device(
            UUID.randomUUID(), "Device 2", false,
            "Windows", Device.Os.WINDOWS,
            "192.168.1.101", "secret2"
    );
    public static final Group TEST_GROUP_1 = new Group(TEST_UUID_1, "group1", TEST_UUID_1_DEVICE);

    public static final UUID TEST_UUID_3 = UUID.randomUUID();
    public static final UUID TEST_UUID_4 = UUID.randomUUID();

    public static final Group TEST_GROUP_2 = new Group(TEST_UUID_3, "group2", TEST_UUID_4);
    public static final List<Group> TEST_GROUPS_LIST = List.of(TEST_GROUP_1, TEST_GROUP_2);
    public static final WhitelistedApp TEST_WHITELISTED_APP_1 = new WhitelistedApp(123, "app1", TEST_UUID_3);
    public static final WhitelistedApp TEST_WHITELISTED_APP_2 = new WhitelistedApp(456, "app2", TEST_UUID_3);
    public static final WhitelistedApp TEST_WHITELISTED_APP_3 = new WhitelistedApp(789, "app3", TEST_UUID_3);
    public static final List<WhitelistedApp> TEST_WHITELISTED_APPS_LIST = List.of(TEST_WHITELISTED_APP_1, TEST_WHITELISTED_APP_2, TEST_WHITELISTED_APP_3);

    public static final DetoxPeriod TEST_DETOX_PERIOD_1 = new DetoxPeriod(10, TEST_UUID_2, 10_000L);
}
