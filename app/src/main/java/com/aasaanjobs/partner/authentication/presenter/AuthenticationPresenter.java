package com.aasaanjobs.partner.authentication.presenter;

import android.content.Context;

import com.aasaanjobs.partner.authentication.di.components.AuthenticationPresenterComponent;
import com.aasaanjobs.partner.authentication.di.modules.AuthPresenterModule;
import com.aasaanjobs.partner.authentication.presenter.jobs.FetchFAJob;
import com.aasaanjobs.partner.base.presenter.BasePartnerPresenter;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;
import com.birbit.android.jobqueue.JobManager;

import javax.inject.Inject;



/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@ScopedFragment
public class AuthenticationPresenter extends BasePartnerPresenter {

    @Inject
    JobManager jobManager;

    FAService faService;
    private AuthenticationPresenterComponent component;

    @Inject
    public AuthenticationPresenter(@ActivityContext Context context
            , AuthenticationViewListener viewListener) {
        super(context,viewListener);
    }

    @Override
    protected void initDagger() {
        component = getComponent()
                .provideAuthPresenterComponent(new AuthPresenterModule());
        component.injectPresenter(this);
        faService = component.provideFAService();
    }

    public void fetchFA() {
        jobManager.addJobInBackground(new FetchFAJob());
    }

    public void fetchFADescription() {
        jobManager.addJobInBackground(new FetchFAJob());
    }

    public void fetchFACategory() {
        jobManager.addJobInBackground(new FetchFAJob());
    }
}
