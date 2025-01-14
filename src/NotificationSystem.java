import java.util.ArrayList;
import java.util.List;

public class NotificationSystem {
    private static final List<String> notifications = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // Simula la recepción de notificaciones cada segundo
        new Thread(NotificationSystem::receiveNotifications).start();

        // Procesa las notificaciones en un solo hilo
        processNotifications();
    }

    // Simula la recepción de notificaciones
    private static void receiveNotifications() {
        int counter = 1;
        while (true) {
            String notification = "Notification" + counter++;
            notifications.add(notification);
            System.out.println("Added: " + notification);
            try {
                Thread.sleep(1000); // Simula la recepción cada 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Procesa las notificaciones
    private static void processNotifications() {
        while (true) {
            if (!notifications.isEmpty()) {
                String notification = notifications.remove(0); // Extrae la primera notificación
                sendNotification(notification); // Simula el envío
            } else {
                System.out.println("No notifications to process...");
                try {
                    Thread.sleep(500); // Espera antes de revisar de nuevo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Simula el envío de una notificación (operación I/O lenta)
    private static void sendNotification(String notification) {
        System.out.println("Sending: " + notification);
        try {
            Thread.sleep(3000); // Simula el retraso del envío
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sent: " + notification);
    }
}
