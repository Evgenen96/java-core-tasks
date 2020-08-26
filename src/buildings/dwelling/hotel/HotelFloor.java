package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Space;

/**
    TASK 6.2 Создайте класс HotelFloor, расширяющий класс DwellingFloor. <br/>
    Каждый этаж отеля имеет показатель «количество звезд». <br/>
    Разные этажи отеля могут иметь разные значения показателя количества звезд. <br/>
    Этаж отеля можно создать как по количеству помещений этажа, так и по массиву помещений. <br/>
    Количество звезд этажа при создании объекта должно браться из константы в классе, равной 1.
*/

public class HotelFloor extends DwellingFloor {

    private int stars;

    public HotelFloor(int flatsAmount) {
        super(flatsAmount);
        stars = 1;
    }

    public HotelFloor(Space[] spacesArray) {
        super(spacesArray);
        stars = 1;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HotelFloor (" + getSpacesAmount() + ", ");
        for (Space space : getSpacesArray()) {
            stringBuilder.append(space + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HotelFloor && this.stars == (((HotelFloor) obj).stars) && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getSpacesAmount() ^ getStars() ^ super.hashCode();
    }
}
