package io.github.mohamed.sallam.awb.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Objects;

import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements AddGroupDialog.GroupNameDialogListener {

    private FragmentHomeBinding binding;

    private HomeViewModel viewModel;
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
        binding = FragmentHomeBinding.inflate(Objects.requireNonNull(inflater), container, false);
        viewModel = new HomeViewModel(requireActivity().getApplication());
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        return binding.getRoot();
    }

    public void openDialog() {
        AddGroupDialog addGroupDialog = new AddGroupDialog();
        addGroupDialog.setListener(this);
        addGroupDialog.show(getChildFragmentManager(), "Dialog");
    }

    @Override
    public void onSaveGroupName(String groupName) {
        viewModel.insertGroup(groupName,
                Objects.requireNonNull(
                        viewModel
                                .getThisDevice()
                                .getValue()
                ).uuid
        );
    }
}