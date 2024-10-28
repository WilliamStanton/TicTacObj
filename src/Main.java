import Modal.Status;
import Service.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game(); // init game
        Scanner kb = new Scanner(System.in);

        // While game is active
        while (game.getStatus() == Status.INCOMPLETE) {
            System.out.println("Board:\n" + game.getCompleteBoard());
            System.out.print(game.getNextPlayer() + " turn: ");
            game.next(kb.nextInt());
        }

        if (game.getStatus() == Status.WON) {
            System.out.println("Game over, won: " + game.getWinner()); // Congratulate winner
            System.out.println(game.getCompleteBoard()); // get spots won
        }
        else
            System.out.println("Game over, there was a tie!");
    }
}