package ru.live.game.livegame;

public class GameEngine {

    private final static int[][] WORLD = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public void gameLoop() {

//        while (true) {


            checkConditions();

            for (int row = 0; row < WORLD.length; row++) {
                for (int col = 0; col < WORLD[row].length; col++) {
                    System.out.print(WORLD[row][col]);
                    System.out.print(" ");
                }

                System.out.println();
            }

        sleep();

            clearConsole();
//        }

    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private void checkConditions() {

        for (int row = 0; row < WORLD.length; row++) {
            for (int col = 0; col < WORLD[row].length; col++) {
                final var neighboursCount = neighbours(row, col);

                if (WORLD[row][col] == 1 && (neighboursCount >= 4 || neighboursCount <= 1)) {
                    WORLD[row][col] = 0;
                } else if (WORLD[row][col] == 0 && neighboursCount == 3) {
                    WORLD[row][col] = 1;
                }
            }
        }
    }

    private int neighbours(int row, int col) {
        int neighboursCount = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i < 0 || j < 0 || i >= WORLD.length - 1 || j >= WORLD.length - 1) {
                    continue;
                }

                if (WORLD[i][j] == 0 && (i != row || j != col)) {
                    neighboursCount++;
                }
            }
        }

        return neighboursCount;
    }

    private static void clearConsole() {
        System.out.print(String.format("\033[H\033[2J"));
        System.out.flush();
    }

}
