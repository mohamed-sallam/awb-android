package io.github.mohamed.sallam.awb;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
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
    private LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
            detoxPeriodAndGroupWithWhitelistedApps;
    private CountDownTimer cTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        detoxPeriodAndGroupWithWhitelistedApps =
                detoxPeriodRepository.getAndGroupWithWhitelistedApps();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent lockNotificationIntent = new Intent(
                this, MainLauncherActivity.class
        );
        PendingIntent pendingLockNotificationIntent = PendingIntent.getActivity(
                this, 0, lockNotificationIntent, 0
        );

        Notification lockNotification =
                new NotificationCompat.Builder(this, "LockChannelID")
                        .setContentTitle("Detox Period Started")
                        .setContentText("Lock Service in operation")
                        .setSmallIcon(R.drawable.ic_lock)
                        .setContentIntent(pendingLockNotificationIntent)
                        .build();

        startForeground(1,lockNotification);

        final LockService lockService = this;
        new CountDownTimer(intent.getLongExtra("duration", 0)
                                                        , 1000) {
            public void onTick(long millisUntilFinished) {
                Intent mainLauncherActivityIntent = new Intent(lockService, MainLauncherActivity.class);
                mainLauncherActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainLauncherActivityIntent.putExtra("foregroundApp", getForegroundAppPackageName());
                startActivity(mainLauncherActivityIntent);
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
        subtractedTime = -Math.abs(subtractedTime);
        final DetoxPeriod detoxPeriod = Objects.requireNonNull(
                detoxPeriodAndGroupWithWhitelistedApps
                        .getValue()
        ).detoxPeriodAndGroup.detoxPeriod;
        detoxPeriod.endDate.setTime(detoxPeriod.endDate.getTime() + subtractedTime);
        detoxPeriodRepository.update(detoxPeriod);
    }

    private String getForegroundAppPackageName() {
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