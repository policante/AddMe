package com.rpolicante.data.features.names;

import com.rpolicante.data.features.names.datasources.disk.NamesDataSourceDisk;
import com.rpolicante.domain.Names;
import com.rpolicante.domain.repository.NamesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 1/19/16.
 */
public class NamesDataRepository implements NamesRepository {
    private final NamesDataSourceDisk dataSourceDisk;

    @Inject
    public NamesDataRepository(NamesDataSourceDisk namesDataSourceDisk){
        this.dataSourceDisk = namesDataSourceDisk;
    }

    @Override
    public Observable<List<Names>> names() {
        return this.dataSourceDisk.getNames();
    }

    @Override
    public Observable<Boolean> delete(Names names) {
        return this.dataSourceDisk.removeName(names);
    }

    @Override
    public Observable<Integer> save(Names names) {
        return this.dataSourceDisk.saveName(names);
    }

    @Override
    public Observable<Names> getName(Integer identifier) {
        return this.dataSourceDisk.findByID(identifier);
    }

}
