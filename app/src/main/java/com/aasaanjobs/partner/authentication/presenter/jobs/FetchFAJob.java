package com.aasaanjobs.partner.authentication.presenter.jobs;

import android.util.Log;

import com.aasaanjobs.partner.authentication.presenter.AuthenticationPresenter;
import com.aasaanjobs.partner.authentication.presenter.FAService;
import com.aasaanjobs.partner.base.data.db.tables.FunctionalAreaListDO;
import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.presenter.jobs.BasePartnerJob;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */
public class FetchFAJob extends BasePartnerJob {

    private static final int PRIORITY = 1;

    public FetchFAJob() {
        super(new Params(PRIORITY).requireNetwork().persist());
    }

    @Override
    public void onAdded() {
        Log.i("JOBS","inside added");
    }

    @Override
    public void onRun() throws Throwable {
        FAService faService = retrofitService.create(FAService.class);
        Call<FunctionalAreaListDO> call = faService.fetchFunctionalArea();
        call.enqueue(new Callback<FunctionalAreaListDO>() {
            @Override
            public void onResponse(Call<FunctionalAreaListDO> call
                    , Response<FunctionalAreaListDO> response) {
                Log.i("","");
            }

            @Override
            public void onFailure(Call<FunctionalAreaListDO> call
                    , Throwable t) {
                Log.i("","");
            }
        });

        Log.i("JOBS","inside run");

    }

    @Override
    protected void onCancel(int cancelReason) {
        Log.i("JOBS","inside cancel");
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}
