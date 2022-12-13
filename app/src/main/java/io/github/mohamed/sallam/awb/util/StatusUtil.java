package io.github.mohamed.sallam.awb.util;

import static android.app.AppOpsManager.MODE_ALLOWED;
import static android.app.AppOpsManager.OPSTR_GET_USAGE_STATS;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;

public class StatusUtil {
    public static boolean isUsageStatGranted(Context context) {
        AppOpsManager appOps = (AppOpsManager) context
                .getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                context.getPackageName());
        if (mode == AppOpsManager.MODE_DEFAULT) {
            return (
                    context.checkCallingOrSelfPermission(
                            android.Manifest.permission.PACKAGE_USAGE_STATS
                    ) == PackageManager.PERMISSION_GRANTED
            );
        } else {
            return (mode == MODE_ALLOWED);
        }
    }
}
