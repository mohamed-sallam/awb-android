package io.github.mohamed.sallam.awb.screens.updategroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UpdateGroupViewModel extends ViewModel {
    private MutableLiveData<List<String>> app;

    public MutableLiveData<List<String>> getApp() {
        return app;
    }

    public void setApp(MutableLiveData<List<String>> app) {
        this.app = app;
    }
}
