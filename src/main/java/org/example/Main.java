package org.example;

public class Main {
    public static final int CAR_COUNT = 10;
    public static final int CUSTOMER_COUNT = 20;
    public static final long CUSTOMER_DESIRE_TIMEOUT_IN_SECONDS = 5;
    public static final int BUILD_TIME_IN_SECONDS = 1;

    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom();

        new Thread(() -> {
            for (int i = 0; i < CAR_COUNT; i++) {
                carShowroom.acceptCar();
                try {
                    Thread.sleep(BUILD_TIME_IN_SECONDS * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 1; i <= CUSTOMER_COUNT; i++) {
            String name = "Покупатель " + i;
            new Thread(() -> carShowroom.sellCar(new Customer(name, CUSTOMER_DESIRE_TIMEOUT_IN_SECONDS))).start();
        }
    }
}
