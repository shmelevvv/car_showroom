package org.example;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    public final static int CAR_PREPARATION_TIME_IN_SECONDS = 2;

    private final Map<Car, Integer> avalibleCars;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public CarShowroom(Map<Car, Integer> avalibleCars) {
        this.avalibleCars = avalibleCars;
    }

    public void receiveCar(Provider provider, Car car) {
        lock.lock();
        try {
            int currentAmount = (avalibleCars.get(car) == null) ? 0 : avalibleCars.get(car);
            avalibleCars.put(car, ++currentAmount);
            System.out.println("Производитель " + provider.getName() + " выпустил 1 авто " + car);
        } finally {
            lock.unlock();
        }
    }

    public void sellCar(Car car, Customer customer) {
        System.out.println("Покупатель: " + customer.getName() + " зашел в автосалон");
        if (car.getPrice() > customer.getAmountOfMoney()) {
            System.out.println("У покупателя " + customer.getName() + " не хватает денег на машину");
            return;
        }

        try {
            if (lock.tryLock(customer.getDesireTimeoutInSeconds(), TimeUnit.SECONDS)) {
                if (avalibleCars.get(car) == null || avalibleCars.get(car) == 0) {
                    System.out.println("На складе нет модели " + car);
                } else {
                    int currentAmount = (avalibleCars.get(car) == null) ? 0 : avalibleCars.get(car);
                    avalibleCars.put(car, --currentAmount);
                    Thread.sleep(CAR_PREPARATION_TIME_IN_SECONDS * 1000);
                    System.out.println("Покупатель " + customer.getName() + ", уехал на новеньком авто: " + car);
                }
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
