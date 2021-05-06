import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Game {

    int day;
    int nutrients;
    List<Cell> board;
    List<Action> possibleActions;
    List<Tree> trees;
    int mySun, opponentSun;
    int myScore, opponentScore;
    boolean opponentIsWaiting;

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
            if(currentTree.canComplete() && mySun >= 4){
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
}
