import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    //MOIMOI
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Game game = new Game();
        game.readFirstTurn(in);

        while (true) {
            game.resetTurnBeforeInput();
            game.readInput(in);
            game.initializeTurn(); // casting shawords

            Action action = game.getNextAction();
            System.out.println(action);
        }
    }
}