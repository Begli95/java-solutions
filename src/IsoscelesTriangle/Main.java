package IsoscelesTriangle;

public class Main {
    public static void main(String[] args) {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(-2, 0);
        Point pointC = new Point(2, 0);

        try {
            IsoscelesTriangle triangle = new IsoscelesTriangle(pointA, pointB, pointC);
            System.out.println(triangle);
            System.out.println("Периметр: " + triangle.getPerimeter());
            System.out.println("Площадь: " + triangle.getArea());
            System.out.println("Углы: " + java.util.Arrays.toString(triangle.getAngles()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
