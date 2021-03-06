package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

/**
 * TASK 7.1
 * Вторая нить последовательно считывает значения площадей помещений этажа. <br/>
 * По достижении конца этажа, а также в случае прерывания <br/>
 * нить заканчивает свое выполнение, выводя сообщение об этом.
 */
public class Cleaner extends Thread {

    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {

        int spacesAmount = floor.getSpacesAmount();
        Space[] spaces = floor.getSpacesArray();
        for (int i = 0; i < spacesAmount; i++) {
            if (isInterrupted()) {
                System.out.println("Cleaning is interrupted!");
                return;
            }
            System.out.println("Cleaning room number " + i + " with total area " + spaces[i].getArea() + " square meters");
        }
        System.out.println("Cleaning is done!");
    }
}
