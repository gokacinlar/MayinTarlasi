import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowSize;
    int colSize;
    char[][] board;
    char[][] mineLocations;

    public MineSweeper(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.board = new char[rowSize][colSize];
        this.mineLocations = new char[rowSize][colSize];
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                board[i][j] = '-';
                mineLocations[i][j] = '-';
            }
        }
    }

    private void placeMines() {
        int mineCount = rowSize * colSize / 4;
        Random random = new Random();

        for (int i = 0; i < mineCount; i++) {
            int row = random.nextInt(rowSize);
            int col = random.nextInt(colSize);

            // Check if there is already a mine at the chosen location
            while (mineLocations[row][col] == '*') {
                row = random.nextInt(rowSize);
                col = random.nextInt(colSize);
            }

            mineLocations[row][col] = '*';
        }
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (mineLocations[i][j] != '*') {
                    int count = countAdjacentMines(i, j);
                    mineLocations[i][j] = (char) (count + '0');
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rowSize && j >= 0 && j < colSize && mineLocations[i][j] == '*') {
                    count++;
                }
            }
        }

        return count;
    }

    public void printBoard() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMineLocations() {
        System.out.println("Mayınların Konumu");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                System.out.print(mineLocations[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz.");
        printBoard();

        while (true) {
            System.out.print("Satır Giriniz: ");
            int row = scanner.nextInt();
            System.out.print("Sütun Giriniz: ");
            int col = scanner.nextInt();

            if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
                System.out.println("Geçersiz bir konum girdiniz, lütfen tekrar deneyin.");
                continue;
            }

            if (mineLocations[row][col] == '*') {
                System.out.println("Oyunu Kaybettiniz!");
                break;
            } else {
                board[row][col] = mineLocations[row][col];
                printBoard();

                boolean hasWon = checkWin();
                if (hasWon) {
                    System.out.println("Oyunu Kazandınız!");
                    break;
                }
            }
        }

        scanner.close();
    }

    private boolean checkWin() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (board[i][j] == '-' && mineLocations[i][j] != '*') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MineSweeper game = new MineSweeper(3, 3);
        game.printMineLocations();
        game.playGame();
    }
}