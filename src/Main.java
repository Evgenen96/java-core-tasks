
import buildings.office.OfficeBuilding;
import buildings.threads.*;
import interfaces.Floor;


public class Main {

    public static void main(String[] args) {
        int[] officesAmount = new int[]{1, 3, 5, 6, 1, 5};
        OfficeBuilding offBuilding = new OfficeBuilding(6, officesAmount);
        Floor floor = offBuilding.getFloor(3);

        /*        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("building.out"))) {
            objectOutputStream.writeObject(offBuilding);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("building.out"))) {
            OfficeBuilding aBuilding = (OfficeBuilding) objectInputStream.readObject();
            System.out.println(aBuilding);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } */
        Semaphore sem = new Semaphore();
        Thread repairer = new Thread(new SequentialRepairer(sem, floor));
        Thread cleaner = new Thread(new SequentialCleaner(sem, floor));
        repairer.start();
        cleaner.start();




    }


}
