package buildings.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {

    private int roomsAmount;

    public InvalidRoomsCountException() {
    }

    public InvalidRoomsCountException(int officeNumber) {
        this.roomsAmount = officeNumber;

    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    @Override
    public String getMessage() {
        if (roomsAmount < 0) {
            return "Отрицательное значение количества комнат: " + getRoomsAmount();
        }
        if (roomsAmount > 20) {
            return "Значение кол-ва комнат выше возможных 20: " + getRoomsAmount();
        }
        return super.getMessage();
    }
}
