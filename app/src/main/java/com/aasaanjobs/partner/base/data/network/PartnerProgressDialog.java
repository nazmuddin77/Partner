package com.aasaanjobs.partner.base.data.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */

@Singleton
public class PartnerProgressDialog {

    private final Context context;
    private ProgressDialog progressDialog;

    @Inject
    public PartnerProgressDialog(@ApplicationContext Context context) {
        this.context = context;
    }

    public void show() {
        progressDialog.show();
    }

    public void hide() {
        progressDialog.hide();
    }

    public void dismiss() {
        progressDialog.dismiss();
    }

    public boolean isShowing() {
        return progressDialog.isShowing();
    }
}
