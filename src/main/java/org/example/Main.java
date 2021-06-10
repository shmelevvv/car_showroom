package org.example;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom(new HashMap<Car, Integer>());
        Provider provider = new Provider("Audi");

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Random random = new Random();
                Model model = Model.values()[random.nextInt(Model.values().length)];
                carShowroom.receiveCar(provider, new Car(model));
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
                for (int k = 0; k < 20; k++) {
                    Random random = new Random();
                    Model randomModel = Model.values()[random.nextInt(Model.values().length)];
                    Long amountOfMoney = ((long) random.nextInt(5 + 1) + 3) * 1000000;
                    Long desireTimeoutInSeconds = (long) random.nextInt(10) + 1;
                    String name = "Покупатель " + k;
                    carShowroom.sellCar(new Car(randomModel), new Customer(name, amountOfMoney, desireTimeoutInSeconds));
                }
            }
        }).start();




    }
}
