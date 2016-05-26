package com.aasaanjobs.partner.base.presenter;

import android.content.Context;

import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefService;
import com.aasaanjobs.partner.base.di.components.BasePresenterComponent;
import com.aasaanjobs.partner.base.di.components.DaggerBasePresenterComponent;
import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.utils.LoggerUtil;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.HasComponent;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@ScopedFragment
public class BasePresenter implements HasComponent<BasePresenterComponent> {

    @Inject
    protected RetrofitService retrofitService;

    @Inject
    protected RealmService realmService;

    @Inject
    protected SharedPrefService sharedPrefService;

    @Inject
    protected LoggerUtil loggerUtil;

    protected final Context context;
    protected final BaseViewListener viewListener;
    private final BasePresenterComponent basePresenterComponent;

    public BasePresenter(@ActivityContext Context context, BaseViewListener viewListener) {
        this.context = context;
        this.viewListener = viewListener;
        basePresenterComponent = DaggerBasePresenterComponent.builder()
                                .baseActivityComponent(viewListener.getBaseActivityComponent())
                                .build();
        basePresenterComponent.injectBasePresenter(this);
        loggerUtil.setTag(setTag());
    }

    protected String setTag() {
        return this.getClass().getName();
    }

    @Override
    public BasePresenterComponent getComponent() {
        return basePresenterComponent;
    }
}
