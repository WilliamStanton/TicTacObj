package Modal.Board;

import Modal.Player;

public class BoardSpot {
    private int id;
    private Player player;

    public BoardSpot(int id) {
        // Initialize player for spot
        this.id = id;
        this.player = new Player(null, null, false);
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}