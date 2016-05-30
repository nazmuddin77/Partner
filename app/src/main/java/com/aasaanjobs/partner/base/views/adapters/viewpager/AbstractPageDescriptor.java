package com.aasaanjobs.partner.base.views.adapters.viewpager;

import android.os.Parcel;
import android.support.v4.app.Fragment;

/**
 * Created by nazmuddinmavliwala on 21/01/16.
 */
public abstract class AbstractPageDescriptor<T extends Fragment> implements PageDescriptor<T> {

    protected String fragmentTag;
    protected int pageId;
    protected String pageTile;
    protected T fragment;


    public AbstractPageDescriptor(T fragment) {
        this.fragment = fragment;
    }

    public AbstractPageDescriptor(Parcel in) {
        fragmentTag = in.readString();
        pageTile = in.readString();
        pageId = in.readInt();
    }

    @Override
    public String getFragmentTag() {
        return fragmentTag;
    }

    @Override
    public int getPageId() {
        return pageId;
    }

    @Override
    public String getTitle() {
        return pageTile;
    }

    @Override
    public T getFragment() {
        return fragment;
    }


    public AbstractPageDescriptor addFragmentTag(String fragmentTag) {
        this.fragmentTag = fragmentTag;
        return this;
    }

    public AbstractPageDescriptor addPageId(int id) {
        this.pageId = id;
        return this;
    }

    public AbstractPageDescriptor addPageTitle(String title) {
        this.pageTile = title;
        return this;
    }
}
