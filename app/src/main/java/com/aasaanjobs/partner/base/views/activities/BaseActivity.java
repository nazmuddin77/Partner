package com.aasaanjobs.partner.base.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.aasaanjobs.partner.base.di.components.DaggerBaseActivityComponent;
import com.aasaanjobs.partner.base.di.modules.BaseActivityModule;
import com.aasaanjobs.partner.base.utils.LoggerUtil;
import com.aasaanjobs.partner.base.views.helper.FragmentHandler;
import com.aasaanjobs.partner.base.views.helper.FragmentUtil;
import com.aasaanjobs.partner.base.views.helper.NavigationHandler;
import com.aasaanjobs.partner.base.views.helper.Navigator;
import com.aasaanjobs.partner.base.di.components.BaseActivityComponent;
import com.aasaanjobs.partner.base.views.animation.AnimationType;
import com.aasaanjobs.partner.root.PartnerApplication;
import com.aasaanjobs.partner.root.di.components.ApplicationComponent;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityUnBinder;
import com.aasaanjobs.partner.root.di.HasComponent;

import javax.inject.Inject;

import butterknife.Unbinder;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements NavigationHandler
        , FragmentHandler, HasComponent<BaseActivityComponent> {

    private BaseActivityComponent baseComponent;

    @Inject
    protected Navigator navigator;

    @Inject
    protected FragmentUtil fragmentUtil;

    @Inject
    protected LoggerUtil loggerUtil;

    @Inject
    @ActivityUnBinder
    protected Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseComponent = DaggerBaseActivityComponent
                .builder()
                .baseActivityModule(new BaseActivityModule(this))
                .applicationComponent(getApplicationComponent())
                .build();
        baseComponent.injectBaseActivity(this);
        loggerUtil.setTag(setTag());
    }

    private ApplicationComponent getApplicationComponent() {
        return PartnerApplication.getComponent();
    }

    protected String setTag() {
        return this.getClass().getName();
    }

    @Override
    public BaseActivityComponent getComponent() {
        return baseComponent;
    }

    @Override
    public void navigateAndClearStack(Intent intent) {
        navigator.navigateAndClearStack(intent);
    }

    @Override
    public void navigate(Class clazz) {
        navigator.navigate(clazz);
    }

    @Override
    public void navigate(Intent intent) {
        navigator.navigate(intent);
    }

    @Override
    public void navigate(Class clazz, AnimationType animationType) {
        navigator.navigate(clazz,animationType);
    }

    @Override
    public void navigate(Intent intent, AnimationType animationType) {
        navigator.navigate(intent,animationType);
    }

    @Override
    public void navigateForResult(int requestCode, Class clazz) {
        navigator.navigateForResult(requestCode,clazz);
    }

    @Override
    public void navigateForResult(int requestCode, Intent intent) {
        navigator.navigateForResult(requestCode,intent);
    }

    @Override
    public void navigateForResult(int requestCode, Class clazz, AnimationType animationType) {
        navigator.navigateForResult(requestCode,clazz,animationType);
    }

    @Override
    public void navigateForResult(int requestCode, Intent intent, AnimationType animationType) {
        navigator.navigateForResult(requestCode,intent,animationType);
    }

    @Override
    public void addFragment(int containerViewId, Fragment fragment) {
        fragmentUtil.addFragment(containerViewId,fragment);
    }

    @Override
    public void addFragment(int containerViewId, Fragment fragment, String tag) {
        fragmentUtil.addFragment(containerViewId,fragment,tag);
    }

    @Override
    public void replaceFragment(int containerViewId, Fragment fragment) {
        fragmentUtil.addFragment(containerViewId,fragment);
    }

    @Override
    public void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        fragmentUtil.replaceFragment(containerViewId,fragment,tag);
    }

    @Override
    public Fragment findFragmentByTag(String tag) {
        return fragmentUtil.findFragmentByTag(tag);
    }

    @Override
    public <T extends Fragment> T findFragmentByTag(Class<T> clazz) {
        return fragmentUtil.findFragmentByTag(clazz);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
