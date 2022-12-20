package io.github.mohamed.sallam.awb.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import java.util.Objects;

import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.FragmentHomeBinding;
import io.github.mohamed.sallam.awb.db.entity.Device;

public class HomeFragment extends Fragment implements UpdateGroupNameDialog.GroupNameDialogListener {

    private FragmentHomeBinding binding;
    private Device thisDevice;
    private HomeViewModel viewModel;
    private long duration;

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
        return binding.getRoot();
    }

    public void openDialog() {
        UpdateGroupNameDialog updateGroupNameDialog = new UpdateGroupNameDialog();
        updateGroupNameDialog.setListener(this);
        updateGroupNameDialog.show(getChildFragmentManager(), "Dialog");
    }

    @Override
    public void onSaveGroupName(String groupName) {
        viewModel.insertGroup(groupName, thisDevice.uuid);
    }
}