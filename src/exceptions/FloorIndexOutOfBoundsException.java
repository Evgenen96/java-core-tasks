package exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    private Integer number = null;

    public FloorIndexOutOfBoundsException() {
    }

    public FloorIndexOutOfBoundsException(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String getMessage() {
        if (number != null) {
            return "Ошибка выхода за границы номеров этажей: " + getNumber();
        }
        return super.getMessage();
    }
}
