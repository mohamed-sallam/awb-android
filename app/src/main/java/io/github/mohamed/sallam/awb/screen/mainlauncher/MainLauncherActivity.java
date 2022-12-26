package io.github.mohamed.sallam.awb.screen.mainlauncher;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;


/**
 * @author Yousef Ahmed
 * @author Mohamed Sallam
 */
public class MainLauncherActivity extends AppCompatActivity {
    private MainLauncherViewModel viewModel;
    private TextView countDownTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_launcher);
        getIntent().getStringExtra("foregroundApp");
        final Timestamp[] endDate = new Timestamp[1];
        viewModel.getDetoxPeriodAndGroupWithWhitelistedApps().observe(this,
                new Observer<DetoxPeriodAndGroupWithWhitelistedApps>() {
            @Override
            public void onChanged(DetoxPeriodAndGroupWithWhitelistedApps detoxPeriodAndGroupWithWhitelistedApps) {
                endDate[0] = detoxPeriodAndGroupWithWhitelistedApps.detoxPeriodAndGroup.detoxPeriod.endDate;
                setVisible(detoxPeriodAndGroupWithWhitelistedApps.whitelistedApps
                                                      .contains(getIntent()
                                                                .getStringExtra(
                                                                        "foregroundApp"
                                                                )
                                                      )
                );
            }
        });
        countDownTextView = findViewById(R.id.countDownTextView);
        new CountDownTimer(endDate[0].getTime() - new Date().getTime(), 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}