package com.aasaanjobs.partner.base.presenter.jobs;
import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.di.components.BaseJobComponent;
import com.aasaanjobs.partner.base.di.components.DaggerBaseJobComponent;
import com.aasaanjobs.partner.base.di.modules.BaseJobModule;
import com.aasaanjobs.partner.root.PartnerApplication;
import com.aasaanjobs.partner.root.di.HasComponent;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import javax.inject.Inject;


/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */
public abstract class BasePartnerJob extends Job implements HasComponent<BaseJobComponent> {

    private BaseJobComponent baseJobComponent;

    @Inject
    protected RetrofitService service;

    protected BasePartnerJob(Params params) {
        super(params);
    }

    @Override
    public void onAdded() {}

    @Override
    public void onRun() throws Throwable {
        baseJobComponent = DaggerBaseJobComponent
                .builder()
                .baseJobModule(new BaseJobModule())
                .applicationComponent(PartnerApplication.getComponent())
                .build();
        baseJobComponent.injectJob(this);
        performTask();
    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable
            , int runCount, int maxRunCount) {
        return null;
    }

    @Override
    public BaseJobComponent getComponent() {
        return baseJobComponent;
    }

    protected abstract void performTask() throws Throwable;
}
