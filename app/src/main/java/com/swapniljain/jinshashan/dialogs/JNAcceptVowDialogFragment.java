package com.swapniljain.jinshashan.dialogs;

import android.app.Dialog;
import android.os.Bundle;

import com.swapniljain.jinshashan.R;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class JNAcceptVowDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.vow_accepted_dialog_title);
        builder.setMessage(R.string.vow_accepted_dialog_message)
                .setPositiveButton(R.string.ok_text, (dialog, id) -> {
                    getActivity().finish();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
