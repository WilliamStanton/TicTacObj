package Modal;

public class Player {
    private String name;
    private String symbol;
    private boolean isTurn;
    private boolean won;

    public Player(String name, String symbol, boolean isTurn) {
        this.isTurn = isTurn;
        this.name = name;
        this.symbol = symbol;
        this.won = false;
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

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public String toString() {
        return name;
    }
}
