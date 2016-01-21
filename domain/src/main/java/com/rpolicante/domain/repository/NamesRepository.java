package com.rpolicante.domain.repository;

import com.rpolicante.domain.Names;

import java.util.List;

import rx.Observable;

/**
 * Created by policante on 1/19/16.
 */
public interface NamesRepository {

    Observable<List<Names>> names();

    Observable<Boolean> delete(Names names);

    Observable<Integer> save(Names names);

    Observable<Names> getName(Integer identifier);
}
