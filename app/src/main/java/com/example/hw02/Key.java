package com.example.hw02;

public enum Key {

    CALC ("Calc"),
    NUMB ("Numb");

    private String title;

    Key(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
