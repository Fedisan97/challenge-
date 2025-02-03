import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите уровень сложности:");
        System.out.println("1 - Самый легкий (1-50, 6 попыток)");
        System.out.println("2 - Легкий (1-250, 10 попыток)");
        System.out.println("3 - Средний (1-500, 12 попыток)");
        System.out.println("4 - Сложный (1-1025, 15 попыток)");
        System.out.println("5 - Невозможный (1-2000, 17 попыток)");
        System.out.println("6 - 200IQ (1-5000, 20 попыток)");

        String level = scanner.nextLine();

        switch (level) {
            case "1": // Уровень 1: от 1 до 50, 6 попыток
                playGame(scanner, 1, 50, 6);
                break;
            case "2": // Уровень 2: от 1 до 250, 10 попыток
                playGame(scanner, 1, 250, 10);
                break;
            case "3": // Уровень 3: от 1 до 500, 12 попыток
                playGame(scanner, 1, 500, 12);
                break;
            case "4": // Уровень 4: от 1 до 1025, 15 попыток
                playGame(scanner, 1, 1025, 15);
                break;
            case "5": // Уровень 5: от 1 до 2000, 17 попыток
                playGame(scanner, 1, 2000, 17);
                break;
            case "6": // Уровень 6: от 1 до 5000, 20 попыток
                playGame(scanner, 1, 5000, 20);
                break;
            default:
                System.out.println("Неверный ввод уровня. Попробуйте еще раз.");
                break;
        }

        scanner.close();
    }

    private static void playGame(Scanner scanner, int min, int max, int attempts) {
        int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
        System.out.printf("Угадайте загаданное число от %d до %d.\nУ вас есть %d попыток:\n", min, max, attempts);

        for (int i = 1; i <= attempts; i++) {
            System.out.printf("Попытка №%d: ", i);
            int guess = Integer.parseInt(scanner.nextLine());

            if (guess < min || guess > max) {
                System.out.printf("Введите число в диапазоне от %d до %d.\n", min, max);
                i--; // Повторяем попытку
                continue;
            }

            if (guess == randomNumber) {
                System.out.println("Верно! Вы угадали число!");
                return;
            } else if (guess < randomNumber) {
                System.out.println("Ваше число меньше загаданного.");
            } else {
                System.out.println("Ваше число больше загаданного.");
            }
        }

        System.out.printf("Вы исчерпали все попытки. Загаданное число было: %d.\n", randomNumber);
    }
}
