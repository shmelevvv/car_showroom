package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    private List<Car> avalibleCars = new ArrayList<>();
    private final String provider = "AUDI";

    public synchronized void acceptCar() {
            System.out.println("Производитель " + provider + " выпустил 1 авто");
            avalibleCars.add(new Car());
            notifyAll();
    }

    public synchronized void sellCar(Customer customer) {
        Long waitingTime = 0L;
        try {
            System.out.println(customer.getName() + " зашел в автосалон");
            while (avalibleCars.size() == 0 && waitingTime <= customer.getDesireTimeoutInSeconds()) {
                long startWaitingTime = System.currentTimeMillis();
                wait(customer.getDesireTimeoutInSeconds()*1000);
                long endWaitingTime = System.currentTimeMillis();
                waitingTime += (endWaitingTime - startWaitingTime + 1) / 1000;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (avalibleCars.size() == 0) {
            System.out.println("Машин нет, " + customer.getName() + " ушел из магазина");
        } else {
            avalibleCars.remove(0);
            System.out.println(customer.getName() + " уехал на новеньком авто");
        }
    }
}
