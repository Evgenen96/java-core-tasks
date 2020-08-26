package buildings.net.server.sequential;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.net.server.Server;
import buildings.util.Buildings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


/**
 * TASK 8.2
 * Реализуйте серверную часть приложения в новом классе buildings.net.server.sequential.BinaryServer,
 * содержащем метод main(). Сервер должен выполнять последовательную обработку запросов. <br/>
 * Оценка стоимости здания считается как сумма всех площадей помещений здания,
 * умноженная на 1000$ для жилого дома, на 1500$ для офиса и на 2000$ для любой гостиницы.
 */
public class BinaryServer extends Server {

    public static void main(String args[]) {
        try {
            while (true) {
                System.out.println("Waiting for client...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                char info = dataInputStream.readChar();
                if (info == 'S') {
                    System.out.println("processing...");
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    while (info != 'F') {
                        String data = dataInputStream.readUTF();
                        String type = dataInputStream.readUTF();
                        System.out.println("Received:");
                        System.out.println(data);
                        System.out.println(type);
                        Buildings.setFactory(getFactory(type));
                        double cost = 0;
                        try {
                            cost = getBuildingCost(Buildings.readBuilding(new Scanner(data)));
                        } catch (BuildingUnderArrestException e) {
                            cost = -1;
                        }
                        dataOutputStream.writeDouble(cost);
                        System.out.println("Sent:");
                        System.out.println(cost);
                        info = dataInputStream.readChar();
                    }
                    System.out.println("Processing is done!");
                }
            }
        } catch (IOException e) {
            System.out.println("wtf");
            e.printStackTrace();
        }
    }



}
