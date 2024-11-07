package tictactoelib.board;

import tictactoelib.Player;
import tictactoelib.game.GameException;

public class BoardSpot {
    private final int id;
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

    /**
     * Spot Taken
     * @return true if spot taken, false if available
     */
    public boolean isTaken() {
        return player.getName() != null;
    }

    /**
     * Sets player for spot
     * @param player player to place in spot
     * @throws GameException spot already taken
     */
    public void setPlayer(Player player) throws GameException {
        if (!isTaken())
            this.player = player;
        else
            throw new GameException("Board spot has already been taken");
    }

    /**
     * To String
     * @return ID (String) of spot
     */
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
