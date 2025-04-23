import java.util.*;
import java.util.concurrent.*;

public class Main {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final char SNAKE_BODY = 'O';
    private static final char SNAKE_HEAD = '@';
    private static final char FOOD = '*';
    private static final char EMPTY = ' ';

    private static int score = 0;
    private static boolean gameOver = false;
    private static Direction direction = Direction.RIGHT;

    private static LinkedList<Point> snake = new LinkedList<>();
    private static Point food;
    private static Random random = new Random();

    public static void main(String[] args) {
        initializeGame();


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (!gameOver) {
                try {
                    handleInput();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //  игровой цикл
        while (!gameOver) {
            update();
            render();
            try {
                TimeUnit.MILLISECONDS.sleep(200); // скорость для игры
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdownNow();
        System.out.println("Game Over! Final Score: " + score);
    }

    private static void initializeGame() {
        snake.add(new Point(5, 5));
        snake.add(new Point(4, 5));
        snake.add(new Point(3, 5));
        spawnFood();
    }

    private static void spawnFood() {
        int x, y;
        do {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
            food = new Point(x, y);
        } while (snake.contains(food));
    }

    private static void handleInput() {
        try {
            if (System.in.available() > 0) {
                char input = (char) System.in.read();
                switch (input) {
                    case 'w' -> {
                        if (direction != Direction.DOWN) direction = Direction.UP;
                    }
                    case 's' -> {
                        if (direction != Direction.UP) direction = Direction.DOWN;
                    }
                    case 'a' -> {
                        if (direction != Direction.RIGHT) direction = Direction.LEFT;
                    }
                    case 'd' -> {
                        if (direction != Direction.LEFT) direction = Direction.RIGHT;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        Point head = snake.getFirst();
        Point newHead = switch (direction) {
            case UP -> new Point(head.x, head.y - 1);
            case DOWN -> new Point(head.x, head.y + 1);
            case LEFT -> new Point(head.x - 1, head.y);
            case RIGHT -> new Point(head.x + 1, head.y);
        };

        // столкновениее
        if (newHead.x < 0 || newHead.x >= WIDTH || newHead.y < 0 || newHead.y >= HEIGHT
                || snake.contains(newHead)) {
            gameOver = true;
            return;
        }

        snake.addFirst(newHead);

        //  съедания еды
        if (newHead.equals(food)) {
            score += 10;
            spawnFood();
        } else {
            snake.removeLast();
        }
    }

    private static void render() {
        clearConsole();

        //  границы
        for (int i = 0; i < WIDTH + 2; i++) System.out.print("#");
        System.out.println();

        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("#");
            for (int x = 0; x < WIDTH; x++) {
                Point p = new Point(x, y);
                if (p.equals(snake.getFirst())) {
                    System.out.print(SNAKE_HEAD);
                } else if (snake.contains(p)) {
                    System.out.print(SNAKE_BODY);
                } else if (p.equals(food)) {
                    System.out.print(FOOD);
                } else {
                    System.out.print(EMPTY);
                }
            }
            System.out.println("#");
        }

        for (int i = 0; i < WIDTH + 2; i++) System.out.print("#");
        System.out.println();

        System.out.println("Score: " + score);
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
        } catch (Exception e) {
            
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
