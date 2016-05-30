package com.aasaanjobs.partner.base.views.adapters.recyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aasaanjobs.partner.base.views.fargments.BasePartnerFragment;

import java.util.List;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public class BaseViewPagerAdapter<T extends BasePartnerFragment> extends FragmentStatePagerAdapter {

    private final List<T> fragmentList;

    public BaseViewPagerAdapter(FragmentManager fm, List<T> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
