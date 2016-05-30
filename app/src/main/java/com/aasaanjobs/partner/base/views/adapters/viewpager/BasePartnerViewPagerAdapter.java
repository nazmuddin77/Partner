package com.aasaanjobs.partner.base.views.adapters.viewpager;

import android.support.v4.app.FragmentManager;

import com.aasaanjobs.partner.base.views.fargments.BaseFragment;

import java.util.List;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public abstract class BasePartnerViewPagerAdapter<T extends BaseFragment> extends ArrayPagerAdapter<T> {

    public BasePartnerViewPagerAdapter(FragmentManager fragmentManager
            , List<PageDescriptor> descriptors) {
        super(fragmentManager, descriptors);
    }

    public T getFragmentByClassName(Class<T> clazz) {
        return (T) getFragmentManager().findFragmentByTag(
                clazz.getName());
    }
}
