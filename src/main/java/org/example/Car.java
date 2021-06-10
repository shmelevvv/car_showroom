package org.example;

import java.util.Objects;

public class Car {
    private Model model;

    public Car(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return model == car.model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
