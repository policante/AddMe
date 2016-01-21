package com.rpolicante.presentation.model;

import java.util.Random;

/**
 * Created by policante on 1/19/16.
 */
public class NameModel {

    public NameModel(){
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }

    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("* * * * * Name Model Details * * * * * *\n");
        builder.append("name=" + getName() + "\n");
        builder.append("description=" + getDescription() + "\n");
        builder.append("* * * * * * * * * * * * * * * * * * * * *\n");

        return builder.toString();
    }
}
