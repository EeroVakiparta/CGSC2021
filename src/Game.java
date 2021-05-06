import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


// Heuristiikat
// kasvata keskustaa kohden ? Onko hyvä taku ?
// kasvata puita joiden ympärillä on vähiten puita?
// Ei kannata varmaan jonoon kasvattaa (Iso puu pistää varjoon monta?)
// Iso puu voi ampua pidemmälle siemenen :S

// state -> Board (voiko boardin

// soludata täytyy jotenki laskee
// ennustaa varjo seuraavale turnille??

// Pitääkö varoa ettei myy kaikkia puita ??
// varjo näköjään kasvaa vasta vuoron lopus?? Pitääkö vuoron alussa vaan tutkii varjot?
// Entä jos puun myy? Näyttäis että varjo jää?
// Simuloi päiviä ??
// Ennusta koska peli loppuu ?+ Haluanko myydä puun vai en ?

// ALA MYYMÄÄN VASTA KU PELI ON LOPPUMAISILLAAN !!!
public class Game {

    int day;
    boolean dayChanged = false;
    int nutrients;
    List<Cell> board;
    List<Action> possibleActions;
    List<Tree> trees;
    int mySun, opponentSun;
    int myScore, opponentScore;
    boolean opponentIsWaiting;
    Actor enemy,myself;
    public static int sunDirection = 0;

    public Game(List<Cell> board, List<Action> possibleActions, List<Tree> trees) {
        this.board = board;
        this.possibleActions = possibleActions;
        this.trees = trees;

    }

    public long countTrees(int size){
        return trees.stream().filter(e -> e.isMine() && e.size == size).count();
    }

    public long findGrowTreeCost(Tree tree){
        int baseCost = 0;
        switch (tree.size){
            case 0:
                baseCost = 1;
                break;
            case 1:
                baseCost = 3;
                break;
            case 2:
                baseCost = 7;
                break;
        }
        return countTrees(tree.size + 1) + baseCost;
    }

    Action getNextAction(){
        return myself.findBestAction(); // move to actor
    }

    public void readInput(Scanner in){
        int newDay = in.nextInt();
        dayChanged = newDay != day;
        int day = newDay; // the game lasts 24 days: 0-23
        int nutrients = in.nextInt(); // the base score you gain from the next COMPLETE action
        myself.sunPoints = in.nextInt(); // your sun points
        myself.scorePoints = in.nextInt(); // your current score
        enemy.sunPoints = in.nextInt(); // opponent's sun points
        enemy.scorePoints = in.nextInt(); // opponent's score
        opponentIsWaiting = in.nextInt() != 0; // whether your opponent is asleep until the next day

        trees.clear();

        int numberOfTrees = in.nextInt(); // the current amount of trees
        for (int i = 0; i < numberOfTrees; i++) {
            int cellIndex = in.nextInt(); // location of this tree
            int size = in.nextInt(); // size of this tree: 0-3
            Actor treeOwner = in.nextInt() != 0 ? myself : enemy;
            boolean isDormant = in.nextInt() != 0; // 1 if this tree is dormant
            Tree tree = new Tree(cellIndex,size,treeOwner,isDormant);
            trees.add(tree);
            board.get(tree.cellIndex).tree = tree;
            if(tree.isMine()){
                myself.trees.add(tree);
            }else {
                enemy.trees.add(tree);
            }
        }

    }


    public void readFirstTurn(Scanner in){
        int numberOfCells = in.nextInt(); // 37
        for (int i = 0; i < numberOfCells; i++) {
            int index = in.nextInt(); // 0 is the center cell, the next cells spiral outwards
            int richness = in.nextInt(); // 0 if the cell is unusable, 1-3 for usable cells
            int neigh0 = in.nextInt(); // the index of the neighbouring cell for each direction
            int neigh1 = in.nextInt();
            int neigh2 = in.nextInt();
            int neigh3 = in.nextInt();
            int neigh4 = in.nextInt();
            int neigh5 = in.nextInt();
            int neighs[] = new int[] {neigh0,neigh1,neigh2,neigh3,neigh4,neigh5};
            Cell cell = new Cell(index, richness,neighs);
            game.board.add(cell);
        }
    }

    public void startTurn(){
        dayChanged = false;
        myself.startTurn();
        enemy.startTurn();
        // tee warjot
        for(Cell cell: )

    }

    public void initializeTurn(){
        if (dayChanged) {
            for(Tree tree: trees ){
                tree.castShadow(board);

            }
            // ehkä tulevaisuudessa vihulle kans mut nyt vaa omille puille ?
            for()
            for(Cell cell : board){
                System.err(cell.toString())
            }
        }

    }

}
