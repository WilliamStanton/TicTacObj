package tictactoelib.game;

import tictactoelib.board.Board;
import tictactoelib.board.BoardSpot;
import tictactoelib.Player;

import java.util.ArrayList;

/**
 * Game API
 */
public abstract class Game {
    private final Board board;
    private final Player p1;
    private final Player p2;
    private GameStatus gameStatus;

    // Initialize board
    public Game() {
        this.board = new Board();
        this.p1 = new Player("Player 1", "O", true);
        this.p2 = new Player("Player 2", "X", false);
        gameStatus = GameStatus.INCOMPLETE;
    }

    // Initialize board
    public Game(String player1Name, String player2Name) {
        this.board = new Board();
        this.p1 = new Player(player1Name, "O", true);
        this.p2 = new Player(player2Name, "X", false);
        gameStatus = GameStatus.INCOMPLETE;
    }

    /**
     * Updates the game by setting spot and setting next player
     * @param id the board spot id
     */
    public abstract void next(int id) throws GameException;

    /**
     * Checks if there is a winner
     */
    protected void updateStatus() {
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
                gameStatus = GameStatus.WON;
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
                gameStatus = GameStatus.WON;
                return;
            }
        }

        // check horizontal (top right -> bottom left)
        boolean horizontalFlag = true; // horizontal flag
        winningBoardSpots.add(bs[0][bs[0].length-1]); // base pos to check against
        for (int i = 0; i < bs.length; i++) {
            if (bs[i][bs[0].length-1 - i].getPlayer().equals(winningBoardSpots.get(0).getPlayer())) {
                winningBoardSpots.add(bs[i][bs[0].length-1 - i]);
            } else {
                winningBoardSpots.clear();
                horizontalFlag = false;
                break;
            }
        }

        // horizontal (top right -> bottom left) success
        if (horizontalFlag) {
            board.setWinningSpots(winningBoardSpots);
            gameStatus = GameStatus.WON;
            return;
        }

        // check horizontal (top left -> bottom right)
        horizontalFlag = true; // reset horizontal flag
        winningBoardSpots.add(bs[0][0]); // base pos to check against
        for (int i = 0; i < bs.length; i++) {
            if (bs[i][i].getPlayer().equals(winningBoardSpots.get(0).getPlayer())) {
                winningBoardSpots.add(bs[i][i]);
            } else {
                winningBoardSpots.clear();
                horizontalFlag = false;
                break;
            }
        }

        // horizontal (top left -> bottom right) success
        if (horizontalFlag) {
            board.setWinningSpots(winningBoardSpots);
            gameStatus = GameStatus.WON;
            return;
        }

        // Check tie if status hasn't yet been chosen
        if (gameStatus == GameStatus.INCOMPLETE) {
            gameStatus = GameStatus.TIED; // temporary set tie
            for (int i = 0; i < bs.length; i++) {
                for (int j = 0; j < bs[0].length; j++) {
                    // Check if any element is available
                    if (bs[i][j].getPlayer().getName() == null) {
                        gameStatus = GameStatus.INCOMPLETE; // change back to incomplete if tie not found
                        break;
                    }
                }
            }
        }
    }

    /**
     * Sets the player for the next turn
     */
    protected void setNextPlayer() throws GameException {
        // If P1 just went, make P2 next turn
        if (p1.isTurn() && gameStatus == GameStatus.INCOMPLETE) {
            p1.setTurn(false);
            p2.setTurn(true);
        }
        // If P2 just went, make P1 next turn
        else if (p2.isTurn() && gameStatus == GameStatus.INCOMPLETE){
            p1.setTurn(true);
            p2.setTurn(false);
        }
        // Else game over
        else {
            throw new GameException("Game has already been concluded");
        }
    }

    /**
     * Gets the player for the next turn
     * @return player for next turn
     */
    public Player getNextPlayer() throws GameException {
        if (gameStatus == GameStatus.INCOMPLETE)
            return p1.isTurn() ? p1 : p2;
        else
            throw new GameException("Game has already been concluded");
    }

    /**
     * Gets the complete board spot object from the spot id
     * @param id the spot id
     * @return board spot object
     */
    public BoardSpot getBoardSpot(int id) throws GameException {
        var bs = board.getBoardSpots();
        for (int i = 0; i < bs.length; i++) {
            for (int j = 0; j < bs[0].length; j++) {
                if (bs[i][j].getId() == id)
                    return bs[i][j];
            }
        }
        // Not found, throw exception
        throw new GameException("Board spot not found");
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
    public GameStatus getStatus() {
        return gameStatus;
    }

    /**
     * Gets Player 1
     * @return p1
     */
    protected Player getP1() {
        return p1;
    }

    /**
     * Gets Player 2, can be AI
     * @return p2 (can also be AI player obj)
     */
    protected Player getP2() {
        return p2;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Gets the winning player
     * @return winner
     */
    public Player getWinner() throws GameException {
        try {
            return board.getWinningSpots().get(0).getPlayer();
        } catch (Exception e) {
            throw new GameException("Winner has not yet been concluded!");
        }
    }
}
