package io.github.mohamed.sallam.awb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lock_table")
public class DataEntities {
    @PrimaryKey(autoGenerate = true)
    private int secretKey;

    private String device;           //table fields
    private String group;
    private String blockedEntity;
    private String detoxPeriod;

    public DataEntities(String device, String group, String blockedEntity, String detoxPeriod) {
        //primary key is not selected in constructor
        this.device = device;
        this.group = group;
        this.blockedEntity = blockedEntity;
        this.detoxPeriod = detoxPeriod;
    }

    //room will use this method to denote secretkey
    public void setSecretKey(int secretKey) {
        this.secretKey = secretKey;
    }

    //getter methods
    public int getSecretKey() {
        return secretKey;
    }

    public String getDevice() {
        return Device;
    }

    public String getGroup() {
        return Group;
    }

    public String getBlockedEntity() {
        return blockedEntity;
    }

    public String getDetoxPeriod() {
        return detoxPeriod;
    }
}


