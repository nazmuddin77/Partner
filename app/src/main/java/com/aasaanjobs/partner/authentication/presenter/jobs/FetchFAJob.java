package com.aasaanjobs.partner.authentication.presenter.jobs;

import android.util.Log;
import com.aasaanjobs.partner.authentication.presenter.FAService;
import com.aasaanjobs.partner.base.data.db.tables.FunctionalAreaListDO;
import com.aasaanjobs.partner.base.presenter.jobs.BasePartnerJob;
import com.birbit.android.jobqueue.Params;


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
    protected void performTask() throws Throwable {
        FAService faService = service.create(FAService.class);
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
}
