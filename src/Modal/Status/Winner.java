package Modal.Status;

import Modal.Board.BoardSpot;

import java.util.ArrayList;

public class Winner {
    private ArrayList<BoardSpot> boardSpots;
    private String winnerName;
    private boolean won;

    public Winner(ArrayList<BoardSpot> boardSpots) {
        this.boardSpots = boardSpots;
        this.winnerName = boardSpots.get(0).getPlayer().getName();
        this.won = true;
    }

    public Winner() {
        this.won = false;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public ArrayList<BoardSpot> getBoardSpots() {
        return boardSpots;
    }

    public void setBoardSpots(ArrayList<BoardSpot> boardSpots) {
        this.boardSpots = boardSpots;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}
