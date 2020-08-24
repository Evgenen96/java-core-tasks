
import buildings.OfficeBuilding;

import java.io.*;


public class Main {

    public static void main(String[] args) {
        int[] officesAmount = new int[]{1, 3, 5, 6, 1, 5};
        OfficeBuilding offBuilding = new OfficeBuilding(6, officesAmount);


//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
//                new FileOutputStream("building.out"))) {
//            objectOutputStream.writeObject(offBuilding);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("building.out"))) {
            OfficeBuilding aBuilding = (OfficeBuilding) objectInputStream.readObject();
            System.out.println(aBuilding);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


//            objectOutputStream.writeObject(igor);
//            objectOutputStream.writeObject(renat);


//        // Востановление из файла с помощью класса ObjectInputStream
//        ObjectInputStream objectInputStream = new ObjectInputStream(
//                new FileInputStream("person.out"));
//        Person igorRestored = (Person) objectInputStream.readObject();
//        Person renatRestored = (Person) objectInputStream.readObject();
//        objectInputStream.close();


    }


}
