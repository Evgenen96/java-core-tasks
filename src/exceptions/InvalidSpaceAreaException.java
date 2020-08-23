package exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {

    private int square;

    public InvalidSpaceAreaException() {
    }

    public InvalidSpaceAreaException(int officeNumber) {
        this.square = officeNumber;

    }

    public int getSquare() {
        return square;
    }

    @Override
    public String getMessage() {
        if (square < 0) {
            return "Отрицательное значение площади: " + getSquare();
        }
        if (square > 5000) {
            return "Значение площади выше возможных 5000: " + getSquare();
        }
        return super.getMessage();
    }

}
