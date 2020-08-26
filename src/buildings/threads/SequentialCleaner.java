package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class SequentialCleaner implements Runnable {

    private Semaphore semaphore;
    private Floor floor;

    public SequentialCleaner(Semaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getSpacesArray();
        for (int i = 0; i < spaces.length; i++) {
            semaphore.clean(i, spaces[i].getArea());
        }
        System.out.println("Cleaning is done!");
    }
}