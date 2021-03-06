package io.github.mohamed.sallam.awb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.role.RoleManager;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class CountDownActivity extends AppCompatActivity {
    private static final String FORMAT = "%02d:%02d:%02d";
    // declaring required variables
    private Context context;
    private View mView;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private LayoutInflater layoutInflater;
    private Long durationInMillis;
    TextView countDown_textView;
    CountDownTimer cTimer = null;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ListView appsMenu;
    AppsAdapter adapter;

    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public CountDownActivity(Context context){
        this.context=context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // set the layout parameters of the window
            mParams = new WindowManager.LayoutParams(
                    // filling the screen
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    // Display it on top of other application windows
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT);
        }

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.activity_count_down, null);
        appsMenu = mView.findViewById(R.id.whitelisted_apps);
        adapter = new AppsAdapter(context, AppsAdapter.ListMode.WHITELIST_MODE);
        appsMenu.setAdapter(adapter);

        mView.findViewById(R.id.window_close).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                close();
            }
        });
        mView.findViewById(R.id.window_close).setVisibility(View.GONE); // Beta
        countDown_textView = mView.findViewById(R.id.count_down_textView);
        mParams.width = -1;
        mParams.height = -1;
        mWindowManager = (WindowManager)context.getSystemService(WINDOW_SERVICE);
    }

    public void countDownVisibility(int visible) {
        mView.setVisibility(visible);
    }


    public void open() {
        try {
            if(mView.getWindowToken()==null && durationInMillis <= 89999000 && durationInMillis >= 1) {
                if(mView.getParent()==null) {
                    mWindowManager.addView(mView, mParams);
                    preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    editor = preferences.edit();
                    cTimer = new CountDownTimer(durationInMillis, 1000) {
                        public void onTick(long millisUntilFinished) {
                            countDown_textView.setText(""+String.format(FORMAT,
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            editor.putLong("remaining_time", millisUntilFinished);
                            editor.commit();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.Q)
                        public void onFinish() {
                            close();
                        }
                    };
                    cTimer.start();
                }
            }
        } catch (Exception e) {
            Log.d("Error1",e.toString());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void close() {
        try {
            if(cTimer!=null)
                cTimer.cancel();
            durationInMillis = 0L;
            editor.putLong("remaining_time", durationInMillis);
            editor.commit();
            ((WindowManager)context.getSystemService(WINDOW_SERVICE)).removeView(mView);
            mView.invalidate();
            ((ViewGroup)mView.getParent()).removeAllViews();
        } catch (Exception e) {
            Log.d("Error2",e.toString());
        }
        while (getSystemService(RoleManager.class).isRoleHeld(RoleManager.ROLE_HOME))
            getPackageManager().clearPackagePreferredActivities(getPackageName());
    }
}