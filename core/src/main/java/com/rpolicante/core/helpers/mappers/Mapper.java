package com.rpolicante.core.helpers.mappers;

/**
 * Created by policante on 1/19/16.
 */
public interface Mapper<M, D> {
    D modelToData(M model);
    M dataToModel(D data);
}
