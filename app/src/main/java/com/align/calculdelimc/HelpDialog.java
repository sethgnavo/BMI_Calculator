package com.align.calculdelimc;

/**
 * Project: Calculdel'IMC
 *
 * @author Seth-Pharès Gnavo, Aidan Follestad (afollestad)
 * @since 30 décembre 2015.
 * Copyright (C) 2015 Aidan Follestad
 */

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

public class HelpDialog extends DialogFragment {

    public static void show(AppCompatActivity context) {
        HelpDialog dialog = new HelpDialog();
        dialog.show(context.getSupportFragmentManager(), "[HELP_DIALOG]");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.dialog_help_title)
                .positiveText("OK")
                .content(R.string.dialog_help)
                .contentLineSpacing(1.6f)
                .build();
    }
}
