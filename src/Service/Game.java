package Service;

import Modal.Board.Board;
import Modal.Board.BoardSpot;
import Modal.Player;
import Modal.Status;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Game API
 */
public class Game {
    private final Board board;
    private final Player p1;
    private final Player p2;
    private Status status;

    // Initialize board
    public Game() {
        this.board = new Board();
        p1 = new Player("Player 1", "O", true);
        p2 = new Player("Player 2", "X", false);
        status = Status.INCOMPLETE;
    }

    /**
     * Updates the game by setting spot and setting next player
     * @throws Exception game complete
     * @param id the board spot id
     */
    public void next(int id) throws Exception {
        if (status == Status.INCOMPLETE) {
            // Update chosen board spot for current player
            getBoardSpot(id).setPlayer(getNextPlayer());
            setNextPlayer(); // Conclude move and enable next player to move
            updateStatus();
        }
        else
            throw new Exception("Game is complete, cannot continue.");
    }

    /**
     * Checks if there is a winner
     */
    public void updateStatus() {
        // Get board spots
        var bs = board.getBoardSpots();
        ArrayList<BoardSpot> winningBoardSpots = new ArrayList<>();

        // Check Vertical (each column)
        for (int row = 0; row < bs[0].length; row++) {
            boolean colFlag = true;
            var p = bs[0][row];
            for (int col = 0; col < bs.length; col++) {
                // Get first index in column to compare against
                if (!bs[col][row].getPlayer().equals(p.getPlayer())) {
                    colFlag = false;
                    winningBoardSpots.clear();
                    break; // terminate loop
                } else {
                    winningBoardSpots.add(bs[col][row]);
                }
            }
            if (colFlag) {
                winningBoardSpots.add(p);
                board.setWinningSpots(winningBoardSpots);
                status = Status.WON;
                return;
            }
        }

        // Check horizontal (each row)
        for (int col = 0; col < bs.length; col++) {
            boolean colFlag = true;
            var p = bs[col][0];
            for (int row = 0; row < bs[0].length; row++) {
                // Get first index in row to compare against
                if (!bs[col][row].getPlayer().equals(p.getPlayer())) {
                    colFlag = false;
                    winningBoardSpots.clear();
                    break; // terminate loop
                } else {
                    winningBoardSpots.add(bs[col][row]);
                }
            }
            if (colFlag) {
                winningBoardSpots.add(p);
                board.setWinningSpots(winningBoardSpots);
                status = Status.WON;
                return;
            }
        }

        // Check vertical
        if (bs[0][0].getPlayer().equals(bs[1][1].getPlayer())
                && bs[1][1].getPlayer().equals(bs[2][2].getPlayer())) {
            status = Status.WON;
            board.setWinningSpots(new ArrayList<BoardSpot>(Arrays.asList(bs[0][0], bs[1][1], bs[2][2])));
            return;
        }
        if (bs[2][0].getPlayer().equals(bs[1][1].getPlayer())
                && bs[1][1].getPlayer().equals(bs[0][2].getPlayer())) {
            status = Status.WON;
            board.setWinningSpots(new ArrayList<BoardSpot>(Arrays.asList(bs[2][0], bs[1][1], bs[0][2])));
            return;
        }

        // Check tie if status hasnt yet been chosen
        if (status == Status.INCOMPLETE) {
            status = Status.TIED; // temporary set tie
            for (int i = 0; i < bs.length; i++) {
                for (int j = 0; j < bs[0].length; j++) {
                    // Check if any element is available
                    if (bs[i][j].getPlayer().getName() == null) {
                        status = Status.INCOMPLETE; // change back to incomplete if tie not found
                        break;
                    }
                }
            }
        }
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
        return p1.isTurn() ? p1 : p2;
    }

    /**
     * Gets the complete board spot object from the spot id
     * @param id the spot id
     * @throws Exception board spot not found
     * @return board spot object
     */
    private BoardSpot getBoardSpot(int id) throws Exception {
        var bs = board.getBoardSpots();
        for (int i = 0; i < bs.length; i++) {
            for (int j = 0; j < bs[0].length; j++) {
                if (bs[i][j].getId() == id)
                    return bs[i][j];
            }
        }

        // Not found, throw exception
        throw new Exception("Board spot not found");
    }

    /**
     * Gets the complete board
     * @return complete board
     */
    public String getCompleteBoard() {
        return board.toString();
    }

    /**
     * Gets the game status
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the winning player
     * @throws Exception winner not found
     * @return winner
     */
    public Player getWinner() throws Exception {
        try {
            return board.getWinningSpots().get(0).getPlayer();
        } catch (Exception e) {
            throw new Exception("Winner has not yet been concluded!");
        }
    }
}
