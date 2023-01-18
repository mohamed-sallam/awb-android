package io.github.mohamed.sallam.awb.screen.updategroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.FragmentUpdateGroupBinding;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.screen.adapter.AppsAdapter;

/**
 * class `UpdateGroupFragment` displays the whitelisted applications of a
 * specific group. User can select and unselect applications on the group through
 * this fragment. User can search for a specific application, then finally save
 * the changes.
 *
 * @author Yousef Ahmed
 */
public class UpdateGroupFragment extends Fragment {
    private FragmentUpdateGroupBinding binding;
    private UpdateGroupViewModel updateGroupViewModel;

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
        inflater.inflate(R.menu.fragment_update_group_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateGroupBinding.inflate(inflater, container,false);
        updateGroupViewModel = new UpdateGroupViewModel(getActivity()
                                                        .getApplication(),
                                                        (UUID) getArguments().get("UUID")
        );
        AppsAdapter appsAdapter = new AppsAdapter(getActivity().getApplicationContext()
                , app -> {
                    if (app.isSelected()){
                         updateGroupViewModel.blockApp(app.getPackageName());
                         app.setSelected(false);
                    } else {
                        updateGroupViewModel.allowApp(app.getPackageName());
                        app.setSelected(true);
                    }
                });
        updateGroupViewModel.getWhitelistedApps().observe(getViewLifecycleOwner(),
                whitelistedApps -> {
                    List<App> newList = new ArrayList<>(appsAdapter.getCurrentList());
                    for (App app: newList) {
                        for (WhitelistedApp whitelistedApp: whitelistedApps) {
                            if (whitelistedApp.packageName.equals(app.getPackageName())) {
                                app.setSelected(true);
                                break;
                            }
                        }
                    }
                    appsAdapter.submitList(newList);
                    appsAdapter.notifyDataSetChanged();
                });
        updateGroupViewModel.getApps().observe(getViewLifecycleOwner(), apps -> appsAdapter.submitList(apps));
        binding.appsRecyclerView.setAdapter(appsAdapter);
        binding.saveButton.setOnClickListener(v -> {
            updateGroupViewModel.save();
            updateGroupViewModel.navigateBack.setValue(true);
        });
        updateGroupViewModel.navigateBack.observe(getViewLifecycleOwner(),
                navigate -> {
                    if (navigate) {
                        Navigation.findNavController(binding.getRoot()).
                                navigate(R.id.action_updateGroupFragment_to_homeFragment);
                        updateGroupViewModel.resetNavigation();
                    }
                });
        binding.cancelButton.setOnClickListener(v -> updateGroupViewModel.navigateBack.setValue(true));
        return binding.getRoot();
    }
}