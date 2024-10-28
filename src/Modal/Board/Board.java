package Modal.Board;

import java.util.ArrayList;

/**
 * Represents a board
 */
public class Board {
    private static int id = 1;
    private BoardSpot[][] boardSpots;
    private ArrayList<BoardSpot> winningSpots;

    public Board() {
        // Initialize all board spots
        this.boardSpots = new BoardSpot[3][3];
        for (int i = 0; i < boardSpots.length; i++) {
            for (int j = 0; j < boardSpots[0].length; j++) {
                boardSpots[i][j] = new BoardSpot(id);
                id++; // increment id
            }
        }
    }

    public BoardSpot[][] getBoardSpots() {
        return boardSpots;
    }

    public ArrayList<BoardSpot> getWinningSpots() {
        return winningSpots;
    }

    public void setWinningSpots(ArrayList<BoardSpot> winningSpots) {
        this.winningSpots = winningSpots;
    }

    /**
     * Generates complete board
     * @return complete board
     */
    @Override
    public String toString() {
        String completeBoard = "";

        // Build the complete board
        for (int i = 0; i < boardSpots.length; i++) {
            for (int j = 0; j < boardSpots[0].length; j++) {
                if (boardSpots[i][j].getPlayer().getName() != null) {
                    completeBoard += boardSpots[i][j].getPlayer().getSymbol();
                }
                else
                    completeBoard += boardSpots[i][j].getId();
            }
            completeBoard += "\n";
        }

        // return completed board
        return completeBoard;
    }
}
