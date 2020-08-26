package buildings.net.client;

import buildings.exceptions.BuildingUnderArrestException;

import java.io.*;
import java.net.Socket;

/**
 * TASK 8.5
 * Создайте класс buildings.net.client.SerialClient, решающий ту же задачу,
 * но отличающиеся по протоколу взаимодействия: для передачи данных следует использовать сериализацию.
 */
public class SerialClient {
    private static ObjectInputStream objectInputStream;

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReaderType = new BufferedReader(new FileReader(new File("res/BuildingType.txt")));
            BufferedReader bufferedReaderData = new BufferedReader(new FileReader(new File("res/BuildingData.txt")));
            PrintWriter printWriter = new PrintWriter(new FileWriter("res/BuildingCost.txt"));
            String type = bufferedReaderType.readLine();
            String data = bufferedReaderData.readLine();
            Socket socket = new Socket("localhost", 666);
            System.out.println("Connected");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeChar('S');
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (type != null) {
                objectOutputStream.writeObject(data);
                objectOutputStream.writeObject(type);
                System.out.println("Sent:");
                System.out.println(data);
                System.out.println(type);
                if (objectInputStream == null) {
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                }
                Object result = objectInputStream.readObject();
                if (result instanceof BuildingUnderArrestException) {
                    BuildingUnderArrestException exception = (BuildingUnderArrestException) result;
                    System.out.println("Received:");
                    System.out.println(exception);
                    printWriter.println(exception.toString());
                } else {
                    double resultCost = (double) result;
                    System.out.println("Received:");
                    System.out.println(resultCost);
                    printWriter.println(resultCost);
                }
                printWriter.flush();
                type = bufferedReaderType.readLine();
                data = bufferedReaderData.readLine();
                if (data == null) {
                    dataOutputStream.writeChar('F');
                } else {
                    dataOutputStream.writeChar('N');
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
