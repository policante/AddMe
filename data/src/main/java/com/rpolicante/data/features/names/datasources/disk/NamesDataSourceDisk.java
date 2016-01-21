package com.rpolicante.data.features.names.datasources.disk;

import com.rpolicante.domain.Names;

import java.util.List;

import rx.Observable;

/**
 * Created by policante on 1/19/16.
 */
public interface NamesDataSourceDisk {

    Observable<Names> findByID(Integer identifier);

    Observable<List<Names>> getNames();

    Observable<Integer> saveName(Names names);

    Observable<Boolean> removeName(Names name);
}
