package com.aasaanjobs.partner.base.views.animation;

/**
 * Created by aditya on 9/9/15.
 */
public class AnimationWrapper {
    private int entry;
    private int exit;

    public AnimationWrapper(int entry, int exit) {
        this.setEntry(entry);
        this.setExit(exit);
    }


    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }
}
