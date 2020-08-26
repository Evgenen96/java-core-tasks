package buildings.util.sortcriterion;

import buildings.interfaces.Space;

import java.util.Comparator;

/**
 * TASK 6.4
 * Опишите класс критерия, реализующий интерфейс <p>java.buildings.util.Comparator</p> таким образом,
 * чтобы он сравнивал помещения по количеству комнат
 * и считал бОльшим помещение с меньшим количеством комнат
 */
public class SortSpaces implements Comparator<Space> {

    @Override
    public int compare(Space o1, Space o2) {
        return -o1.compareTo(o2);
    }
}
