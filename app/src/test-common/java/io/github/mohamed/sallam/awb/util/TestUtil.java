package io.github.mohamed.sallam.awb.util;

import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.Group;

public class TestUtil {
    public static final UUID TEST_UUID_1 = UUID.randomUUID();
    public static final UUID TEST_UUID_2 = UUID.randomUUID();
    public static final Group TEST_GROUP_1 = new Group(TEST_UUID_1, "group1", TEST_UUID_2);

    public static final UUID TEST_UUID_3 = UUID.randomUUID();
    public static final UUID TEST_UUID_4 = UUID.randomUUID();

    public static final Group TEST_GROUP_2 = new Group(TEST_UUID_3, "group2", TEST_UUID_4);
}