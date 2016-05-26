package com.aasaanjobs.partner.root.di.customidentifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ViewHolderUnBinder {
}
