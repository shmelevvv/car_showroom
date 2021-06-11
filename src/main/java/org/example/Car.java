package org.example;

public enum Car {
    A4(3000000), A5 (4000000), A6(5000000), Q3 (3500000), Q5(4000000), Q7(5000000);
    private Long price;

    Car(long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
