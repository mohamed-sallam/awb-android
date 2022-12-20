package io.github.mohamed.sallam.awb.view.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

import io.github.mohamed.sallam.awb.R;

public class AddGroupDialog extends AppCompatDialogFragment {

    private EditText editTextGroupName;
    private HomeViewModel homeViewModel;
    private GroupNameDialogListener listener;

    public void setListener(GroupNameDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        @SuppressLint({"ResourceType", "InflateParams"}) View view = inflater.inflate(R.layout.dialog_add_group, null);
        editTextGroupName = view.findViewById(R.id.groupNameEditText);

        builder.setView(view)
                .setTitle("Add Group")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Objects.requireNonNull(AddGroupDialog.this.getDialog()).cancel();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onSaveGroupName(editTextGroupName.getText().toString());
                    }
                });

        return builder.create();
    }

    public interface GroupNameDialogListener {
        void onSaveGroupName(String GroupName);
    }
}
