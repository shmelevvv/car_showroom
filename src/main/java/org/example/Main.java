package org.example;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom(new HashMap<Car, Integer>());
        Provider provider = new Provider("Audi");
        int carCoutn = 10;
        int custumerCount = 20;

        new Thread(() -> {
            for (int i = 0; i < carCoutn; i++) {
                Random random = new Random();
                Car car = Car.values()[random.nextInt(Car.values().length)];
                carShowroom.receiveCar(provider, car);
                try {
                    Thread.sleep(Provider.BUILD_TIME_IN_SECONDS * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < custumerCount; k++) {
                    Random random = new Random();
                    Car randomCar = Car.values()[random.nextInt(Car.values().length)];
                    Long amountOfMoney = ((long) random.nextInt(5 + 1) + 3) * 1000000;
                    Long desireTimeoutInSeconds = (long) random.nextInt(10) + 1;
                    String name = "Покупатель " + k;
                    carShowroom.sellCar(randomCar,
                            new Customer(name, amountOfMoney, desireTimeoutInSeconds));
                }
            }
        }).start();
    }
}
