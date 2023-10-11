package me.kevinmandeville;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kmandeville
 * @since NEXT_RELEASE_VERSION
 */
class DefaultCellStateStrategyImplTest {

    private DefaultCellStateStrategyImpl defaultCellStateStrategy = new DefaultCellStateStrategyImpl();

    @Test
    void testHowManyLiveNeighbors1() {
        // set up a test board
        //  - * - - -
        //  - * - - -
        //  - - * - -
        //  - - - - -
        //  - - - - -
        boolean[][] board = new boolean[5][5];
        board[0][1] = true;
        board[1][1] = true;
        board[2][2] = true;
        int num = defaultCellStateStrategy.howManyLiveNeighbors(board, 1, 1);
        Assertions.assertEquals(2, num);
    }

    @Test
    void testHowManyLiveNeighbors2() {
        // set up a test board
        //  - - - - -
        //  * * - - *
        //  - * - - -
        //  - - - - -
        //  - - - - -
        boolean[][] board = new boolean[5][5];
        board[1][0] = true;
        board[1][1] = true;
        board[1][4] = true;
        board[2][1] = true;
        GameBoard gameBoard = new GameBoard(board, board, defaultCellStateStrategy);
        System.out.println(gameBoard.createPrintableString());
        int num = defaultCellStateStrategy.howManyLiveNeighbors(board, 1, 1);
        Assertions.assertEquals(2, num);
    }

    @Test
    void testHowManyLiveNeighborsWithWrap() {
        // set up a test board
        //  - - - - -
        //  * * - - *
        //  - * - - -
        //  - - - - -
        //  - - - - -
        boolean[][] board = new boolean[5][5];
        board[1][0] = true;
        board[1][1] = true;
        board[1][4] = true;
        board[2][1] = true;
        GameBoard gameBoard = new GameBoard(board, board, defaultCellStateStrategy);
        System.out.println(gameBoard.createPrintableString());

        int num = defaultCellStateStrategy.howManyLiveNeighbors(board, 1, 0);
        Assertions.assertEquals(3, num);
    }

    @Test
    void testProcessCells1() {
        // set up a test board
        //  - - - - -
        //  - * * * -
        //  - - - - -
        //  - - - - -
        //  - - - - -
        boolean[][] board = new boolean[5][5];
        board[1][1] = true;
        board[1][2] = true;
        board[1][3] = true;
        GameBoard gameBoard = new GameBoard(board, board, defaultCellStateStrategy);
        System.out.println(gameBoard.createPrintableString());

        gameBoard.processCells();
        boolean[][] testBoard = gameBoard.getBoard();
        System.out.println(gameBoard.createPrintableString());
        // should be:
        //  - - * - -
        //  - - * - -
        //  - - * - -
        //  - - - - -
        //  - - - - -
        Assertions.assertFalse(testBoard[1][1]);
        Assertions.assertFalse(testBoard[1][3]);
        Assertions.assertTrue(testBoard[0][2]);
        Assertions.assertTrue(testBoard[2][2]);
        Assertions.assertTrue(testBoard[1][2]);
    }

    @Test
    void testProcessCells2() {
        // set up a test board
        //  - - * - -
        //  - - * - -
        //  - - * - -
        //  - - - - -
        //  - - - - -
        boolean[][] board = new boolean[5][5];
        board[0][2] = true;
        board[1][2] = true;
        board[2][2] = true;
        GameBoard gameBoard = new GameBoard(board, board, defaultCellStateStrategy);
        System.out.println(gameBoard.createPrintableString());

        gameBoard.processCells();
        boolean[][] testBoard = gameBoard.getBoard();
        System.out.println(gameBoard.createPrintableString());
        // should be:
        //  - - - - -
        //  - * * * -
        //  - - - - -
        //  - - - - -
        //  - - - - -
        Assertions.assertFalse(testBoard[0][2]);
        Assertions.assertFalse(testBoard[2][2]);
        Assertions.assertTrue(testBoard[1][1]);
        Assertions.assertTrue(testBoard[1][2]);
        Assertions.assertTrue(testBoard[1][3]);
    }

    @Test
    void testProcessCells3() {
        // set up a test board
        //  - - - - - -
        //  - - * * * -
        //  - * * * - -
        //  - - - - - -
        //  - - - - - -
        boolean[][] board = new boolean[6][6];
        board[1][2] = true;
        board[1][3] = true;
        board[1][4] = true;
        board[2][1] = true;
        board[2][2] = true;
        board[2][3] = true;
        GameBoard gameBoard = new GameBoard(board, board, defaultCellStateStrategy);
        System.out.println(gameBoard.createPrintableString());

        gameBoard.processCells();
        boolean[][] testBoard = gameBoard.getBoard();
        System.out.println(gameBoard.createPrintableString());
        // should be:
        //  - - - * - -
        //  - * - - * -
        //  - * - - * -
        //  - - * - - -
        //  - - - - - -
        Assertions.assertFalse(testBoard[1][2]);
        Assertions.assertFalse(testBoard[1][3]);
        Assertions.assertFalse(testBoard[2][2]);
        Assertions.assertFalse(testBoard[2][3]);
        Assertions.assertTrue(testBoard[0][3]);
        Assertions.assertTrue(testBoard[1][1]);
        Assertions.assertTrue(testBoard[1][4]);
        Assertions.assertTrue(testBoard[2][1]);
        Assertions.assertTrue(testBoard[2][4]);
        Assertions.assertTrue(testBoard[3][2]);
    }

    @Test
    void testBlockDoesntChange() {
        // set up a test board
        //  - - - - -
        //  - * * - -
        //  - * * - -
        //  - - - - -
        //  - - - - -
        boolean[][] board = new boolean[5][5];
        board[1][1] = true;
        board[1][2] = true;
        board[2][1] = true;
        board[2][2] = true;
        GameBoard gameBoard = new GameBoard(board, board, defaultCellStateStrategy);
        System.out.println(gameBoard.createPrintableString());

        gameBoard.processCells();
        boolean[][] testBoard = gameBoard.getBoard();
        System.out.println(gameBoard.createPrintableString());

        // should be:
        //  - - - - -
        //  - * * - -
        //  - * * - -
        //  - - - - -
        //  - - - - -
        Assertions.assertFalse(testBoard[0][0]);
        Assertions.assertFalse(testBoard[0][1]);
        Assertions.assertFalse(testBoard[0][2]);
        Assertions.assertFalse(testBoard[0][3]);
        Assertions.assertFalse(testBoard[0][4]);
        Assertions.assertFalse(testBoard[1][0]);
        Assertions.assertFalse(testBoard[1][3]);
        Assertions.assertFalse(testBoard[1][4]);
        Assertions.assertFalse(testBoard[2][0]);
        Assertions.assertFalse(testBoard[2][3]);
        Assertions.assertFalse(testBoard[2][4]);
        Assertions.assertTrue(testBoard[1][1]);
        Assertions.assertTrue(testBoard[1][2]);
        Assertions.assertTrue(testBoard[2][1]);
        Assertions.assertTrue(testBoard[2][2]);
    }
}
