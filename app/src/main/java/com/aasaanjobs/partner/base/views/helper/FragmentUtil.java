package com.aasaanjobs.partner.base.views.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;

import javax.inject.Inject;

/**
 * Created by aditya on 8/7/15.
 */

@ScopedActivity
public class FragmentUtil {

    private final FragmentManager fragmentManager;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    @Inject
    public FragmentUtil(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    public void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    public Fragment findFragmentByTag(String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }

    public <T extends Fragment> T findFragmentByTag(Class<T> clazz) {
        return (T) fragmentManager.findFragmentByTag(clazz.getName());
    }
}
