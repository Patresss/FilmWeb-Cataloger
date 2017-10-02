package com.patres.cataloger;

public enum FilmType {

    FILM("F"),
    SERIES("S");

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    FilmType(String type) {
        this.type = type;
    }
}
