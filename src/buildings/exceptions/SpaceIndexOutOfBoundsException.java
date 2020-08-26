package buildings.exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {

    private Integer number = null;

    public int getNumber() {
        return number;
    }

    public SpaceIndexOutOfBoundsException() {
    }

    public SpaceIndexOutOfBoundsException(int number) {
        this.number = number;
    }

    @Override
    public String getMessage() {
        if (number != null) {
            return "Ошибка выхода за границы номеров помещений: " + getNumber();
        }
        return super.getMessage();
    }
}
