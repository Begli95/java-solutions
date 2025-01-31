package expedition;

public class Main {
    public static void main(String[] args) {
        try {
            Expedition expedition = new Expedition();
            expedition.start();
        } catch (ExpeditionFailureException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nТестирование класса Car:");
        Car car = new Car(1, 2, 5);
        System.out.println("Машина №" + car.getId() + ": экипаж = " + car.getCrew() + ", свободные места = " + car.getFreeSeats());

        System.out.println("Добавляем 2 члена экипажа...");
        car.addCrew(2);
        System.out.println("Машина №" + car.getId() + ": экипаж = " + car.getCrew() + ", свободные места = " + car.getFreeSeats());

        System.out.println("Попытка добавить 3 члена экипажа...");
        try {
            car.addCrew(3);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("Ломаем машину...");
        car.breakDown();
        System.out.println("Машина №" + car.getId() + " функциональна: " + car.isFunctional());
        System.out.println("Свободные места после поломки: " + car.getFreeSeats());
    }
}
