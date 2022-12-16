package io.github.mohamed.sallam.awb;

import android.graphics.drawable.Drawable;

public class App {
    private String appName;
    private final String packageName;
    private final Drawable icon;
    private boolean isSelected;

    public App(String packageName, Drawable icon) {
        this(packageName, icon, false);
    }

    public App(String packageName, Drawable icon, boolean isSelected) {
        this.packageName = packageName;
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof App) {
            App anotherApp = (App) o;
            return this.appName.equals(anotherApp.getAppName()) &&
                   this.icon.equals(anotherApp.icon) &&
                   packageName.equals(anotherApp.getAppName()) ;
        }
        return false;
    }
}
