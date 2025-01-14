import java.util.concurrent.*;

public class NotificationSystem {
    private static BlockingQueue<String> notifications = new LinkedBlockingQueue<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            notifications.add("Notification " + i); //Agrega una notificación a la cola
            executor.submit(() -> sendNotification(notifications.poll()));//El submit envía una notificacion a un hilo disponible del pool y el poll extrae y elimina la primera notificación de la cola
        }
        executor.shutdown(); //cierra las tareas y no admite mas
    }

    private static void sendNotification(String message) {
        try {
            Thread.sleep(500); // Simulación de API externa
            System.out.println("Sent: " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();// Restablece el estado de interrupción del hilo
        }
    }
}
