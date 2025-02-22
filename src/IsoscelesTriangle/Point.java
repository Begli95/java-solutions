package IsoscelesTriangle;

import java.util.Objects;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        /*
        1e-6 — это запись числа в экспоненциальной (научной) нотации в Java.
        Оно означает 1×10^−6, то есть 0.000001.
        1e-6 — это достаточно маленькое значение, чтобы учитывать погрешности при работе с числами с плавающей запятой.
         */
        return Math.abs(x - other.x) < 1e-6 && Math.abs(y - other.y) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
