package expedition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Expedition {
    private static final int TOTAL_CARS = 10;// общее количество машин в экспедиции
    private static final int CAR_CAPACITY = 5;// всего мест в машине
    private static final int CREW_PER_CAR = 2;// занято мест в машине экипажем
    private static final int DISTANCE = 1000;// дистанция экспедиции
    private static final int CHECK_INTERVAL = 100;// интервал, через который, экспедиция будет делать остановки/привалы
    private static final double FAILURE_PROBABILITY = 0.6;// вероятность поломки машины - 60%

    private List<Car> cars = new ArrayList<>(); // хранит список машин
    private Random random = new Random();// объект для случайной генерации событий

    /*
    Конструктор, срабатывает когда мы создадим объект (пример: Expedition expedition = new Expedition())
    В данном конструкторе мы заполняем наш список машинами, в данном случае в списке будет создано/добавлено 10 мишин
     */
    public Expedition() {
        for (int i = 0; i < TOTAL_CARS; i++) {
            cars.add(new Car(i + 1, CREW_PER_CAR, CAR_CAPACITY));
        }
    }

    public void start() {
        System.out.println("Экспедиция начинается! Исходное состояние:");
        printExpeditionStatus();
        // на каждом отрезке пути (100 км) вызывает проверку машин и выводит статус
        for (int currentDistance = CHECK_INTERVAL; currentDistance <= DISTANCE; currentDistance += CHECK_INTERVAL) {
            System.out.println("\nПройдено " + currentDistance + " км");
            checkCars();
            printExpeditionStatus();
        }
        System.out.println("Экспедиция успешно завершена!");
    }

    private void checkCars() {
        List<Car> brokenCars = new ArrayList<>();

        for (Car car : cars) {
            if (!car.isFunctional()) {
                continue;
            }
            // проверяет каждую машину на поломку
            if (random.nextDouble() < FAILURE_PROBABILITY) {
                car.breakDown();
                System.out.println("Машина №" + car.getId() + " сломалась!");
                brokenCars.add(car);
            }
        }
        // сломанные машины добавляются в список brokenCars
        for (Car brokenCar : brokenCars) {
            redistributeCrew(brokenCar);
        }
    }

    // пересаживает экипаж из сломанной машины
    private void redistributeCrew(Car brokenCar) {
        int crewToMove = brokenCar.getCrew();
        System.out.println("\nПересаживаем " + crewToMove + " человек из машины №" + brokenCar.getId());

        brokenCar.removeCrew();
        int totalMoved = 0;

        for (Car car : cars) {
            if (!car.isFunctional()) continue;
            // для каждой рабочей машины вычисляет свободные места
            int freeSeats = car.getFreeSeats();
            if (freeSeats == 0) continue;
            // перемещает людей порциями
            int movingCrew = Math.min(crewToMove, freeSeats);
            car.addCrew(movingCrew);
            crewToMove -= movingCrew;
            totalMoved += movingCrew;

            System.out.printf("  В машину №%d добавлено %d человек. Теперь экипаж: %d/%d (свободно %d)%n",
                    car.getId(),
                    movingCrew,
                    car.getCrew(),
                    car.getCapacity(),
                    car.getFreeSeats()
            );

            if (crewToMove == 0) break;
        }
        // Если людей не удалось разместить (crewToMove > 0), выбрасывает исключение
        if (crewToMove > 0) {
            throw new ExpeditionFailureException("Не удалось рассадить весь экипаж! Оставшиеся: " + crewToMove);
        } else {
            System.out.printf("Успешно пересажено %d человек из машины №%d%n",
                    totalMoved,
                    brokenCar.getId()
            );
        }
    }

    // выводит текущее состояние
    private void printExpeditionStatus() {
        int workingCars = 0;
        int totalFreeSeats = 0;

        for (Car car : cars) {
            if (car.isFunctional()) {
                workingCars++;
                totalFreeSeats += car.getFreeSeats();
            }
        }

        System.out.printf("\nТекущий статус экспедиции:\n"
                        + "  Рабочих машин: %d/%d\n"
                        + "  Свободных мест: %d\n",
                workingCars,
                TOTAL_CARS,
                totalFreeSeats
        );
    }
}
