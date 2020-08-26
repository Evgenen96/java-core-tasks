package buildings.exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {

    private double area;

    public InvalidSpaceAreaException() {
    }

    public InvalidSpaceAreaException(double officeNumber) {
        this.area = officeNumber;

    }

    public double getArea() {
        return area;
    }

    @Override
    public String getMessage() {
        if (area < 0) {
            return "Отрицательное значение площади: " + getArea();
        }
        if (area > 5000) {
            return "Значение площади выше возможных 5000: " + getArea();
        }
        return super.getMessage();
    }

}
