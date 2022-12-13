package io.github.mohamed.sallam.awb;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import io.github.mohamed.sallam.awb.view.mainlauncher.MainLauncherActivity;

/**
 * Lock Foreground Service Class
 *
 * @author Mohamed Sherif
 */
public class LockService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}