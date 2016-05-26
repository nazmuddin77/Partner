package com.aasaanjobs.partner.base.data.db.tables;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */
@RealmClass
public class FADescriptionDO implements RealmModel {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("functional_area")
    private FunctionalAreaDO functionalAreaDO;

    @SerializedName("hire_fa_description")
    private String hireFADescription;

    @SerializedName("hire_why_hire")
    private String hireWhyHire;

    @SerializedName("jobs_fa_description")
    private String jobsFADescription;

    @SerializedName("jobs_fa_requirement")
    private String jobsFARequirement;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FunctionalAreaDO getFunctionalAreaDO() {
        return functionalAreaDO;
    }

    public void setFunctionalAreaDO(FunctionalAreaDO functionalAreaDO) {
        this.functionalAreaDO = functionalAreaDO;
    }

    public String getHireFADescription() {
        return hireFADescription;
    }

    public void setHireFADescription(String hireFADescription) {
        this.hireFADescription = hireFADescription;
    }

    public String getHireWhyHire() {
        return hireWhyHire;
    }

    public void setHireWhyHire(String hireWhyHire) {
        this.hireWhyHire = hireWhyHire;
    }

    public String getJobsFADescription() {
        return jobsFADescription;
    }

    public void setJobsFADescription(String jobsFADescription) {
        this.jobsFADescription = jobsFADescription;
    }

    public String getJobsFARequirement() {
        return jobsFARequirement;
    }

    public void setJobsFARequirement(String jobsFARequirement) {
        this.jobsFARequirement = jobsFARequirement;
    }
}
