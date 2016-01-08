package com.align.calculdelimc;

/**
 * Project: Calculdel'IMC
 *
 * @author Seth-Pharès Gnavo
 * @since 30 décembre 2015.
 * Copyright (C) 2015 Alissa Solutions
 */

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import com.afollestad.materialdialogs.MaterialDialog;

public class AboutDialog extends DialogFragment {

    public static void show(AppCompatActivity context) {
        AboutDialog dialog = new AboutDialog();
        dialog.show(context.getSupportFragmentManager(), "[ABOUT_DIALOG]");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String dismiss = getString(R.string.dialog_dismiss);
        return new MaterialDialog.Builder(getActivity())
                .iconRes(R.mipmap.ic_launcher)
                .title(R.string.dialog_about_title)
                .content(Html.fromHtml(getString(R.string.dialog_about)))
                .positiveText(dismiss)
                .contentLineSpacing(1.6f)
                .build();
    }
}