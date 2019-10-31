package edu.bloomu.tic_tac_toe;

/**
 * TIC-TAC-TOE RULES:
 *
 * - Tic-Tac-Toe is played in a 3x3 grid.
 *
 * - The object of Tic Tac Toe is to get three in a row.
 *
 * - The first player is known as 'X' and the second is 'O'.
 *
 * - Players alternate placing X's and O's on the game board until either opponent has
 *   three in a row or all nine squares are filled. 'X' always goes first, and in the
 *   event that no one has three in a row, the game is called a stalemate.
 *
 * @author Lennox Haynes
 */

class TicTacToe {

    private int[][] ticTacToeGrid; // Tic-Tac-Toe Board.
    private static int gridSize = 3; // #x# Size of the Board.
    private int turn; // The player whose turn it is.

    TicTacToe() {
        ticTacToeGrid = new int [gridSize][gridSize]; // Initializes the game board
        turn = 1; // Starts on player ones turn
    }

    /**
     * Return the size of the row/column of the game grid.
     */
    int getGridSize(){
        return gridSize; // #x# Size of Grid
    }

    /**
     * Returns the player whose turn it is.
     */
    int getTurn(){
        int whoseTurn = turn;
        return (whoseTurn == 1) ? 2:1; // Returns 1 for 'player 1' and 2 for 'player 2'.
    }

    /**
     * Returns a copy of the ticTacToeGrid
     */
    int[][] gameGrid() {
        return ticTacToeGrid.clone();
    }

    /**
     * The user-selected spot of where the want to place their X/O.
     */
    void move(int row, int col){
        // If the selected button is at row, col is equal to 0...
        if (ticTacToeGrid[row][col] == 0) {
            // Changes the int at row, col to X/O depending on the turn
            ticTacToeGrid[row][col] = turn;

            // If turn is equal to 1 change to 2 and vice-versa
            turn = (turn == 1) ? 2:1;
        }
    }

    /**
     * Checks all possible win conditions of the game.
     */
    boolean isWon(){

        for (int i = 0; i < ticTacToeGrid.length; i++) {
            if (
                    // Checks Horizontal Win Conditions.
                    (ticTacToeGrid[i][0] == ticTacToeGrid[i][1] && ticTacToeGrid[i][0] ==
                            ticTacToeGrid[i][2] && ticTacToeGrid[i][0] != 0)
                    ||
                    // Checks Vertical Win Conditions.
                    (ticTacToeGrid[0][i] == ticTacToeGrid[1][i] && ticTacToeGrid[0][i] ==
                            ticTacToeGrid[2][i] && ticTacToeGrid[0][i] != 0)
                    ||
                    // Checks Top-Left to Bottom-Right Win Condition.
                    (ticTacToeGrid[0][0] == ticTacToeGrid[1][1] && ticTacToeGrid[0][0] ==
                            ticTacToeGrid[2][2] && ticTacToeGrid[0][0] != 0)
                    ||
                    // Checks Bottom-Left to Top-Right Win Condition.
                    (ticTacToeGrid[0][2] == ticTacToeGrid[1][1] && ticTacToeGrid[0][2] ==
                            ticTacToeGrid[2][0] && ticTacToeGrid[0][2] != 0))
            {
                return true; // If a player wins (has 3 in a row).
            }
        }
        return false; // If neither player has 3 in a row.
    }

    /**
     * Iterates over the game board to see if there are any empty spaces/possible moves
     * to be made.
     *
     * @return true: If there are still moves that can be made.
     *         false: If there are no moves that can be made.
     */
    boolean stalemate() {
        for (int i = 0; i < ticTacToeGrid.length; i++) {
            for (int j = 0; j < ticTacToeGrid[i].length; j++) {
                if (gameGrid()[i][j] == 0 && !isWon()) {
                    return false; // If there are no possible moves.
                }
            }
        }
        return true; // If there are still possible moves.
    }
}
