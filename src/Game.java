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
public class Game {

    int day;
    int nutrients;
    List<Cell> board;
    List<Action> possibleActions;
    List<Tree> trees;
    int mySun, opponentSun;
    int myScore, opponentScore;
    boolean opponentIsWaiting;
    Actor enemy,myself;
    int sunDirection = 0;

    public Game(List<Cell> board, List<Action> possibleActions, List<Tree> trees) {
        this.board = board;
        this.possibleActions = possibleActions;
        this.trees = trees;

    }

    public long countTrees(int size){
        return trees.stream().filter(e -> e.isMine && e.size == size).count();
    }

    public long findGrowTreeCost(Tree tree){
        int baseCost = tree.size == 1 ? 3 : 7;
        return countTrees(tree.size + 1) + baseCost;
    }

    Action getNextAction(){
        for (int i = 0;i< trees.size(); i++){
            Tree currentTree = trees.get(i);
            if(currentTree.canComplete() && myself.sunPoints >= 4){
                return new Action(Action.COMPLETE, trees.get(i).cellIndex);
            }
        }

        //LAMDA reverse sorting type explicit ks stackoverflow reverse sort crow best trees
        trees.sort(Comparator.comparingInt((Tree o) -> board.get(o.cellIndex).richness).reversed());


        for (int i = 0;i < trees.size(); i++){
            Tree currentTree = trees.get(i);
            //System.err.println(("My sun " + mySun + " my trees: " + trees.stream().filter(e -> e) ;
            //System.err.pringln("Can gro " + currentTree.canGrow() + " cost: " + findGrowTreeCost(currentTree));
            if(currentTree.canGrow() && mySun >= findGrowTreeCost((currentTree))){
                return new Action(Action.GROW, trees.get(i).cellIndex);
            }
        }


        System.err.println("going to get possible .get(possibleActions.size() - 1");
        return possibleActions.get(possibleActions.size() - 1);
    }

    public void readInput(Scanner in){

        int day = in.nextInt(); // the game lasts 24 days: 0-23
        int nutrients = in.nextInt(); // the base score you gain from the next COMPLETE action
        int sun = in.nextInt(); // your sun points
        int score = in.nextInt(); // your current score
        int oppSun = in.nextInt(); // opponent's sun points
        int oppScore = in.nextInt(); // opponent's score
        boolean oppIsWaiting = in.nextInt() != 0; // whether your opponent is asleep until the next day
        int numberOfTrees = in.nextInt(); // the current amount of trees

        trees.clear();

        for (int i = 0; i < numberOfTrees; i++) {
            int cellIndex = in.nextInt(); // location of this tree
            int size = in.nextInt(); // size of this tree: 0-3
            boolean isMine = in.nextInt() != 0; // 1 if this is your tree
            boolean isDormant = in.nextInt() != 0; // 1 if this tree is dormant

            Tree tree = new Tree(cellIndex,size,isMine,isDormant);
            trees.add(tree);
            if(tree.isMine){
                myself.trees.add(tree);
            }else {
                enemy.trees.add(tree);
            }
        }
            game.day = day;
            game.nutrients = nutrients;
            game.mySun = sun;
            game.myScore = score;
            game.opponentSun = oppSun;
            game.opponentScore = oppScore;
            game.opponentIsWaiting = oppIsWaiting;


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
        myself.startTurn();
        enemy.startTurn();
    }
}
