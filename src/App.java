import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class App {
    static Scanner input = new Scanner(System.in);

    static Set<Integer> emptyPositions = new HashSet<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                { '_', '_', '_' },
                { '_', '_', '_' },
                { ' ', ' ', ' ' }
        };

        boolean gameOver = false;

        for (int i = 1; i < 10; i++) {
            emptyPositions.add(i);
        }

        while (!gameOver) {
            playerMove(gameBoard);
            gameOver = isGameOver(gameBoard);
            if (gameOver) {
                break;
            }

            computerMove(gameBoard);
            gameOver = isGameOver(gameBoard);
            if (gameOver) {
                break;
            }

        }
    }

    public static boolean isValidMove(int move, char[][] gameboard) {
        if (!emptyPositions.contains(move)) {
            return false;
        } else {
            emptyPositions.remove(move);
            return true;
        }
    }

    public static void playerMove(char[][] gameBoard) {
        System.out.print("Please make a move. (1-9): ");

        int move = input.nextInt();

        boolean result = isValidMove(move, gameBoard);

        while (!result) {
            System.out.print("Invalid move. Try Again: ");
            move = input.nextInt();
            result = isValidMove(move, gameBoard);
        }

        System.out.println("Player placed at position " + move);

        updateBoard(move, 1, gameBoard);
    }

    public static void computerMove(char[][] gameBoard) {
        Random rand = new Random();
        int move = rand.nextInt(9) + 1;
        boolean result = isValidMove(move, gameBoard);

        while (!result) {
            move = rand.nextInt(9) + 1;
            result = isValidMove(move, gameBoard);
        }

        System.out.println("Computer placed at position " + move);
        updateBoard(move, 2, gameBoard);
    }

    public static boolean isGameOver(char[][] gameBoard) {
        // horizontal check
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] != '_' && gameBoard[i][0] != ' ' && gameBoard[i][0] == gameBoard[i][1]
                    && gameBoard[i][1] == gameBoard[i][2]) {
                System.out.println(gameBoard[i][0] == 'x' ? "Player Wins!" : "You lost!");
                return true;
            }
        }

        // vertical check
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] != '_' && gameBoard[0][i] != ' ' && gameBoard[0][i] == gameBoard[1][i]
                    && gameBoard[1][i] == gameBoard[2][i]) {
                System.out.println(gameBoard[0][i] == 'x' ? "Player Wins" : "You lost!");
                return true;
            }
        }

        // diagonal check (first diagonal)
        if (gameBoard[0][0] != '_' && gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1]
                && gameBoard[1][1] == gameBoard[2][2]) {
            System.out.println(gameBoard[0][0] == 'x' ? "Player Wins" : "You lost!");
            return true;
        }

        // diagonal check (second diagonal)
        if (gameBoard[0][2] != '_' && gameBoard[0][2] != ' ' && gameBoard[0][2] == gameBoard[1][1]
                && gameBoard[1][1] == gameBoard[2][0]) {
            System.out.println(gameBoard[0][2] == 'x' ? "Player Wins" : "You lost!");
            return true;
        }

        // tie check
        boolean allFilled = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Only check columns 0, 2, 4
                if (gameBoard[i][j] == '_' || gameBoard[i][j] == ' ') {
                    allFilled = false; // If there's an empty space, it's not a tie
                    break;
                }
            }
            if (!allFilled)
                break; // If any row has an empty space, stop checking
        }

        if (allFilled) {
            System.out.println("Tie!");
            return true; // If all cells are filled and no winner, it's a tie
        }

        return false;
    }

    public static void printBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (int j = 0; j < 3; j++) {
                System.out.print(row[j]);
                if (!(j == 2)) {
                    System.out.print('|');
                }
            }

            System.out.println();
        }
    }

    public static void updateBoard(int position, int player, char[][] gameBoard) {
        char character;

        if (player == 1) {
            character = 'x';
        } else {
            character = 'o';
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = character;
                printBoard(gameBoard);
                break;
            case 2:
                gameBoard[0][1] = character;
                printBoard(gameBoard);
                break;
            case 3:
                gameBoard[0][2] = character;
                printBoard(gameBoard);
                break;
            case 4:
                gameBoard[1][0] = character;
                printBoard(gameBoard);
                break;
            case 5:
                gameBoard[1][1] = character;
                printBoard(gameBoard);
                break;
            case 6:
                gameBoard[1][2] = character;
                printBoard(gameBoard);
                break;
            case 7:
                gameBoard[2][0] = character;
                printBoard(gameBoard);
                break;
            case 8:
                gameBoard[2][1] = character;
                printBoard(gameBoard);
                break;
            case 9:
                gameBoard[2][2] = character;
                printBoard(gameBoard);
                break;
            default:
                break;
        }
    }
}
