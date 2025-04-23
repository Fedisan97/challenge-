import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        int heroX = 0, heroY = 0;      //координаты
        int health = 100;              //здоровье
        int maxX = 4, maxY = 4;      //поле

        // карта (0 — пусто, 1 — ловушка, 2 — враг)
        int[][] dungeon = {
                {0, 0, 0, 0, 0}, // левый верхний угол
                {0, 1, 0, 2, 0},
                {0, 0, 0, 0, 0},
                {0, 2, 0, 1, 0},
                {0, 0, 0, 0, 0}  // правый нижний угол
        };


        System.out.println("Управление:");
        System.out.println("8 — вверх\n2 — вниз\n4 — влево\n6 — вправо");
        System.out.println("Цель: добежать до правой нижней клетки.\n");
        System.out.println("карта 5 на 5");
        while (heroX != maxX || heroY != maxY) {
            System.out.printf("Координаты (%d,%d). Здоровье %d%n", heroX, heroY, health);
            System.out.print("Направление хода: ");
            int dir = input.nextInt();

            //перемещение
            switch(dir) {
                case 8: if (heroY > 0) heroY--; break;
                case 2: if (heroY < maxY) heroY++; break;
                case 4: if (heroX > 0) heroX--; break;
                case 6: if (heroX < maxX) heroX++; break;
                default: continue;
            }


            int cellType = dungeon[heroY][heroX];
            if (cellType == 1) { // Ловушка
                health -= 20;
                System.out.println("Попались в ловушку! Потеряно 20 очков здоровья.");
            } else if (cellType == 2) { // Встреча с врагом
                health -= 10;
                System.out.println("Победили врага! Потеряно 10 очков здоровья.");
            }

            // смерть героя
            if (health <= 0) {
                System.out.println("Герой погиб. Конец игры.");
                break;
            }
        }

        if (health > 0 && heroX == maxX && heroY == maxY) {
            System.out.println("Вы успешно добрались до выхода! Поздравляем!");
        }

        input.close();
    }
}
