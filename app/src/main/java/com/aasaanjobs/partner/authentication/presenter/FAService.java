package com.aasaanjobs.partner.authentication.presenter;

import com.aasaanjobs.partner.base.data.db.tables.FACategoryListDO;
import com.aasaanjobs.partner.base.data.db.tables.FADescriptionListDO;
import com.aasaanjobs.partner.base.data.db.tables.FunctionalAreaListDO;
import com.aasaanjobs.partner.base.data.db.tables.LanguageListDO;
import com.aasaanjobs.partner.base.data.db.tables.LocationListDO;

import retrofit2.Call;

import retrofit2.http.GET;


/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */


public interface FAService {

    @GET("api/v4/fa/?limit=500")
    Call<FunctionalAreaListDO> fetchFunctionalArea();

    @GET("api/v4/fa_category/?limit=500")
    Call<FACategoryListDO> fetchFACategories();

    @GET("api/v4/fa_description/?format=json&limit=500")
    Call<FADescriptionListDO> fetchFADescription();

    @GET("api/v4/place/?limit=12000")
    Call<LocationListDO> fetchLocation();

    @GET("api/v4/language/?limit=20")
    Call<LanguageListDO> fetchLanguages();
}
