package io.github.mohamed.sallam.awb.screen.mainlauncher;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.github.mohamed.sallam.awb.LockService;
import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;
import io.github.mohamed.sallam.awb.screen.home.HomeViewModel;


/**
 * @author Yousef Ahmed
 * @author Mohamed Sallam
 */
public class MainLauncherActivity extends AppCompatActivity {
    private MainLauncherViewModel viewModel;
    private TextView countDownTextView;
    private List<WhitelistedApp> whitelistedApps;
    private Timestamp endDate;
    private boolean isCountDownStarted = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_launcher);
        viewModel = new ViewModelProvider(this).get(MainLauncherViewModel.class);
        viewModel.getWhitelistedApps().observe(this, new Observer<List<WhitelistedApp>>() {
            @Override
            public void onChanged(List<WhitelistedApp> newWhitelistedApps) {
                whitelistedApps = newWhitelistedApps;
            }
        });
        whitelistedApps = new ArrayList<WhitelistedApp>();
        viewModel.getDetoxPeriod().observe(this,
                new Observer<DetoxPeriod>() {
            @Override
            public void onChanged(DetoxPeriod detoxPeriod) {
                endDate = detoxPeriod.endDate;
            }
        });
        countDownTextView = findViewById(R.id.countDownTextView);
    }

    boolean mBounded;
    LockService mServer;

    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, LockService.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Sosa", "Service is disconnected");
            mBounded = false;
            mServer = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("Sosa", "Service is connected");
            mBounded = true;
            LockService.LocalBinder mLocalBinder = (LockService.LocalBinder)service;
            mServer = mLocalBinder.getServerInstance();
            if (!isCountDownStarted) {
                openCountDown();
                isCountDownStarted = true;
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    };

    void openCountDown() {
        new CountDownTimer(endDate.getTime() - new Date().getTime(), 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                setVisible(whitelistedApps.contains(mServer.getForegroundAppPackageName()));
                countDownTextView.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                this.cancel();
            }
        }.start();
    }
}