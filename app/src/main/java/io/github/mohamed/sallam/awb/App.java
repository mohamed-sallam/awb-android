package io.github.mohamed.sallam.awb;

import android.graphics.drawable.Drawable;

public class App {
    private final String name;
    private final Drawable icon;
    private boolean isSelected;

    public App(String name, Drawable icon) {
        this(name, icon, false);
    }

    public App(String name, Drawable icon, boolean isSelected) {
        this.name = name;
        this.icon = icon;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof App) {
            App anotherApp = (App) o;
            return this.name.equals(anotherApp.getName()) && this.icon.equals(anotherApp.icon);
        }
        return false;
    }
}
