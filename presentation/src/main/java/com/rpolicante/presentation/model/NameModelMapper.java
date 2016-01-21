package com.rpolicante.presentation.model;

import com.rpolicante.core.helpers.mappers.Mapper;
import com.rpolicante.domain.Names;

import javax.inject.Inject;

/**
 * Created by policante on 1/19/16.
 */
public class NameModelMapper implements Mapper<Names, NameModel> {

    @Inject
    public NameModelMapper(){
        //
    }

    @Override
    public NameModel modelToData(Names model) {
        NameModel nameModel = new NameModel();
        nameModel.setId(model.getId());
        nameModel.setName(model.getName());
        nameModel.setDescription(model.getDescription());
        return nameModel;
    }

    @Override
    public Names dataToModel(NameModel data) {
        Names names = new Names();
        names.setId(data.getId());
        names.setName(data.getName());
        names.setDescription(data.getDescription());
        return names;
    }
}
