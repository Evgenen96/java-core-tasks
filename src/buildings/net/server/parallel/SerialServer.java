package buildings.net.server.parallel;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.net.server.Server;
import buildings.util.Buildings;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * TASK 8.5
 * Создайте класс buildings.net.server.parallel.SerialServer, решающий ту же задачу,
 * но отличающиеся по протоколу взаимодействия: для передачи данных следует использовать сериализацию.
 */
public class SerialServer extends Server {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            while (!Thread.currentThread().isInterrupted()) {
                executorService.submit(new Server(serverSocket.accept()));
            }
            executorService.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class Server implements Runnable {

        private Socket socket;

        public Server(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            DataInputStream dataInputStream = null;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                char info = dataInputStream.readChar();
                if (info == 'S') {
                    System.out.println("Processing..");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    while (info != 'F') {
                        String data = (String) objectInputStream.readObject();
                        String type = (String) objectInputStream.readObject();
                        System.out.println("Received:");
                        System.out.println(data);
                        System.out.println(type);
                        Buildings.setFactory(getFactory(type));
                        Object result;
                        try {
                            result = getBuildingCost(Buildings.readBuilding(new Scanner(data)));
                            objectOutputStream.writeObject(result);
                        } catch (BuildingUnderArrestException e) {
                            result = e;
                            objectOutputStream.writeObject(result);
                        }
                        System.out.println("Sent:");
                        System.out.println(result);
                        info = dataInputStream.readChar();
                    }
                    System.out.println("Processing is done!!!");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}