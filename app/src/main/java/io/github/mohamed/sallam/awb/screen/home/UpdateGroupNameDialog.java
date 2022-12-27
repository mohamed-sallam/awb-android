package io.github.mohamed.sallam.awb.screen.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

import io.github.mohamed.sallam.awb.R;

public class UpdateGroupNameDialog extends AppCompatDialogFragment {
    private EditText editTextGroupName;
    private GroupNameDialogListener listener;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
                .setTitle(getTitle())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("Save", null);

        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog dialog = (AlertDialog)getDialog();
        if (dialog != null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextGroupName.getText().length() == 0) {
                        editTextGroupName.setError("Group name is empty");
                    } else {
                        listener.onSaveGroupName(editTextGroupName.getText().toString());
                        dismiss();
                    }
                }
            });
        }
    }

    public interface GroupNameDialogListener {
        void onSaveGroupName(String groupName);
    }
}
