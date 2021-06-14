package org.example;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static final int CAR_COUNT = 10;
    public static final int CUSTOMER_COUNT = 20;
    public static final long CUSTOMER_DESIRE_TIMEOUT_IN_SECONDS = 5;
    public static final int MIN_AMOUNT_OF_MONEY = 2_500_000;
    public static final int MAX_AMOUNT_OF_MONEY = 6_000_000;

    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom(new HashMap<Car, Integer>());
        Provider provider = new Provider("Audi");

        new Thread(() -> {
            for (int i = 0; i < CAR_COUNT; i++) {
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
                for (int k = 0; k < CUSTOMER_COUNT; k++) {
                    Random random = new Random();
                    Car randomCar = Car.values()[random.nextInt(Car.values().length)];
                    Long amountOfMoney = (long)random.nextInt(MAX_AMOUNT_OF_MONEY - MIN_AMOUNT_OF_MONEY + 1)
                            + MIN_AMOUNT_OF_MONEY;
                    String name = "Покупатель " + k;
                    carShowroom.sellCar(randomCar,
                            new Customer(name, amountOfMoney, CUSTOMER_DESIRE_TIMEOUT_IN_SECONDS));
                }
            }
        }).start();
    }
}
