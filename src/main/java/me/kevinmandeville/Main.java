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
    }


}