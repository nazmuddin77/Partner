package com.aasaanjobs.partner.base.views.adapters.viewpager;

import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 * Created by nazmuddinmavliwala on 23/12/15.
 */

public interface PageDescriptor<T extends Fragment> extends Parcelable {
    String getFragmentTag();
    int getPageId();
    String getTitle();
    T getFragment();
}