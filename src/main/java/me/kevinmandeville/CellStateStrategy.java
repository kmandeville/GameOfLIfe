package me.kevinmandeville;

/**
 * @author kmandeville
 * @since NEXT_RELEASE_VERSION
 */
public interface CellStateStrategy {

    void processCells(boolean[][] board, boolean[][] bufferBoard);

    default int howManyLiveNeighbors(boolean[][] board, int i, int j) {
        int numOfLiveNeighbors = 0;
        // Check the 8 cells surrounding the coordinates passed in for other live (true) cells
        // coordinates are relative to the passed in coordinates.
        // [-1, -1][-1,0][-1, 1]
        // [0, -1]  [0,0] [0, 1]
        // [1, -1]  [1,0] [1, -1]
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                // have to account for if the -1 or the +1 will have to wrap to other side of board
                int row = calcCoordinate(board.length, i + k);
                int col = calcCoordinate(board[0].length, j + l);
                if (board[row][col] && !(k == 0 && l == 0)) {
                    numOfLiveNeighbors++;
                }
            }
        }

        return numOfLiveNeighbors;
    }

    default int calcCoordinate(int length, int position) {
        if (position < 0) {
            return length - 1;
        }
        if (position == length) {
            return 0;
        }
        return position;
    }
}
