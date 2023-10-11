package me.kevinmandeville;

import java.util.Arrays;
import java.util.Random;

/**
 * @author kmandeville
 * @since 1.0
 */
public class GameBoard {

    private final CellStateStrategy cellStateStrategy;
    private boolean[][] board;
    private boolean[][] boardBuffer;

    public GameBoard(int widthAndHeightOfGrid, CellStateStrategy cellStateStrategy) {
        this.board = new boolean[widthAndHeightOfGrid][widthAndHeightOfGrid];
        this.boardBuffer = new boolean[widthAndHeightOfGrid][widthAndHeightOfGrid];
        this.cellStateStrategy = cellStateStrategy;
    }

    public GameBoard(boolean[][] board, boolean[][] boardBuffer, CellStateStrategy cellStateStrategy) {
        this.board = copyBoard(board);
        this.boardBuffer = copyBoard(boardBuffer);
        this.cellStateStrategy = cellStateStrategy;
    }

    public void setGridPosition(int x, int y, boolean value, boolean[][] boardToUpdate) {
        boardToUpdate[x][y] = value;
    }

    public String createPrintableString() {
        return createPrintableString(board);
    }

    public String createPrintableString(boolean[][] boardToPrint) {
        StringBuilder gridBuilder = new StringBuilder();
        int numRows = boardToPrint.length;
        int numCols = boardToPrint[0].length;

        // Top line
        gridBuilder.append("-".repeat((numCols * 2)));
        gridBuilder.append("\n");
        for (int i = 0; i < numRows; i++) {
            boolean[] booleans = boardToPrint[i];
            gridBuilder.append("|");
            for (int j = 0; j < numCols; j++) {
                boolean aBoolean = booleans[j];
                gridBuilder.append(aBoolean ? "*" : " ");
                gridBuilder.append("|");
            }
            // horizontal separator
            gridBuilder.append("\n");
            gridBuilder.append("-".repeat((numCols * 2)));
            gridBuilder.append("\n");
        }

        return gridBuilder.toString();
    }

    public void initialize(long percentOfCellsToStart) {
        Random random = new Random();

        int numberOfCells = (int) (((double) percentOfCellsToStart / 100) * (board.length * board[0].length));
        for (int i = 0; i < numberOfCells; i++) {
            int rndX = random.nextInt(board[0].length);
            int rndY = random.nextInt(board.length);
            setGridPosition(rndX, rndY, true, board);
            setGridPosition(rndX, rndY, true, boardBuffer);
        }
    }

    public void processCells() {
        // Need to evaluate all the cells to see which ones should die and which should become alive, and which stay the same
        cellStateStrategy.processCells(board, boardBuffer);
        board = boardBuffer;
        boardBuffer = copyBoard(board);
    }

    public boolean[][] getBoard() {
        return board;
    }

    public boolean[][] getBoardBuffer() {
        return boardBuffer;
    }

    private boolean[][] copyBoard(boolean[][] original) {
        boolean[][] copy = new boolean[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
}
