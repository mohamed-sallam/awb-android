package io.github.mohamed.sallam.awb.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;

import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;

import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;

public class DetoxPeriodRepository { //generic irepo.. // 3 x irep.... extend irepo
    private DetoxPeriodDao detoxPeriodDao;

    public DetoxPeriodRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        detoxPeriodDao = db.detoxPeriodDao();
    }

    // DetoxPeriodDao
    public void insert(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.insert(detoxPeriod);
            }
        });
    }

    public void update(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.update(detoxPeriod);
            }
        });
    }

    public void delete(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.delete(detoxPeriod);
            }
        });
    }

    public LiveData<DetoxPeriod> get(int id) {
        return detoxPeriodDao.get(id);
    }

    public LiveData<DetoxPeriodAndGroup> getDetoxPeriodWithGroup(int id) {
        return detoxPeriodDao.getWithGroup(id);
    }
}
