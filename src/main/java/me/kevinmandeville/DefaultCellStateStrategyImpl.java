package me.kevinmandeville;

/**
 * Strategy that will do the normal B3/S23 strategy. New cells are born if surrounded by 3 cells. Cells stay alive if
 * surrounded by 2 or 3 cells. Cells die for any other state.
 *
 * @author kmandeville
 * @since 1.0
 */
public class DefaultCellStateStrategyImpl implements CellStateStrategy {

    /**
     * Method processes the full board, updates the buffer board, returns the buffer board
     *
     * @param board
     * @param bufferBoard
     */
    @Override
    public void processCells(boolean[][] board, boolean[][] bufferBoard) {
        int numRows = board.length;
        int numCols = board[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int numberOfLiveNeighbors = howManyLiveNeighbors(board, i, j);
                // if cell is alive in non buffer board
                if (board[i][j]) {
                    if (numberOfLiveNeighbors != 2 && numberOfLiveNeighbors != 3) {
                        // cell dies if it doesn't have 2 or 3 alive neighbors
                        bufferBoard[i][j] = false;
                    }
                }
                // cell is NOT alive
                else {
                    if (numberOfLiveNeighbors == 3) {
                        bufferBoard[i][j] = true;
                    }
                }
            }
        }
    }
}
