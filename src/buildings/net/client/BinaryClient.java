package buildings.net.client;

import java.io.*;
import java.net.Socket;


/**
 * TASK 8.1
 * Реализуйте клиентскую часть приложения в новом классе buildings.net.client.BinaryClient, содержащем метод main().
 * Задайте имена трех файлов. <br/>
 * Первый файл существует на момент запуска программы и содержит в текстовом виде информацию о зданиях <br/>
 * Второй файл существует на момент запуска программы и содержит в текстовом виде информацию о видах зданий <br/>
 * Третий файл должен быть создан программой в ходе работы и должен хранить в текстовом виде оценки стоимости зданий.
 * <br/><br/>Программа должна установить через сокеты соединение с сервером, после чего считывать
 * из первого и второго файладанные о здании, передавать их в бинарной форме серверу
 * и получать от него результат оценки стоимости здания, и так по очереди для всех исходных данных.
 */
public class BinaryClient {
    public static void main(String[] args) {
        try {
            FileReader fileReaderType = new FileReader(new File("res/BuildingType.txt"));
            FileReader fileReaderData = new FileReader(new File("res/BuildingData.txt"));
            PrintWriter printWriter = new PrintWriter(new FileWriter(new File("res/BuildingCost.txt")));
            BufferedReader bufferedReaderType = new BufferedReader(fileReaderType);
            BufferedReader bufferedReaderData = new BufferedReader(fileReaderData);
            String type = bufferedReaderType.readLine();
            String data = bufferedReaderData.readLine();
            Socket socket = new Socket("localhost", 666);
            System.out.println("Connected");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeChar('S');
            while (type != null) {
                dataOutputStream.writeUTF(data);
                dataOutputStream.writeUTF(type);
                System.out.println("Sent:");
                System.out.println(data);
                System.out.println(type);
                double result = dataInputStream.readDouble();
                System.out.println("Received:");
                System.out.println(result);
                if (result == -1) {
                    String message = "Building is under arrest";
                    System.out.println(message);
                    printWriter.println(message);
                }
                else {
                    printWriter.println(result);
                }
                printWriter.flush();
                type = bufferedReaderType.readLine();
                data = bufferedReaderData.readLine();
                if (type == null) {
                    dataOutputStream.writeChar('F'); //finish
                } else {
                    dataOutputStream.writeChar('N'); //next
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
