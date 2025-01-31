package expedition;

public class Car {
    private int id;
    private int crew;
    private int capacity;
    private boolean functional;

    public Car(int id, int crew, int capacity) {
        this.id = id;
        this.crew = crew;
        this.capacity = capacity;
        this.functional = true;
    }

    public int getId() {
        return id;
    }

    public int getCrew() {
        return crew;
    }

    // возвращает свободные места
    public int getFreeSeats() {
        return functional ? capacity - crew : 0;
    }

    public boolean isFunctional() {
        return functional;
    }

    // переводит машину в нерабочее состояние
    public void breakDown() {
        this.functional = false;
    }

    public void removeCrew() {
        this.crew = 0;
    }
    public int getCapacity() {
        return capacity;
    }

    // добавляет экипаж с проверкой на переполнение
    public void addCrew(int count) {
        if (this.crew + count > this.capacity) {
            throw new IllegalArgumentException("Невозможно добавить экипаж: недостаточно мест в машине №" + this.id);
        }
        this.crew += count;
    }
}
