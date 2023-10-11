package me.kevinmandeville;

/**
 * @author kmandeville
 * @since 1.0
 */
public class Game {

    private final int boardSize;
    private final int pauseLength;
    private final int percentOfCellsToStart;
    private boolean isRunning = true;
    private GameBoard gameBoard;

    public Game(int boardSize, int pauseLength, int percentOfCellsToStart) {
        this.boardSize = boardSize;
        this.pauseLength = pauseLength;
        this.percentOfCellsToStart = percentOfCellsToStart;
    }

    public void start() throws InterruptedException {
        initializeGame();

        gameLoop();
    }

    private void gameLoop() throws InterruptedException {
        while (isRunning) {
            renderBoard(gameBoard);
            gameBoard.processCells();
            //checkEndGameConditions(gameBoard);
            waitForShortDelay(pauseLength);
            clearConsole();
        }
    }

    private void renderBoard(GameBoard gameBoard) {
        System.out.println(gameBoard.createPrintableString());
    }

    private void initializeGame() {
        // Allow player to choose the strategy at some poing?
        gameBoard = new GameBoard(boardSize, new DefaultCellStateStrategyImpl());

        gameBoard.initialize(percentOfCellsToStart);
    }

    public void clearConsole() {
        // Use ANSI escape codes to clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void waitForShortDelay(int gameDelayInSec) throws InterruptedException {
        Thread.sleep(1000L * gameDelayInSec); // Sleep for 1 second
    }
}
