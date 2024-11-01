package modal.game;

import java.util.Random;

/**
 * Computer Game implementation, Player vs AI
 */
public class ComputerGame extends Game {
    /**
     * Player Move
     * @param id the board spot id
     * @throws GameException game complete/incorrect player turn
     */
    @Override
    public void next(int id) throws GameException {
        var status = getStatus(); // game status
        var nextPlayer = getNextPlayer(); // next player

        // Game is still in progress and it is player 1's turn
        if (status == GameStatus.INCOMPLETE && !nextPlayer.equals(getP2())) {
            try {
                getBoardSpot(id).setPlayer(nextPlayer); // Update chosen board spot for current player
            } catch(GameException e) {
                throw new GameException(e.getMessage());
            }
            setNextPlayer(); // Conclude move and enable next player (AI) to move
            updateStatus(); // Checks if there is a game winner
        }
        else if (status == GameStatus.WON || status == GameStatus.TIED)
            throw new GameException("Game is complete, cannot continue."); // Game has already been completed
        else
            throw new GameException("Incorrect player turn"); // Computers turn, not player
    }

    /**
     * AI Move
     */
    public void next() throws GameException {
        boolean found = false; // init flag
        Random rand = new Random(); // init rand

        // Find an available spot on board to place computer
        while (!found) {
            var spot = getBoardSpot(rand.nextInt(8)+1); // choose from spots 1-9
            if (!spot.isTaken()) {
                found = true; // update flag
                spot.setPlayer(getNextPlayer()); // Update board spot for current player
                setNextPlayer(); // Update next player
                updateStatus(); // Checks if there is a game winner
            }
        }
    }

    /**
     * Returns true if current turn is computer turn
     * @return true/false if computer turn or not
     */
    public boolean computerTurn() {
        return getNextPlayer().equals(getP2());
    }
}
