import modal.game.Game;
import modal.game.GameException;

import java.util.Scanner;

public class Main {
    private static Game game = new Game();

    public static void main(String[] args) throws GameException {
        Scanner kb = new Scanner(System.in);
        while (true) {
            switch(game.getStatus()) {
                // Game incomplete
                case INCOMPLETE -> {
                    System.out.println("Board:\n" + game.getCompleteBoard());
                    System.out.print(game.getNextPlayer() + " turn: ");
                    while (true) {
                        try {
                            game.next(kb.nextInt());
                            break;
                        } catch (GameException e) {
                            System.out.println("Board spot does not exist, try again");
                        }
                    }
                }

                // Game won
                case WON -> {
                    System.out.println("Game over, won: " + game.getWinner()); // Congratulate winner
                    System.out.println(game.getCompleteBoard()); // get spots won
                    restart();
                }

                // Game tied
                case TIED -> {
                    System.out.println("Game over, there was a tie!");
                    restart();
                }
            }
        }
    }

    /**
     * Restart/exit the game
     */
    public static void restart() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Would you like to restart the game? y/n");
        if (kb.nextLine().toLowerCase().charAt(0) == 'y')
            game = new Game();
        else
            System.exit(0);
    }
}