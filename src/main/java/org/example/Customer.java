package org.example;

public class Customer {
    private String name;
    private Long desireTimeoutInSeconds;

    public Customer(String name, Long desireTimeoutInSeconds) {
        this.name = name;
        this.desireTimeoutInSeconds = desireTimeoutInSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDesireTimeoutInSeconds() {
        return desireTimeoutInSeconds;
    }

    public void setDesireTimeoutInSeconds(Long desireTimeoutInSeconds) {
        this.desireTimeoutInSeconds = desireTimeoutInSeconds;
    }
}
