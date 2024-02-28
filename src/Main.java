import Service.GameService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            // Initialization
            GameService game = new GameService(); // Init game
            Scanner kb = new Scanner(System.in);

            // While game hasn't ended
            while (!game.checkWin()) {
                System.out.println("board:\n" + game.getCompleteBoard());
                System.out.print(game.getNextPlayer() + " turn: ");
                game.updateGame(kb.nextInt());
            }

            // Game won
            System.out.println("Game over, won"); // Congratulate winner
            break;
        }
    }
}