package com.aasaanjobs.partner.base.presenter.jobs;

import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.di.components.BaseJobComponent;
import com.aasaanjobs.partner.base.di.components.DaggerBaseJobComponent;
import com.aasaanjobs.partner.base.di.modules.BaseJobModule;
import com.aasaanjobs.partner.root.PartnerApplication;
import com.aasaanjobs.partner.root.di.HasComponent;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;

import javax.inject.Inject;


/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */
public abstract class BasePartnerJob extends Job implements HasComponent<BaseJobComponent> {

    private final BaseJobComponent baseComponent;

    protected BasePartnerJob(Params params) {
        super(params);
        baseComponent = DaggerBaseJobComponent
                .builder()
                .baseJobModule(new BaseJobModule())
                .applicationComponent(PartnerApplication.getComponent()).build();
    }

    @Override
    public BaseJobComponent getComponent() {
        return baseComponent;
    }
}
