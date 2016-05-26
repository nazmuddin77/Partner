package com.aasaanjobs.partner.base.data.network;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */

@Singleton
public class RetrofitManager implements RetrofitService {

    private final Retrofit retrofit;
    private static RetrofitManager instance;

    @Inject
    PartnerProgressDialog progressDialog;

    private RetrofitManager(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static RetrofitManager getInstance(Retrofit retrofit) {
        if(instance == null) {
            instance = new RetrofitManager(retrofit);
        }
        return instance;
    }

    @Override
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    @Override
    public <T> Response<T> execute(Call<T> call) throws IOException {
        return call.execute();
    }

    @Override
    public <T> void enqueue(Call<T> call, final ServiceRepoListener<T> listener, boolean showLoading) {
        if(showLoading) {
            progressDialog.show();
        }
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                dismissProgressDialog();
                listener.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                dismissProgressDialog();
                listener.onFailure(call,t);
            }
        });
    }

    @Override
    public <T> boolean isExecuted(Call<T> call) {
        return call.isExecuted();
    }

    @Override
    public <T> void cancel(Call<T> call) {
        call.cancel();
    }

    @Override
    public <T> boolean isCanceled(Call<T> call) {
        return call.isCanceled();
    }

    @Override
    public <T> Call<T> clone(Call<T> call) {
        return call.clone();
    }

    @Override
    public <T> Request request(Call<T> call) {
        return call.request();
    }

    private void dismissProgressDialog() {
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
