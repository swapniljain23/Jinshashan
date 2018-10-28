package com.swapniljain.jinshashan.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.swapniljain.jinshashan.R;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class JNAboutDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.nav_about);
        builder.setMessage(R.string.about_us_text)
                .setPositiveButton(R.string.ok_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
