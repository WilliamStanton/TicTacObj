import modal.game.ComputerGame;
import modal.game.Game;
import modal.game.GameException;
import modal.game.PlayerGame;

import java.util.Scanner;

/**
 * A demonstration of the Tic Tac Toe API
 */
public class Main {
    // Player Game
    private static Game game;

    public static void main(String[] args) throws GameException {
        Scanner kb = new Scanner(System.in);

        // Choose game mode
        System.out.println("What would you like to play against?\n1) Another Person\n2) AI");
        game = kb.nextInt() == 1 ? new PlayerGame() : new ComputerGame(); // Instantiate game
        System.out.println(game instanceof PlayerGame ? "Playing against another player." : "Playing against AI"); // Display game type

        // Game loop
        while (true) {
            switch(game.getStatus()) {
                // Game incomplete
                case INCOMPLETE -> {
                    System.out.println("Board:\n" + game.getCompleteBoard());
                    System.out.print(game instanceof ComputerGame ? "Your turn: " : game.getNextPlayer() + " turn: ");
                    while (true) {
                        try {
                            // Computer Game
                            if (game instanceof ComputerGame compGame) {
                                if (compGame.computerTurn())
                                    compGame.next(); // Computer turn
                                else
                                    compGame.next(kb.nextInt()); // Player turn
                            }
                            // Player Game
                            else
                                game.next(kb.nextInt()); // Next Player turn
                            break;
                        } catch (GameException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                // Game won
                case WON -> {
                    System.out.println("Game over, won: " + game.getWinner()); // Congratulate winner
                    restart(); // Restart game
                }

                // Game tied
                case TIED -> {
                    System.out.println("Game over, there was a tie!");
                    restart(); // Restart game
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
        // Recreate game instance
        if (kb.nextLine().toLowerCase().charAt(0) == 'y')
            game = game instanceof PlayerGame ? new PlayerGame() : new ComputerGame();
        // Exit
        else
            System.exit(0);
    }
}