package com.aasaanjobs.partner.base.views.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.aasaanjobs.partner.base.views.animation.AnimationType;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;

import javax.inject.Inject;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedActivity
public class Navigator {


    private final Context context;

    @Inject
    public Navigator(@ActivityContext Context context) {
        this.context = context;
    }

    public void navigateAndClearStack(Intent intent) {
        context.startActivity(intent);
    }

    public void navigate(Class clazz) {
        context.startActivity(getIntentForActivity(clazz));
    }

    public void navigate(Intent intent) {
        context.startActivity(intent);
    }

    public void navigate(Class clazz, AnimationType animationType) {
        context.startActivity(getIntentForActivity(clazz));
        ((Activity)context).overridePendingTransition(animationType.getAnimationWrapper().getEntry(),
                animationType.getAnimationWrapper().getExit());
    }

    public void navigate(Intent intent, AnimationType animationType) {
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(animationType.getAnimationWrapper().getEntry(),
                animationType.getAnimationWrapper().getExit());
    }

    public void navigateForResult(int requestCode, Class clazz) {
        ((Activity)context).startActivityForResult(getIntentForActivity(clazz), requestCode);
    }

    public void navigateForResult(int requestCode, Intent intent) {
        ((Activity)context).startActivityForResult(intent, requestCode);
    }

    public void navigateForResult(int requestCode, Class clazz, AnimationType animationType) {
        ((Activity)context).startActivityForResult(getIntentForActivity(clazz), requestCode);
        ((Activity)context).overridePendingTransition(animationType.getAnimationWrapper().getEntry(),
                animationType.getAnimationWrapper().getExit());
    }

    public void navigateForResult(int requestCode, Intent intent, AnimationType animationType) {
        ((Activity)context).startActivityForResult(intent, requestCode);
        ((Activity)context).overridePendingTransition(animationType.getAnimationWrapper().getEntry(),
                animationType.getAnimationWrapper().getExit());
    }

    private Intent getIntentForActivity(Class<?> clazz) {
        return new Intent(context, clazz);
    }
}
