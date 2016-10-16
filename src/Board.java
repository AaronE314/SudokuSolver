import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

    private static int board[][];
    int[][] empty = new int[9][9];
    int[][] solution = new int[9][9];
    int[][] puzzle = new int[9][9];
    boolean isUnique = false;

    public Board(int[][] grid) {
        board = new int[9][9];
        puzzle = grid;
        board = puzzle;
        System.out.println("Puzzle");
        printBoard();
        System.out.println();
    }

    /**
     * Starts the Solve by calling the solve function twice to see if the Board given is Unique
     */
    public void start() {
        Solve(0, 0);
        Solve(0,0);
        if(isUnique){
            System.out.println("Unique");
        } else {
            System.out.println("Not Unique");
        }
    }

    /**
     * Solves A given board, from a starting position, Recursive loop
     * @param row int row that it is working on
     * @param col int column that it is working on
     * @return Boolean
     */
    public boolean Solve(int row, int col) {
        //checks to see if we reached the end of the board
        if (row > 8) {
            //prints final grid
            System.out.println("Solved Puzzle");
            printBoard();
            //returns true to exit function
            if(Arrays.deepEquals(solution,empty)){
                solution = board;
                board = puzzle;
            } else {
                if(Arrays.deepEquals(solution,board)){
                    isUnique = true;
                } else {
                    isUnique = false;
                }
            }
            return true;
        }
        //checks to see if the tile is a number
        if (board[row][col] != 0) {
            if(Next(row, col)){
                return true;
            }
        } else {
            //loops through all the possible values
            Integer[] randoms = generateRandomNumbers();
            for (int num = 0; num < 9; num++) {
                //checks if it works
                if (checkRow(row, randoms[num]) && checkCol(col, randoms[num]) && checkBox(row, col, randoms[num])) {
                    //if it does set the number and call the next tile
                    board[row][col] = randoms[num];
                    if (Next(row, col)) {
                        return true;
                    }
                }
            }
            //if it doesn't, reset back to 0
            board[row][col] = 0;
        }
        return false;
    }

    /**
     * Moves to the correct next row/column
     * @param row int current row
     * @param col int current column
     * @return Boolean
     */
    public boolean Next(int row, int col){
        if (col < 8) {
            if(Solve(row, col + 1)){
                return true;
            }
            //Solve(row, col + 1);
        } else {
            if (Solve(row + 1, 0)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks to see if a given number is in a row
     * @param row int current row
     * @param num int number to check
     * @return Boolean True if number is not in the row
     */
    protected boolean checkRow(int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if a given number is in a column
     * @param col int current column
     * @param num in number to check
     * @return Boolean True if number is not in the column
     */
    protected boolean checkCol(int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if a given number is in a 3x3 box
     * @param row int current row
     * @param col int current column
     * @param num int number to check
     * @return Boolean True if number is not in the column
     */
    protected boolean checkBox(int row, int col, int num) {
        row = (row / 3) * 3;
        col = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generates a list from 1 - 9 in a random order
     * @return the Integer array of the numbers
     */
    private Integer[] generateRandomNumbers() {
        ArrayList<Integer> randoms = new ArrayList<>();
        for(int i = 0; i<9; i++){
            randoms.add(i+1);
        }
        Collections.shuffle(randoms);

        return randoms.toArray(new Integer[9]);
    }


    /**
     * Prints out the the board
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[j][i]);
                if (j == 2 || j == 5) {
                    System.out.print(" ");
                }
            }
            if (i == 2 || i == 5) {
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();

    }
}