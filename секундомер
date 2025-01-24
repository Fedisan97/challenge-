import java.util.Scanner;

public class Timer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите время в секундах для обратного отсчёта: ");
        int seconds = scanner.nextInt();

        for (int i = seconds; i >= 0; i--) {
            if (i == 0) {
                System.out.println("Время вышло!");
            } else {
                System.out.println(i + " секунд осталось");

                try {
                    Thread.sleep(1000); // Ждем одну секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
