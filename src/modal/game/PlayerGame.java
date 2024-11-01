package modal.game;

/**
 * Player Game implementation, Player vs Player
 */
public class PlayerGame extends Game {

    /**
     * Player Move
     * @param id the board spot id
     * @throws GameException game complete/spot taken
     */
    @Override
    public void next(int id) throws GameException {
        if (getStatus() == GameStatus.INCOMPLETE) {
            // Update chosen board spot for current player
            try {
                getBoardSpot(id).setPlayer(getNextPlayer()); // Update chosen board spot for current player
            } catch(GameException e) {
                throw new GameException(e.getMessage());
            }
            setNextPlayer(); // Conclude move and enable next player to move (player)
            updateStatus(); // Checks if there is a game winner
        }
        else
            throw new GameException("Game is complete, cannot continue."); // Game has already been completed
    }
}
