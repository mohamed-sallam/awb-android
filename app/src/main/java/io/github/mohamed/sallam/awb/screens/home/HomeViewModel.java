package io.github.mohamed.sallam.awb.screens.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.github.mohamed.sallam.awb.Group;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Group>> groups;

    public MutableLiveData<List<Group>> getGroups() {
        return groups;
    }

    public void setGroups(MutableLiveData<List<Group>> groups) {
        this.groups = groups;
    }
}