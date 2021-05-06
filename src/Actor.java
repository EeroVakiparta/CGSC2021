import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Actor {
    Game game;
    ActorType actorType;
    int sunPoints = 0;
    int scorePoints = 0;
    List<Tree> trees = new ArrayList<>();

    public Actor(ActorType actorType, Game game) {
        this.actorType = actorType;
        this.game = game;
    }

    public void startTurn() {
        trees.clear();
    }

    // Etsitäänkö täsä paras vai palautetaanko täsä??+

    public Action findBestAction() {

        Action bestAction = null;
        //LAMDA reverse sorting type explicit ks stackoverflow reverse sort crow best trees
        trees.sort(Comparator.comparingInt((Tree o) -> game.board.get(o.cellIndex).richness).reversed());

        if (game.day >= 22 || (sunPoints > 30 && game.day > 15)) { // tavoittele bonusta
            bestAction = findBestTreeComplete();
        }

        if (bestAction == null) {
            bestAction = findBestTreeGrow();
        }

        if (bestAction == null) {
            bestAction = findBestTreePlan();
        }

        if (bestAction == null) {
            bestAction = game.possibleActions.get(0);
        }

        return bestAction; // can this be null?=
    }

    private Action findBestTreePlan() {
        // hommaa kaikki cellit filtteröi ja valitse paras
        // richness ++ nostaa arvoa
        List<Cell> allCells = game.board.stream()
                .filter(e -> e.seedPossible)
                .sorted(Comparator.comparingInt((Cell c) -> c.richness).reversed())
                .collect(Collectors.toList());

        // ffs dormand puut ei voi siementää joten täsä on probleema
        // joten pitää antaa siementävä puu

        if (!allCells.isEmpty()) {
            return plantTree(allCells.get(0).sourceTree.index, allCells.get(0).index);
        }
        return null;
    }

    private Action findBestTreeGrow() {
        for (int i = 0; i < trees.size(); i++) {
            Tree currentTree = trees.get(i);
            if (currentTree.canGrow() && sunPoints >= game.findGrowTreeCost((currentTree))) {
                return growTree(currentTree.cellIndex);
            }
        }
        return null; // ei löytyny actionia
    }

    private Action findBestTreeComplete() {
        for (int i = 0; i < trees.size(); i++) {
            Tree currentTree = trees.get(i);
            if (currentTree.canComplete() && sunPoints >= 4) {
                return completeTree(trees.get(i).cellIndex);
            }
        }
        return null; // ei löytyny actionia
    }

    public Action plantTree(int fromCellIndex, int toCellIndex) {
        return new Action(Action.SEED, fromCellIndex, toCellIndex);
    }

    public Action growTree(int cellIndex) {
        return new Action(Action.GROW, cellIndex);
    }

    public Action completeTree(int cellIndex) {
        return new Action(Action.COMPLETE, cellIndex);
    }
}
