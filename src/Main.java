import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int puzzle[][] ;
        puzzle = readBoard("Puzzle.txt");
        Board board = new Board(puzzle);
        board.start();
    }

    /**
     * load's in the puzzle from a .txt file and doesn't worry about spaces or other chars
     * @param file String, The txt file that the puzzle is stored in, 0's being blank spots entered as (filename.txt)
     * @return An int array of the puzzle
     * @throws FileNotFoundException
     */
    private static int[][] readBoard(String file) throws FileNotFoundException {
        int puzzle[][] = new int[9][9];
        Scanner fin = new Scanner(new FileReader(file));
        String line;
        int lineIndex = 0, columnIndex=0;
        while (fin.hasNextLine()){
            line = fin.nextLine();
            if(line.equals("") || line.contains("+") || line.contains("-")){
                line = fin.nextLine();
            }
            for(int letterIndex = 0; letterIndex<line.length(); letterIndex++){
                if(Character.isDigit(line.charAt(letterIndex))){
                    if(columnIndex>8){
                        lineIndex++;
                        columnIndex=0;
                    }
                    puzzle[columnIndex][lineIndex] = Character.getNumericValue(line.charAt(letterIndex));
                    columnIndex++;
                }
            }
            lineIndex++;
            columnIndex = 0;
        }
        return puzzle;
    }
}
