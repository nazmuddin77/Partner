package com.aasaanjobs.partner.base.views.viewdelegates;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.aasaanjobs.partner.base.di.components.BaseActivityComponent;
import com.aasaanjobs.partner.base.di.components.BaseViewDelegateComponent;
import com.aasaanjobs.partner.base.di.components.DaggerBaseViewDelegateComponent;
import com.aasaanjobs.partner.base.di.modules.BaseViewDelegateModule;
import com.aasaanjobs.partner.base.utils.LoggerUtil;
import com.aasaanjobs.partner.base.views.viewinteractors.BaseDelegateInteractor;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.HasComponent;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;
import com.aasaanjobs.partner.root.di.customidentifiers.ViewDelegateUnBinder;

import java.util.List;

import javax.inject.Inject;

import butterknife.Unbinder;

import static butterknife.ButterKnife.*;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
public abstract class BaseViewDelegate implements HasComponent<BaseViewDelegateComponent> {


    protected final Context context;
    protected final View view;
    protected final BaseDelegateInteractor interactor;
    private final BaseViewDelegateComponent baseViewDelegateComponent;

    @ViewDelegateUnBinder
    protected Unbinder unbinder;

    @Inject
    protected
    LoggerUtil loggerUtil;

    public BaseViewDelegate(@ActivityContext Context context, View view, BaseDelegateInteractor interactor) {
        this.context = context;
        this.view = view;
        this.interactor = interactor;

        baseViewDelegateComponent = DaggerBaseViewDelegateComponent.builder()
                                    .baseActivityComponent(getBaseActivityComponent())
                                    .baseViewDelegateModule(new BaseViewDelegateModule(this,view))
                                    .build();

        baseViewDelegateComponent.injectBaseViewDelegate(this);
        loggerUtil.setTag(setTag());

    }

    @Override
    public BaseViewDelegateComponent getComponent() {
        return baseViewDelegateComponent;
    }

    private BaseActivityComponent getBaseActivityComponent() {
        return interactor.getBaseActivityComponent();
    }

    protected String setTag() {
        return this.getClass().getName();
    }

    protected <T extends View>T findViewById(int id) {
        return findById(view,id);
    }

    protected void showToast(String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int stringResourceId) {
        try {
            Toast.makeText(context, stringResourceId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showToast(String message, int length) {
        Toast.makeText(context,message,length).show();
    }

    protected void showToast(int stringResourceId, int length) {
        try {
            Toast.makeText(context,stringResourceId,length).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setBackground(View view, int drawableResourceId) {
        view.setBackgroundResource(drawableResourceId);
    }

    protected void setBackgroundColor(View view, int colorId) {
        view.setBackgroundColor(colorId);
    }

    //with hint
    protected void setAdaptersForSpinners(int spinnerId, List<String> data, String hint) {
        Spinner spinner = findViewById(spinnerId);
        setAdaptersForSpinners(spinner,data,hint);
    }

    //with hint
    protected void setAdaptersForSpinners(Spinner spinner, List<String> data, String hint) {
       /* ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(R.layout.row_spinner_item);
        HintSpinnerAdapter hintSpinnerAdapter = new HintSpinnerAdapter(adapter, R.layout.default_spinner_hint_row, context, hint);
        spinner.setAdapter(hintSpinnerAdapter);
        */
    }

    //without hint
    protected void setAdaptersForSpinners(int spinnerId, List<String> data) {
        Spinner spinner = findViewById(spinnerId);
        setAdaptersForSpinners(spinner, data);
    }

    //without hint
    protected void setAdaptersForSpinners(Spinner spinner, List<String> data) {
        /*ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(R.layout.row_spinner_item);
        spinner.setAdapter(adapter);
        */
    }

    protected void settRecyclerViewAdapter(RecyclerView recyclerView,RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    protected void settRecyclerViewAdapter(int recyclerViewId,RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = findViewById(recyclerViewId);
        settRecyclerViewAdapter(recyclerView,adapter);
    }


    public void onDestroyView() {
        unbinder.unbind();
    }
}
