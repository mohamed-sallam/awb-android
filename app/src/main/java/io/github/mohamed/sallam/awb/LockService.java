package io.github.mohamed.sallam.awb;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;
import io.github.mohamed.sallam.awb.repo.DetoxPeriodRepository;
import io.github.mohamed.sallam.awb.screen.mainlauncher.MainLauncherActivity;

/**
 * Lock Foreground Service Class
 *
 * @author Mohamed Sherif
 * @author Yousef Ahmed
 * @author Mohamed Sallam
 */
public class LockService extends Service {
    private DetoxPeriodRepository detoxPeriodRepository;
    private CountDownTimer cTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    IBinder mBinder = new LocalBinder();
    public class LocalBinder extends Binder {
        public LockService getServerInstance() {
            return LockService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String NOTIFICATION_CHANNEL_ID = "LockChannelID";
            String channelName = "Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_MIN);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(chan);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            Notification notification = notificationBuilder.setOngoing(true)
                    .setContentTitle("Detox Period Started")
                    .setContentText("Lock Service in operation")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
            startForeground(2, notification);
        } else {
            startForeground(1, new Notification());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Intent mainLauncherActivityIntent = new Intent(this, MainLauncherActivity.class);
        mainLauncherActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainLauncherActivityIntent);


        new CountDownTimer(intent.getLongExtra("duration", 0)
                                                        , 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                this.cancel();
                stopSelf();
            }
        }.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void decreaseDetoxPeriod(long subtractedTime) {
//        subtractedTime = -Math.abs(subtractedTime);
//        final DetoxPeriod detoxPeriod = Objects.requireNonNull(
//                detoxPeriodAndGroupWithWhitelistedApps
//                        .getValue()
//        ).detoxPeriodAndGroup.detoxPeriod;
//        detoxPeriod.endDate.setTime(detoxPeriod.endDate.getTime() + subtractedTime);
//        detoxPeriodRepository.update(detoxPeriod);
    }

    public String getForegroundAppPackageName() {
        String currentAppPackageName = "Unknown";
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usageStatsManager =
                    (UsageStatsManager)this.getSystemService(Context.USAGE_STATS_SERVICE);
            long currentTime = System.currentTimeMillis();
            List<UsageStats> apps =
                    usageStatsManager.queryUsageStats(
                            UsageStatsManager.INTERVAL_DAILY,
                            currentTime - 1000 * 1000,
                            currentTime
                    );
            if (apps != null && apps.size() > 0) {
                SortedMap<Long, UsageStats> mySortedMap =
                        new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : apps) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    currentAppPackageName =
                            mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        } else {
            ActivityManager activityManager =
                    (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningTasks =
                    activityManager.getRunningAppProcesses();
            currentAppPackageName = runningTasks.get(0).processName;
        }

        Log.e("adapter", "Current App in foreground is: " + currentAppPackageName);
        return currentAppPackageName;
    }
}