package Service;

import Modal.Board.Board;
import Modal.Board.BoardSpot;
import Modal.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class GameService {
    private Board board;
    private Player p1;
    private Player p2;

    // Initialize board
    public GameService() {
        this.board = new Board();
        p1 = new Player("Player 1", "O", true);
        p2 = new Player("Player 2", "X", false);
    }

    /**
     * Updates the game by setting spot and setting next player
     * @param id the spot id
     */
    public void updateGame(int id) {
        // Get board spot
        var spot = getBoardSpot(id);

        // Update board spot for current player
        spot.setPlayer(getNextPlayer());

        // Update next player
        setNextPlayer();
    }

    /**
     * Checks if there is a winner
     * @return true/false
     */
    public boolean checkWin() {
        // Get board spots
        boolean won = false;
        var bs = board.getBoardSpots();

        // Check Vertical (each column)
        for (int row = 0; row < bs[0].length; row++) {
            boolean colFlag = true;
            for (int col = 0; col < bs.length; col++) {
                // Get first index in column to compare against
                var p = bs[0][row].getPlayer();
                if (!bs[col][row].getPlayer().equals(p)) {
                    colFlag = false;
                    break; // terminate loop
                }
            }
            if (colFlag)
                won = true;
        }

        // Check horizontal (each row)
        for (int col = 0; col < bs.length; col++) {
            boolean colFlag = true;
            for (int row = 0; row < bs[0].length; row++) {
                // Get first index in row to compare against
                var p = bs[col][0].getPlayer();
                if (!bs[col][row].getPlayer().equals(p)) {
                    colFlag = false;
                    break; // terminate loop
                }
            }
            if (colFlag)
                won = true;
        }

        // Check vertical
        if (bs[0][0].getPlayer().equals(bs[1][1].getPlayer())
                && bs[1][1].getPlayer().equals(bs[2][2].getPlayer()))
            won = true;
        if (bs[2][0].getPlayer().equals(bs[1][1].getPlayer())
                && bs[1][1].getPlayer().equals(bs[0][2].getPlayer()))
            won = true;



        // Return winflag
        return won;
    }

    /**
     * Sets the player for the next turn
     */
    private void setNextPlayer() {
        // If P1 just went, make P2 next turn
        if (p1.isTurn()) {
            p1.setTurn(false);
            p2.setTurn(true);
        }
        // If P2 just went, make P1 next turn
        else {
            p1.setTurn(true);
            p2.setTurn(false);
        }
    }

    /**
     * Gets the player for the next turn
     * @return player for next turn
     */
    public Player getNextPlayer() {
        if (p1.isTurn()) {
            return p1;
        }
        else {
            return p2;
        }
    }

    /**
     * Gets the board spot object from the spot id
     * @param id the spot id
     * @return board spot object
     */
    private BoardSpot getBoardSpot(int id) {
        var bs = board.getBoardSpots();
        for (int i = 0; i < bs.length; i++) {
            for (int j = 0; j < bs[0].length; j++) {
                if (bs[i][j].getId() == id)
                    return bs[i][j];
            }
        }

        // No board spot found
        return null;
    }

    /**
     * Gets the complete board
     * @return complete board
     */
    public String getCompleteBoard() {
        return board.toString();
    }
}
