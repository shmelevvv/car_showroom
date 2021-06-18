package org.example;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    private final Map<Car, Integer> avalibleCars;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public CarShowroom(Map<Car, Integer> avalibleCars) {
        this.avalibleCars = avalibleCars;
    }

    public void receiveCar(Provider provider, Car car) {
        try {
            lock.lock();
            int currentAmount = (avalibleCars.get(car) == null) ? 0 : avalibleCars.get(car);
            avalibleCars.put(car, ++currentAmount);
            System.out.println("Производитель " + provider.getName() + " выпустил 1 авто " + car);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void sellCar(Car car, Customer customer) {
        System.out.println(customer.getName() + " зашел в автосалон, хочет купить " + car);
        if (car.getPrice() > customer.getAmountOfMoney()) {
            System.out.println("У " + customer.getName() + " не хватает денег на машину");
            return;
        }
        int waitingTime = 0;
        try {
            while (waitingTime <= customer.getDesireTimeoutInSeconds()) {
                lock.lock();
                if (avalibleCars.get(car) != null && avalibleCars.get(car) != 0) {
                    int currentAmount = (avalibleCars.get(car) == null) ? 0 : avalibleCars.get(car);
                    avalibleCars.put(car, --currentAmount);
                    System.out.println(customer.getName() + ", уехал на новеньком авто: " + car);
                    return;
                } else {
                    condition.await(customer.getDesireTimeoutInSeconds(), TimeUnit.SECONDS);
                    waitingTime += customer.getDesireTimeoutInSeconds();
                }
            }
            System.out.println("На складе нет модели " + car);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
