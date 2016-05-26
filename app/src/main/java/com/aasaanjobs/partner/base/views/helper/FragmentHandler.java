package com.aasaanjobs.partner.base.views.helper;

import android.support.v4.app.Fragment;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */
public interface FragmentHandler {

    void addFragment(int containerViewId, Fragment fragment);

    void addFragment(int containerViewId, Fragment fragment, String tag);

    void replaceFragment(int containerViewId, Fragment fragment);

    void replaceFragment(int containerViewId, Fragment fragment, String tag);

    Fragment findFragmentByTag(String tag);

    <T extends Fragment> T findFragmentByTag(Class<T> clazz);
}
