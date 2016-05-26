package com.aasaanjobs.partner.base.views.helper;

import android.content.Intent;

import com.aasaanjobs.partner.base.views.animation.AnimationType;

/**
 * Created by aditya on 8/7/15.
 */
public interface NavigationHandler {

    void navigateAndClearStack(Intent intent);

    void navigate(Class clazz);

    void navigate(Intent intent);

    void navigate(Class clazz, AnimationType animationType);

    void navigate(Intent intent, AnimationType animationType);

    void navigateForResult(int requestCode, Class clazz);

    void navigateForResult(int requestCode, Intent intent);

    void navigateForResult(int requestCode, Class clazz, AnimationType animationType);

    void navigateForResult(int requestCode, Intent intent, AnimationType animationType);
}
