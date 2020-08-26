package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class SequentialRepairer implements Runnable {

    private Semaphore semaphore;
    private Floor floor;

    public SequentialRepairer(Semaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        Space[] spaces = floor.getSpacesArray();
        for (int i = 0; i < spaces.length; i++) {
            semaphore.repair(i, spaces[i].getArea());
        }
        System.out.println("Repairing is done!");
    }
}