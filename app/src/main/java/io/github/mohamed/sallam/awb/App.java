package io.github.mohamed.sallam.awb;

import android.graphics.drawable.Drawable;

public class App {
    private String appName;
    private final String packageName;
    private final Drawable icon;
    private boolean isSelected;

    public App(String packageName, String appName, Drawable icon) {
        this(packageName, appName, icon, false);
    }

    public App(String packageName, String appName, Drawable icon, boolean isSelected) {
        this.packageName = packageName;
        this.appName = appName;
        this.icon = icon;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPackageName() {
        return packageName;
    }
}
