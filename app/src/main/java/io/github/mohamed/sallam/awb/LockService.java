package io.github.mohamed.sallam.awb;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.repo.DetoxPeriodRepositoryImpl;
import io.github.mohamed.sallam.awb.screen.adapter.WhitelistedAppAdapter;

/**
 * Lock Foreground Service Class
 *
 * @author Mohamed Sherif
 * @author Yousef Ahmed
 * @author Mohamed Sallam
 */
public class LockService extends Service {
    private final DetoxPeriodRepositoryImpl detoxPeriodRepository;
    private final WindowManager.LayoutParams mainLauncherParameters;
    private final List<App> whitelistedApps;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LockService() {
        detoxPeriodRepository = new DetoxPeriodRepositoryImpl(getApplication());
        mainLauncherParameters = new WindowManager.LayoutParams(
                            -1,
                            -1,
                            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                            PixelFormat.TRANSLUCENT
        );
        whitelistedApps = new ArrayList<App>();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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

    // https://stackoverflow.com/questions/63899503/how-to-observe-a-live-data-inside-service-class
    @SuppressLint("InflateParams")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        WhitelistedAppAdapter whitelistedAppAdapter = new WhitelistedAppAdapter(getApplicationContext());
        detoxPeriodRepository.getWhitelistedAppsOfCurrentDetoxPeriod().observeForever(newWhitelistedApps -> {
            whitelistedApps.clear();
            for (WhitelistedApp whitelistedApp : newWhitelistedApps) {
                App app = null;
                try {
                    app = new App(whitelistedApp.packageName,
                            getAppLabel(getPackageManager(), whitelistedApp.packageName),
                            getApplication().getPackageManager().getApplicationIcon(whitelistedApp.packageName));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                whitelistedApps.add(app);
            }
            whitelistedAppAdapter.submitList(whitelistedApps);
        });

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout overlayLayout = (RelativeLayout) layoutInflater.inflate(R.layout.layout_main_launcher, null, false);
        windowManager.addView(overlayLayout, mainLauncherParameters);

        TextView countDownTextView = overlayLayout.findViewById(R.id.countDownTextView);
        RecyclerView whitelistedAppsRecyclerView = overlayLayout.findViewById(R.id.whitelistedAppsRecyclerView);
        whitelistedAppsRecyclerView.setAdapter(whitelistedAppAdapter);

        new CountDownTimer(intent.getLongExtra("duration", 0)
                , 1000) {
            public void onTick(long millisUntilFinished) {
                for (App whitelistedApp: whitelistedApps) {
                    if (whitelistedApp.getPackageName().equals(getForegroundAppPackageName())) {
                        overlayLayout.setVisibility(View.GONE);
                        break;
                    }
                    overlayLayout.setVisibility(View.VISIBLE);
                }
                countDownTextView.setText(String.format(Locale.getDefault(),
                                   "%02d:%02d:%02d",
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                          TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                          TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                          TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                          TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                this.cancel();
                overlayLayout.removeAllViewsInLayout();
                overlayLayout.setVisibility(View.GONE);
                stopSelf();
            }
        }.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getForegroundAppPackageName() {
        String currentAppPackageName = "Unknown";
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usageStatsManager =
                    (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
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
                    (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningTasks =
                    activityManager.getRunningAppProcesses();
            currentAppPackageName = runningTasks.get(0).processName;
        }

        return currentAppPackageName;
    }

    // Source: https://stackoverflow.com/a/36590750
    private String getAppLabel(PackageManager packageManager, String packageName) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo( packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "(unknown)");
    }
}