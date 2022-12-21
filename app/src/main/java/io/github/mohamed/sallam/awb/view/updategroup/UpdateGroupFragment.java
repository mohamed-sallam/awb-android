package io.github.mohamed.sallam.awb.view.updategroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import io.github.mohamed.sallam.awb.R;

/**
 * class `UpdateGroupFragment` displays the whitelisted applications of a
 * specific group. User can select and unselect applications on the group through
 * this fragment. User can search for a specific application, then finally save
 * the changes.
 * Use the {@link UpdateGroupFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class UpdateGroupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Empty public constructor is a must.
     * @see <a href="https://developer.android.com/reference/android/app/Fragment#public-constructors_1">
     *     Android Dcoumentation</a>
     */
    public UpdateGroupFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment UpdateGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateGroupFragment newInstance(String param1, String param2) {
        UpdateGroupFragment fragment = new UpdateGroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // To call the method onCreateOptionsMenu()
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_group, container, false);
    }
}