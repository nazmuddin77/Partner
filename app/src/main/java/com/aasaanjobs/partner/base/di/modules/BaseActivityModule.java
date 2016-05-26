package com.aasaanjobs.partner.base.di.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.aasaanjobs.partner.base.views.helper.FragmentUtil;
import com.aasaanjobs.partner.base.views.helper.Navigator;
import com.aasaanjobs.partner.base.views.activities.BaseActivity;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityUnBinder;
import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedActivity
@Module
public class BaseActivityModule {

    private final BaseActivity activity;

    public BaseActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @ScopedActivity
    @ActivityContext
    @Provides
    public Context getActivityContext() {
        return activity;
    }

    @ScopedActivity
    @Provides
    public FragmentManager getFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @ScopedActivity
    @Provides
    @ActivityUnBinder
    public Unbinder provideUnBinder() {
        return ButterKnife.bind(activity);
    }

    @ScopedActivity
    @Provides
    public Navigator getNavigator(@ActivityContext Context context) {
        return new Navigator(context);
    }

    @ScopedActivity
    @Provides
    public FragmentUtil getFragmentUtil(FragmentManager fragmentManager) {
        return new FragmentUtil(fragmentManager);
    }
}
