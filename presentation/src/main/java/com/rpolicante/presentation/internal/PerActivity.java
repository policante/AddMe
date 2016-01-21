package com.rpolicante.presentation.internal;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by policante on 1/18/16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
