package tictactoelib;

/**
 * Represents a player
 */
public class Player {
    private String name;
    private String symbol;
    private boolean isTurn;

    public Player(String name, String symbol, boolean isTurn) {
        this.isTurn = isTurn;
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    @Override
    public String toString() {
        return name;
    }
}
