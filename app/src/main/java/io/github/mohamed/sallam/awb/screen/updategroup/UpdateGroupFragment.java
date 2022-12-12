package io.github.mohamed.sallam.awb.screen.updategroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.FragmentUpdateGroupBinding;
import io.github.mohamed.sallam.awb.screen.adapter.AppsAdapter;

/**
 * Initialize the contents of the Activity's standard options menu.
 *
 * @author Yousef Ahmed
 * @author Abdurrahman Salah
 *
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
        Intent intent = getActivity().getIntent();
        binding = FragmentUpdateGroupBinding.inflate(inflater, container,false);
        updateGroupViewModel = new UpdateGroupViewModel(getActivity().getApplication(), (UUID) intent.getExtras().get("UUID"));
        AppsAdapter appsAdapter = new AppsAdapter(new AppsAdapter.OnAppListener() {
            @Override
            public void onClick(App app) {
                if (app.isSelected()){
                     updateGroupViewModel.blockApp(app.getName());
                     app.setSelected(false);
                } else {
                    updateGroupViewModel.allowApp(app.getName());
                    app.setSelected(true);
                }
            }
        });
        binding.appsRecyclerView.setAdapter(appsAdapter);
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGroupViewModel.save();
                updateGroupViewModel.navigateBack.setValue(true);
            }
        });
        updateGroupViewModel.navigateBack.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean navigate) {
                if (navigate) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateGroupFragment_to_homeFragment);
                    updateGroupViewModel.resetNavigation();
                }
            }
        });
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGroupViewModel.navigateBack.setValue(true);
            }
        });
        return binding.getRoot();
    }
}