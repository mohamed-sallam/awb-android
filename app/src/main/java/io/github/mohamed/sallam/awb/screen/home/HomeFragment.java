package io.github.mohamed.sallam.awb.screen.home;

import static io.github.mohamed.sallam.awb.util.StatusUtil.isUsageStatGranted;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Objects;
import java.util.UUID;

import io.github.mohamed.sallam.awb.LockService;
import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.FragmentHomeBinding;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.screen.adapter.GroupAdapter;


/**
 * Class `HomeFragment` displays user's devices and their groups of applications.
 * We can choose the device we want to lock. We can select which group in our
 * devices to lock for a specific period of time. We offer some functionalities
 * for the user on groups like (Rename - Duplicate - Edit - Remove), he could
 * access them by right-click on the group. Users can determine the period of
 * the locking in the home fragment, finally user can apply the locking by the
 * lock button.
 *
 * A simple {@link Fragment} subclass.
 *
 * @author Yousef Ahmed
 * @author Mohamed Sallam
 */
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Device thisDevice;
    private HomeViewModel viewModel;
    private long duration;
    private GroupAdapter groupAdapter;
    private UpdateGroupNameDialog updateGroupNameDialog;

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
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        updateGroupNameDialog = new UpdateGroupNameDialog();
        binding.minutePicker.setMinValue(0);
        binding.minutePicker.setMaxValue(59);
        binding.minutePicker.setOnValueChangedListener((numberPicker, oldValue, newValue) -> {
            duration -= (long) oldValue * 60_000;
            duration += (long) newValue * 60_000;
        });

        binding.hourPicker.setMinValue(0);
        binding.hourPicker.setMaxValue(24);
        binding.hourPicker.setOnValueChangedListener((numberPicker, oldValue, newValue) -> {
            duration -= (long) oldValue * 3_600_000;
            duration += (long) newValue * 3_600_000;
        });
        binding.addButton.setOnClickListener(v -> {
            updateGroupNameDialog.setListener(groupName -> viewModel.insertGroup(groupName, thisDevice.uuid));
            updateGroupNameDialog.setTitle(getString(R.string.add_group));
            openDialog();
        });
        viewModel.getThisDevice().observe(getViewLifecycleOwner(), device -> {
            if (device == null) {
                Toast.makeText(getActivity(), "yooooooo, no device no life :(", Toast.LENGTH_SHORT).show();
            } else {
                thisDevice = device;
            }
        });
        groupAdapter = new GroupAdapter();
        binding.recyclerView.setAdapter(groupAdapter);
        registerForContextMenu(binding.recyclerView);
        binding.recyclerView.setOnCreateContextMenuListener(this);
        viewModel.getAllGroupsForThisDevice().observe(getViewLifecycleOwner(),
                                                groups -> groupAdapter.submitList(groups));

        binding.lockButton.setOnClickListener(view -> {
            if (isUsageStatGranted(requireContext().getApplicationContext())) {
                Intent lockServiceIntent = new Intent(getActivity(), LockService.class);
                lockServiceIntent.putExtra("duration", duration);
                viewModel.insertDetoxPeriod(duration, groupAdapter.getSelectedGroupUuid());
                requireActivity().startService(lockServiceIntent);
            } else {
                startActivity(
                        new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                );
            }
        });

        return binding.getRoot();
    }

    public void openDialog() {
        updateGroupNameDialog.show(getChildFragmentManager(), "Dialog");
    }

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
                updateGroupNameDialog.setListener(groupName -> viewModel.renameGroup(groupUuid, groupName));
                updateGroupNameDialog.setTitle(getString(R.string.rename_group));
                openDialog();
                break;
            case R.id.duplicateGroupOption:

                break;
            case R.id.editGroupOption:
                Bundle bundle = new Bundle();
                bundle.putSerializable("UUID", groupUuid);
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_homeFragment_to_updateGroupFragment
                                , bundle);
                break;
            case R.id.removeGroupOption:
                viewModel.deleteGroup(groupUuid);
                break;
        }
        return super.onContextItemSelected(item);
    }
}