package com.rpolicante.domain;

/**
 * Created by policante on 1/19/16.
 */
public class Names {

    private int id;
    private String name;
    private String description;

    public Names(){
    }

    public Names(String name, String description) {
        this.id = -1;
        this.name = name;
        this.description = description;
    }

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
