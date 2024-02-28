package Modal.Status;

public class GameStatus {
    private Winner winner;
    private boolean tie;

    public GameStatus(Winner winner) {
        this.winner = winner;
        this.tie = false;
    }

    public GameStatus(boolean tie) {
        this.winner = new Winner();
        this.tie = tie;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }
}
