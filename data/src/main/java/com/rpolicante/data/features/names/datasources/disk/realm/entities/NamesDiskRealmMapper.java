package com.rpolicante.data.features.names.datasources.disk.realm.entities;

import com.rpolicante.core.helpers.mappers.Mapper;
import com.rpolicante.domain.Names;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by policante on 1/19/16.
 */
@Singleton
public class NamesDiskRealmMapper implements Mapper<Names, NamesDiskRealm>{

    @Inject
    public NamesDiskRealmMapper(){
        //
    }

    @Override
    public NamesDiskRealm modelToData(Names model) {
        if (model == null){
            return null;
        }

        NamesDiskRealm namesDiskRealm = new NamesDiskRealm();
        namesDiskRealm.setId(model.getId());
        namesDiskRealm.setName(model.getName());
        namesDiskRealm.setDescription(model.getDescription());

        return namesDiskRealm;
    }

    @Override
    public Names dataToModel(NamesDiskRealm data) {
        if (data == null){
            return null;
        }

        Names names = new Names();
        names.setId(data.getId());
        names.setName(data.getName());
        names.setDescription(data.getDescription());

        return names;
    }

}
