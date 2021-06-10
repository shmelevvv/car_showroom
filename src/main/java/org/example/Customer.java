package org.example;

public class Customer {
    private String name;
    private Long amountOfMoney;
    private Long desireTimeoutInSeconds;

    public Customer(String name, Long amountOfMoney, Long desireTimeoutInSeconds) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
        this.desireTimeoutInSeconds = desireTimeoutInSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Long amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Long getDesireTimeoutInSeconds() {
        return desireTimeoutInSeconds;
    }

    public void setDesireTimeoutInSeconds(Long desireTimeoutInSeconds) {
        this.desireTimeoutInSeconds = desireTimeoutInSeconds;
    }
}
