package com.rpolicante.data.features.names.datasources.disk.realm.entities;

import java.util.Random;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by policante on 1/19/16.
 */
public class NamesDiskRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String description;

    public NamesDiskRealm() {
        this.id = new Random().nextInt(Integer.MAX_VALUE);;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
