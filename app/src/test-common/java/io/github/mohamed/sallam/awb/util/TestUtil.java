package io.github.mohamed.sallam.awb.util;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Group;

public class TestUtil {
    public static final UUID TEST_UUID_1 = UUID.randomUUID();
    public static final UUID TEST_UUID_2 = UUID.randomUUID();

    public static final UUID TEST_UUID_1_DEVICE = UUID.randomUUID();
    public static final Group TEST_GROUP_1 = new Group(TEST_UUID_1, "group1", TEST_UUID_1_DEVICE);

    public static final UUID TEST_UUID_3 = UUID.randomUUID();
    public static final UUID TEST_UUID_4 = UUID.randomUUID();

    public static final Group TEST_GROUP_2 = new Group(TEST_UUID_3, "group2", TEST_UUID_4);
    public static final List<Group> TEST_GROUPS_LIST = List.of(TEST_GROUP_1, TEST_GROUP_2);

    public static final Device TEST_DEVICE_1 = new Device(TEST_UUID_1, "device1", "ANDROID", "SecretKeyTest");
    public static final Device TEST_DEVICE_2 = new Device(TEST_UUID_1, "device2", "ANDROID", "SecretKeyTest");

    public static final List<Device> TEST_DEVICE_LIST = List.of(TEST_DEVICE_1, TEST_DEVICE_2);

    public static final DetoxPeriod TEST_DETOX_PERIOD_1 = new DetoxPeriod(10, TEST_UUID_2, 10_000L);
}
