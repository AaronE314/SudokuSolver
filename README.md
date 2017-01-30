# SudokuSolver
A Program that will take a Sudoku Puzzle from a text file and then print out the solution.

The program uses a breadth first recursive backtracking algorithm.

The algorithm works by starting on the top left of the sudoku grid then inputing the first value that works.

it will then call the function again on the next tile and do the same thing.

if is reaches a point where there are no valid numbers, the program will go back to the previous tile and try for the next value that worked.
