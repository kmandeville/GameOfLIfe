package me.kevinmandeville;

import java.util.Scanner;

/**
 * @author kmandeville
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // how big of a grid?
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the game board: ");
        int boardSize = scanner.nextInt();

        System.out.print("Enter the length of the pause for the game loop (in seconds): ");
        int pauseLength = scanner.nextInt();

        System.out.print(
            "Enter the percentage of the game board for starting number of cells (10% of board, enter 10). The lower the better ");
        int percentOfCellsToStart = scanner.nextInt();

        // Close the scanner to prevent resource leaks
        scanner.close();

        Game game = new Game(boardSize, pauseLength, percentOfCellsToStart);
        game.start();

        // choose number of alive cells to start with (must be smaller than x % of the total number of spaces available.
        // Total spaces available is x * y

        // Randomly populate grid with alive cells based on number above
        // use Random number generator twice, once between 0 -> x.max and again for 0 -> y.max

        // game loop
        // look through game board and determine based on rules which cells suddenly become alive and which ones die
        // cell is "born"" if it has EXACTLY 3 neighbors, cell stays alive as long as it has 2 or 3 neighbors, dies otherwise

        // change cells
        // print board
        // check end game conditions (no cells changed, all cells are dead, check if pattern flips back and forth (how?))
        // pause based on how long each turn should take
        // end loop
    }


}