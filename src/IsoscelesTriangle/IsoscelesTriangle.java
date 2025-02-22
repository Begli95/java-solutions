package IsoscelesTriangle;

import java.util.Objects;

public class IsoscelesTriangle {
    private final Point pointA; // Вершина треугольника
    private final Point pointB; // Левая точка основания
    private final Point pointC; // Правая точка основания

    // Конструктор
    public IsoscelesTriangle(Point pointA, Point pointB, Point pointC) {
        // Проверка, что треугольник существует
        if (!isValidTriangle(pointA, pointB, pointC)) {
            throw new IllegalArgumentException("Треугольник не существует.");
        }
        // Проверка, что основание параллельно оси X
        if (pointB.getY() != pointC.getY()) {
            throw new IllegalArgumentException("Основание треугольника не параллельно оси X.");
        }
        // Проверка, что треугольник равнобедренный
        if (!isIsosceles(pointA, pointB, pointC)) {
            throw new IllegalArgumentException("Треугольник не является равнобедренным.");
        }

        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    // Метод для проверки существования треугольника
    private boolean isValidTriangle(Point a, Point b, Point c) {
        double ab = distance(a, b);
        double ac = distance(a, c);
        double bc = distance(b, c);
        return (ab + ac > bc) && (ab + bc > ac) && (ac + bc > ab);
    }

    // Метод для проверки, что треугольник равнобедренный
    private boolean isIsosceles(Point a, Point b, Point c) {
        double ab = distance(a, b);
        double ac = distance(a, c);
        return Math.abs(ab - ac) < 1e-6; // Сравнение с учётом погрешности
    }

    // Метод для вычисления расстояния между двумя точками
    // метод distance вычисляет евклидово расстояние между двумя точкам p1 и p2  в декартовой системе координат
    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    // Метод для вычисления периметра
    public double getPerimeter() {
        double ab = distance(pointA, pointB);
        double ac = distance(pointA, pointC);
        double bc = distance(pointB, pointC);
        return ab + ac + bc;
    }

    // Метод для вычисления площади
    public double getArea() {
        double base = distance(pointB, pointC); // Основание
        double height = Math.abs(pointA.getY() - pointB.getY()); // Высота
        return (base * height) / 2;
    }

    // Метод для нахождения углов треугольника
    public double[] getAngles() {
        double ab = distance(pointA, pointB);
        double ac = distance(pointA, pointC);
        double bc = distance(pointB, pointC);

        double angleA = Math.toDegrees(Math.acos((ab * ab + ac * ac - bc * bc) / (2 * ab * ac)));
        double angleB = Math.toDegrees(Math.acos((ab * ab + bc * bc - ac * ac) / (2 * ab * bc)));
        double angleC = 180 - angleA - angleB;

        return new double[]{angleA, angleB, angleC};
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return String.format("Равнобедренный треугольник с вершинами: A%s, B%s, C%s", pointA, pointB, pointC);
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IsoscelesTriangle other = (IsoscelesTriangle) obj;
        return pointA.equals(other.pointA) && pointB.equals(other.pointB) && pointC.equals(other.pointC);
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(pointA, pointB, pointC);
    }
}
