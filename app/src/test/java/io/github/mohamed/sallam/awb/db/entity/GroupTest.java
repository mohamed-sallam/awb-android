package io.github.mohamed.sallam.awb.db.entity;


import static org.junit.Assert.*;
import static io.github.mohamed.sallam.awb.util.TestUtil.TEST_GROUP_1;


import org.junit.Test;

import java.util.UUID;

public class GroupTest {
    @Test
    public void equals_identicalProperties_returnTrue() {
        // Arrange
        Group group1 = new Group(TEST_GROUP_1);
        Group group2 = new Group(TEST_GROUP_1);

        // Act & Assert
        assertEquals(group1, group2);
    }

    @Test
    public void equals_differentTypeObject_returnFalse() {
        // Arrange
        Group group = new Group(TEST_GROUP_1);

        // Act & Assert
        assertNotEquals(group, new Object());
        assertNotEquals(group, null);
    }

    @Test
    public void equals_differentProperties_returnFalse() {
        // Arrange
        Group group1 = new Group(TEST_GROUP_1);
        Group group2 = new Group(TEST_GROUP_1);
        group2.name = "group2";
        Group group3 = new Group(TEST_GROUP_1);
        group3.deviceUuid = UUID.randomUUID();
        Group group4 = new Group(TEST_GROUP_1);
        group4.uuid = UUID.randomUUID();

        // Act & Assert
        assertNotEquals(group1, group2);
        assertNotEquals(group1, group3);
        assertNotEquals(group1, group4);
    }
}