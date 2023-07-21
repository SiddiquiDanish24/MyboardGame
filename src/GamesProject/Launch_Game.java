package GamesProject;
import java.util.Random;
import java.util.Scanner;

class BoardGame {
    static char[][] board = new char[6][6];
    private char player1Mark;
    private char player2Mark;
    int player1Score = 0;
    int player2Score = 0;

    BoardGame() {
        player1Mark = 'x';
        player2Mark = 'o';
        initBoard();
    }

    // assigning and providing Empty space to entire board
    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // to display board
    static void dispBoard() {
        System.out.println("--------------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("--------------------------");
        }
    }

    // placing mark on board
    static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row < 6 && col >= 0 && col < 6) {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid position.");
        }
    }

    // Row Win Condition

    private void updateScore() {
        // Check rows
        for (int row = 0; row < 6; row++) {
            if (board[row][0] == player1Mark && board[row][1] == player1Mark && board[row][2] == player1Mark) {
                /*This line checks if the values in the cells board[row][0], board[row][1], and board[row][2] are all equal to
                player1Mark (which is 'x').If this condition is true, it means that player 1 has a series of 3 'x' marks in that row,
                so player1Score is incremented by 1.*/

                player1Score++;
                //This line increments the value of player1Score by 1. It keeps track of the number of
                // times player 1 has achieved a series of 3 'x' marks in a row.

            } else if (board[row][0] == player2Mark && board[row][1] == player2Mark && board[row][2] == player2Mark) {
                /*This line checks if the values in the cells board[row][0], board[row][1],and board[row][2] are all equal to player2Mark (which is 'o').
                 If this condition is true, it means that player 2 has a series of 3 'o' marks in that row, so player2 Score is incremented by 1.*/

                player2Score++;
                // increments the value of player2Score by 1.
                // It keeps track of the number of times player 2 has achieved a series of 3 'o' marks in a row.

                // Each iteration of the loop checks one row of the board for a winning condition, either for player 1 or player 2.
                // If a winning condition is found, the respective player's score is updated.
            }
        }

        // Col Win Condition

        // Check columns
        for (int col = 0; col < 6; col++) {
            if (board[0][col] == player1Mark && board[1][col] == player1Mark && board[2][col] == player1Mark) {
                player1Score++;
            } else if (board[0][col] == player2Mark && board[1][col] == player2Mark && board[2][col] == player2Mark) {
                player2Score++;
            }
        }
    }
}

abstract class  Helper {
    String name;
    char mark;

    abstract void makeMove(); // p1 user i/p ~~ p2 Ai i/p , both using same method by way is different.

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 6 && col >= 0 && col <= 6) {                           //within the boundary
            if (BoardGame.board[row][col] == ' ') {                                  //block u placing move is empty or not
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (BoardGame.board[row][col] == ' ') {
                    return false; // There is at least one empty cell
                }
            }
        }
        return true; // All cells are filled
    }
}


class player1 extends Helper {
    player1(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    void makeMove() {
        int row;
        int col;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter the Row and Coloum");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!isValidMove(row, col));  //condtn
        BoardGame.placeMark(row, col, mark);
    }
}

class player2 extends Helper {
    player2(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    void makeMove() {
        int row;
        int col;
        do {
            // code for AI//
            Random r = new Random();                //Random has a methodnextInt() which generate a random interger
            row = r.nextInt(6);             //  just need to tell it the boundry
            col = r.nextInt(6);


        } while (!isValidMove(row, col));

        BoardGame.placeMark(row, col, mark);
    }
}
public class Launch_Game {
    public static void main(String[] args) {

        BoardGame bg = new BoardGame();
        player1 p1 = new player1("Oppenheimer", 'x');
        player2 p2 = new player2("Liam", 'o');
        Helper cp;
        cp = p1;

        while (true) {
            System.out.println(cp.name + " Turn");
            cp.makeMove();
            BoardGame.dispBoard();

            if (isBoardFull()) {
                System.out.println("The board is full.");

                if (bg.player1Score > bg.player2Score) {
                    System.out.println("Player 1 wins!");
                } else if (bg.player1Score < bg.player2Score) {
                    System.out.println("Player 2 wins!");
                } else {
                    System.out.println("It's a tie!");
                }

                break;
            }

            if (cp == p1) {
                cp = p2;
            } else {
                cp = p1;
            }
        }
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (BoardGame.board[row][col] == ' ') {
                    return false; // There is at least one empty cell
                }
            }
        }
        return true; // All cells are filled
    }
}

