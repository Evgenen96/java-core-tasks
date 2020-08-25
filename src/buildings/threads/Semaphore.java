package buildings.threads;


/**
 * TASK 7.2
 * Нити должны обеспечивать поочередность операций ремонта-уборки помещений
 * (т.е. в консоль сообщения выводятся в порядке Repairing-Cleaning-Repairing-Cleaning-…)
 * независимо от приоритетов потоков. Уборка помещения не может следовать до ремонта помещения.
 * Для этого потребуется описать вспомогательный класс семафора
 */
public class Semaphore {

    private volatile boolean isRepaired = false;

    public synchronized void clean(int number, double area) {
        while (!isRepaired)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        isRepaired = false;
        System.out.println(
                "Cleaning space number " + number + " with total area " + area + " square meters"
        );
        notifyAll();
    }

    public synchronized void repair(int number, double area) {
        while (isRepaired)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        isRepaired = true;
        System.out.println(
                "Repairing space number " + number + " with total area " + area + " square meters"
        );
        notifyAll();
    }
}


