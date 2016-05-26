package com.aasaanjobs.partner.base.views.animation;


import com.aasaanjobs.partner.R;

/**
 * Created by aditya on 9/9/15.
 */
public enum AnimationType {
    SLIDE_FROM_LEFT(new AnimationWrapper(R.anim.slide_from_left_enter, R.anim.slide_from_left_exit)),
    SLIDE_FROM_RIGHT(new AnimationWrapper(R.anim.slide_from_left_exit, R.anim.slide_from_left_enter)),
    SLIDE_FROM_TOP(new AnimationWrapper(R.anim.slide_from_top_enter, R.anim.slide_from_top_exit)),
    SLIDE_FROM_BOTTOM(new AnimationWrapper(R.anim.slide_from_top_exit, R.anim.slide_from_top_enter)),
    ZOOM_IN(new AnimationWrapper(R.anim.zoom_in, R.anim.zoom_out));

    private AnimationWrapper animationWrapper;

    AnimationType(AnimationWrapper animationWrapper) {

        this.animationWrapper = animationWrapper;
    }

    public AnimationWrapper getAnimationWrapper() {
        return animationWrapper;
    }
}
