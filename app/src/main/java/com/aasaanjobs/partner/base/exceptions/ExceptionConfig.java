package com.aasaanjobs.partner.base.exceptions;

import com.aasaanjobs.partner.R;

import java.util.HashMap;

import static com.aasaanjobs.partner.base.exceptions.ExceptionCodeConstants.*;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */
public class ExceptionConfig {

    private static HashMap<Integer,Integer> exceptionDictionary = new HashMap<>();

    static {
        store(VALUE_NOT_FOUND,R.string.app_name);
        store(USER_NOT_LOGGED_IN,R.string.user_not_logged_in);
    }

    private static void store(Integer valueNotFound, int app_name) {

    }

}
