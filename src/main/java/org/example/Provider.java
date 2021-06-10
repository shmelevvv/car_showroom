package org.example;

public class Provider {
    public static final int BUILD_TIME_IN_SECONDS = 1;
    private String name;

    public Provider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
