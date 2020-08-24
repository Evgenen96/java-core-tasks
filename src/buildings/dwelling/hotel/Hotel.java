package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import interfaces.Floor;
import interfaces.Space;

/**
 * TASK 6.2 Создайте класс отеля Hotel, расширяющий класс Dwelling. <br/>
 * Класс отеля должен позволять узнать показатель «количество звезд» всего отеля, который равен <br/>
 * максимальному показателю «количество звезд» среди всех этажей отеля <br/>
 * (если этаж в здании отеля не является этажом отеля, то он при подсчётах игнорируется). <br/>
 */

public class Hotel extends Dwelling {

    public Hotel(int dwellingFloorsAmount, int[] flatOnDwellingFloorAmount) {
        super(dwellingFloorsAmount, flatOnDwellingFloorAmount);
    }

    public Hotel(Floor[] dwellingFloorsArray) {
        super(dwellingFloorsArray);
    }

    public int getStars() {
        int result = 0;
        for (Floor floor : getFloorsArray()) {
            if (floor instanceof HotelFloor) {
                if (result < ((Hotel) floor).getStars()) {
                    result = ((Hotel) floor).getStars();
                }
            }
        }
        return result;
    }

    /**
     * Определение лучшего номера <br/>
     * Лучшим считается номер с максимальным значением показателя area*coeff, <br/>
     * где area-площадь помещения, coeff-определяется следующим образом. <br/>
     * - coeff = 0.5 <br/>
     * * - coeff = 0.75 <br/>
     * ** - coeff = 1 <br/>
     * *** - coeff = 1.25 <br/>
     * **** - coeff = 1.5
     */
    @Override
    public Space getBestSpace() {
        double[] coeff = {0.5, 0.75, 1, 1.25, 1.5};
        Space bestSpace = null;
        double result = 0;
        for (Floor floor : getFloorsArray()) {
            if (floor instanceof HotelFloor) {
                for (Space flat : floor.getSpacesArray()) {
                    double score = coeff[((Hotel) floor).getStars() - 1] * flat.getArea();
                    if (result < score) {
                        result = score;
                        bestSpace = flat;
                    }
                }
            }
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dwelling (\n  " + getFloorsAmount() + "\n  ");
        for (Floor floor : getFloorsArray()) {
            stringBuilder.append(floor + ",\n  ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hotel && super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getFloorsAmount() ^ super.hashCode();
    }
}
