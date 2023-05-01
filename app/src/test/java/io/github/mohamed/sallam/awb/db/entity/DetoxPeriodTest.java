package io.github.mohamed.sallam.awb.db.entity;


import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;


public class DetoxPeriodTest {
    @Test
    public void setPeriod_periodProducesCorrectEndDate_returnTrue() {
        // Arrange & Act
        DetoxPeriod detoxPeriod = new DetoxPeriod();
        detoxPeriod.setPeriod(10_000L);
        Timestamp expectedEndDate = new Timestamp(new Date().getTime() + 10_000L);

        // Assert
        assertEquals(expectedEndDate, detoxPeriod.endDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPeriod_insertsNegativePeriod_throwIllegalArgumentException() {
        new DetoxPeriod().setPeriod(-10_000L);
    }

    @Test
    public void setPeriod_insertZeroPeriodEqualsCurrentTime_returnTrue(){
        // Arrange & Act
        DetoxPeriod detoxPeriod = new DetoxPeriod();
        detoxPeriod.setPeriod(0L);
        Timestamp expectedEndDate = new Timestamp(new Date().getTime());

        // Assert
        assertEquals(expectedEndDate, detoxPeriod.endDate);
    }
}
