package buildings.util.sortcriterion;

import buildings.interfaces.Floor;

import java.util.Comparator;

/**
 * TASK 6.4
 * Опишите класс критерия, реализующий интерфейс <p>java.buildings.util.Comparator</p> таким образом,
 * чтобы он сравнивал этажи по общей площади помещений на этаже и
 * считал бОльшим этаж с меньшей общей площадью помещений на этаже.
 */
public class SortFloors implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return -o1.compareTo(o2);
    }
}
