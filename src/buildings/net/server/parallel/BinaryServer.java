package buildings.net.server.parallel;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.net.server.Server;
import buildings.util.Buildings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TASK 8.4
 * Реализуйте серверную часть приложения в рамках модели параллельной обработки запросов.
 * Для этого создайте новый класс buildings.net.server.parallel.BinaryServer.
 */
public class BinaryServer extends Server {

    public static void main(String args[]) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            while (!Thread.currentThread().isInterrupted()) {
                executor.submit(new Server(serverSocket.accept()));
            }
            executor.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class Server implements Runnable {

        private Socket socket;

        public Server(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                char info = dataInputStream.readChar();
                if (info == 'S') {
                    System.out.println("Processing...");
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
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}