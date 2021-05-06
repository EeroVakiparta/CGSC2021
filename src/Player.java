import java.util.*;
import java.io.*;
import java.math.*;

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



            int numberOfPossibleMoves = in.nextInt();
            if (in.hasNextLine()) {
                in.nextLine();
            }
            for (int i = 0; i < numberOfPossibleMoves; i++) {
                String possibleMove = in.nextLine();
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // GROW cellIdx | SEED sourceIdx targetIdx | COMPLETE cellIdx | WAIT <message>
            System.out.println("WAIT");
        }
    }
}