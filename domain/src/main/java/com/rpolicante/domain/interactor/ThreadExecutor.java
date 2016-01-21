package com.rpolicante.domain.interactor;

import rx.Scheduler;

/**
 * Created by policante on 1/19/16.
 */
public interface ThreadExecutor {
    Scheduler getScheduler();
}
