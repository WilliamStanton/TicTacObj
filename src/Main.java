import Service.GameService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            // Initialization
            GameService game = new GameService(); // Init game
            Scanner kb = new Scanner(System.in);

            // While game hasn't ended
            while (!game.gameStatus().isTie() && !game.gameStatus().getWinner().isWon()) {
                System.out.println("Board:\n" + game.getCompleteBoard());
                System.out.print(game.getNextPlayer() + " turn: ");
                game.updateGame(kb.nextInt());
            }

            // Game over, display TIE else WINNER
            if (game.gameStatus().isTie())
                System.out.println("Game over, there was a tie!");
            else {
                System.out.println("Game over, won: " + game.gameStatus().getWinner().getWinnerName()); // Congratulate winner
                System.out.println(game.gameStatus().getWinner().getBoardSpots().toString()); // get spots won
            }
            break;
        }
    }
}