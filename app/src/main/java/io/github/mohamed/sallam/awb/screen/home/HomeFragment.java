package io.github.mohamed.sallam.awb.screen.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.FragmentHomeBinding;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.screen.adapter.GroupAdapter;

public class HomeFragment extends Fragment implements AddGroupDialog.GroupNameDialogListener {

    private FragmentHomeBinding binding;
    private Device thisDevice;
    private HomeViewModel viewModel;
    private long duration;
    private GroupAdapter groupAdapter;

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The option menu in which place items.
     * @param inflater MenuInflater.
     *
     * @author Yousef Ahmed
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu,
                                    @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(Objects.requireNonNull(inflater),
                container, false);
        viewModel = new HomeViewModel(requireActivity().getApplication());

        binding.minutePicker.setMinValue(0);
        binding.minutePicker.setMaxValue(59);
        binding.minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                duration -= (long) oldValue * 60_000;
                duration += (long) newValue * 60_000;
            }
        });

        binding.hourPicker.setMinValue(0);
        binding.hourPicker.setMaxValue(24);
        binding.hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                duration -= (long) oldValue * 3_600_000;
                duration += (long) newValue * 3_600_000;
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        viewModel.getThisDevice().observe(getViewLifecycleOwner(), new Observer<Device>() {
            @Override
            public void onChanged(Device device) {
                if (device == null) {
                    Toast.makeText(getActivity(), "yooooooo, no device no life :(", Toast.LENGTH_SHORT).show();
                } else {
                    thisDevice = device;
                }
            }
        });
        groupAdapter = new GroupAdapter();
        binding.recyclerView.setAdapter(groupAdapter);
        registerForContextMenu(binding.recyclerView);
        viewModel.getAllDevicesWithGroups().observe(getViewLifecycleOwner(), new Observer<List<DeviceWithGroups>>() {
            @Override
            public void onChanged(List<DeviceWithGroups> deviceWithGroups) {
                groupAdapter.submitList(deviceWithGroups.get(0).groups);
            }
        });

        return binding.getRoot();
    }

    public void openDialog() {
        AddGroupDialog addGroupDialog = new AddGroupDialog();
        addGroupDialog.setListener(this);
        addGroupDialog.show(getChildFragmentManager(), "Dialog");
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        UUID groupUuid;
        try {
            groupUuid = groupAdapter.getLongClickedGroupUuid();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.renameGroupOption:
                break;
            case R.id.duplicateGroupOption:

                break;
            case R.id.editGroupOption:

                break;
            case R.id.removeGroupOption:
                viewModel.deleteGroup(groupUuid);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onSaveGroupName(String groupName) {
        viewModel.insertGroup(groupName, thisDevice.uuid);
    }
}