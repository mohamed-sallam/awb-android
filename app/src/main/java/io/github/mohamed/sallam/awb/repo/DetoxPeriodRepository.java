package io.github.mohamed.sallam.awb.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithBlockedApps;

public class DetoxPeriodRepository implements IDetoxPeriodRepository { 
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

    public void delete(int id) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.delete(id);
            }
        });
    }

    public LiveData<DetoxPeriod> get(int id) {
        return detoxPeriodDao.get(id);
    }

    public LiveData<DetoxPeriodAndGroupWithBlockedApps>
    getDetoxPeriodAndGroupWithBlockedApps(int id) {
        return detoxPeriodDao.getWithGroup(id);
    }


}
