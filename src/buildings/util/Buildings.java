package buildings.util;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.util.factories.DwellingFactory;
import buildings.util.sortcriterion.SortFloors;
import buildings.util.sortcriterion.SortSpaces;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <ul>
 * <li>TASK 4.3
 * Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building,
 * содержащий статические методы ввода-вывода зданий:
 *
 * <li>TASK 4.4
 * Добавьте в класс Buildings методы сериализации/десериализации объектов зданий. </li>
 *
 * <li>TASK 4.5
 * Добавьте метод текстовой записи, использующий возможности форматированного вывода.</li>
 *
 * <li>TASK 6.4
 * Добавьте в класс Buildings метод сортировки помещений этажа по возрастанию площадей помещений,
 * и метод сортировки этажей здания по возрастанию количества помещений на этаже.
 * Объедините оба метода в один параметризованный метод сортировки.
 * <br/>
 * В класс Buildings добавьте два метода сортировки с критерием –
 * сортировка помещений на этаже по убыванию количества комнат
 * и сортировка этажей в здании по убыванию общей площади помещений этажа.
 * Объедините оба метода в один параметризованный метод сортировки с критерием.
 * </li>
 *
 * <li>TASK 6.7
 * Создайте статическую ссылку на фабрику BuildingFactory
 * По умолчанию поле должно ссылаться на объект, порождающий экземпляры класса Dwelling и связанных с ним классов.</li>
 *
 * <li>TASK 6.8
 * В классе Buildings реализуйте статические методы, которые с помощью текущей фабрики создают новые экземпляры
 * соответствующих объектов. В остальных методах класса Buildings замените прямое создание экземпляров объектов
 * на вызов методов фабрики.</li>
 *
 * <li>TASK 7.3
 *  Добавьте в класс Buildings со статическими методами обработки реализацию метода
 *  Floor synchronizedFloor (Floor floor), возвращающего ссылку на оболочку указанного объекта этажа,
 *  безопасную с точки зрения многопоточности.</li>
 *
 *
 *  </ul>
 */

public class Buildings {

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    /**
     * запись данных о здании в байтовый поток
     */
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(out));
        dataOutputStream.writeInt(building.getFloorsAmount());
        for (Floor floor : building.getFloorsArray()) {
            dataOutputStream.writeInt(floor.getSpacesAmount());
            for (Space space : floor.getSpacesArray()) {
                dataOutputStream.writeDouble(space.getArea());
                dataOutputStream.writeInt(space.getRoomsAmount());
            }
        }
        dataOutputStream.flush();
    }

    /**
     * чтение данных о здании из байтового потока
     */
    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        Floor[] floors = new Floor[dataInputStream.readInt()];
        Space[] spaces;
        for (int i = 0; i < floors.length; i++) {
            spaces = new Space[dataInputStream.readInt()];
            for (int j = 0; j < spaces.length; j++) {
                double area = dataInputStream.readDouble();
                int roomsAmount = dataInputStream.readInt();
                spaces[j] = createSpace(area, roomsAmount);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    /**
     * запись здания в символьный поток
     */
    public static void writeBuilding(Building building, Writer out) throws IOException {
        out.write(building.getFloorsAmount());
        for (Floor floor : building.getFloorsArray()) {
            out.write(floor.getSpacesAmount());
            for (Space space : floor.getSpacesArray()) {
                out.write(String.valueOf(space.getArea()));
                out.write(space.getRoomsAmount());
            }
        }
        out.flush();
    }

    /**
     * чтение здания из символьного потока
     */
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        Floor[] floors = new Floor[(int) tokenizer.nval];
        Space[] spaces;
        int roomsAmount;
        double area;
        for (int i = 0; i < floors.length; i++) {
            tokenizer.nextToken();
            spaces = new Space[(int) tokenizer.nval];
            for (int j = 0; j < spaces.length; j++) {
                tokenizer.nextToken();
                area = tokenizer.nval;
                tokenizer.nextToken();
                roomsAmount = (int) tokenizer.nval;
                spaces[j] = createSpace(area, roomsAmount);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    /**
     * сериализация здания в байтовый поток
     */
    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(building);
    }

    /**
     * десериализация здания из байтового потока
     */
    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectStream = new ObjectInputStream(in);
        return (Building) objectStream.readObject();
    }

    /**
     * метод текстовой записи
     */
    public static void writeBuildingFormat(Building building, Writer writer) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.printf("%d ", building.getFloorsAmount());
        for (Floor floor : building.getFloorsArray()) {
            printWriter.printf("%d ", floor.getSpacesAmount());
            for (Space space : floor.getSpacesArray()) {
                printWriter.printf("%.3f ", space.getArea());
                printWriter.printf("%d ", space.getRoomsAmount());
            }
            printWriter.print(" ");
        }
        printWriter.println();
        printWriter.flush();
    }

    /**
     * форматирумый ввод для создания объекта здания
     */
    public static Building readBuilding(Scanner scanner) {
        Floor[] floors = new Floor[scanner.nextInt()];
        Space[] spaces;
        for (int i = 0; i < floors.length; i++) {
            spaces = new Space[scanner.nextInt()];
            for (int j = 0; j < spaces.length; j++) {
                double area = scanner.nextDouble();
                int roomsAmount = scanner.nextInt();
                spaces[j] = createSpace(area, roomsAmount);
            }
            floors[i] = createFloor(spaces);
        }
        return createBuilding(floors);
    }

    private static Space[] sortSpace(Floor floor) {
        Space[] sortedSpaces = floor.getSpacesArray();
        Arrays.sort(sortedSpaces);
        return sortedSpaces;
    }

    private static Floor[] sortFloor(Building building) {
        Floor[] sortedFloors = building.getFloorsArray();
        Arrays.sort(sortedFloors);
        return sortedFloors;
    }


    public static <T, V> V[] sortByAscending(T object) {
        if (object instanceof Floor) {
            Floor floor = (Floor) object;
            return (V[]) sortSpace(floor);
        } else {
            if (object instanceof Building) {
                Building building = (Building) object;
                return (V[]) sortFloor(building);
            }
        }
        return null;
    }

    private static Space[] sortSpaceWithCriterion(Floor floor) {
        Space[] spaces = floor.getSpacesArray();
        Arrays.sort(spaces, new SortSpaces());
        return spaces;
    }

    private static Floor[] sortFloorWithCriterion(Building building) {
        Floor[] floors = building.getFloorsArray();
        Arrays.sort(floors, new SortFloors());
        return floors;
    }

    public static <T, V> V[] sortByDescending(T object) {
        if (object instanceof Floor) {
            Floor floor = (Floor) object;
            return (V[]) sortSpaceWithCriterion(floor);
        } else {
            if (object instanceof Building) {
                Building building = (Building) object;
                return (V[]) sortFloorWithCriterion(building);
            }
        }
        return null;
    }


    public static Space createSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    public static Space createSpace(double area, int roomsAmount) {
        return buildingFactory.createSpace(area, roomsAmount);
    }

    public static Floor createFloor(int spacesAmount) {
        return buildingFactory.createFloor(spacesAmount);
    }

    public static Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }

    public static Building createBuilding(int floorsAmount, int[] spacesAmounts) {
        return buildingFactory.createBuilding(floorsAmount, spacesAmounts);
    }

    public static Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }


}
