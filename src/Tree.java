import java.util.*;

public class Tree {

    int cellIndex;
    int size;
    boolean isDormant;
    Actor actor;


    public Tree(int cellIndex, int size, Actor actor, boolean isDormant) {
        this.cellIndex = cellIndex;
        this.size = size;
        this.actor = actor;
        this.isDormant = isDormant;
    }

    public boolean isMine(){
        return actor.actorType == ActorType.MYSELF;
    }

    public boolean canComplete(){
        return size >= 3 && isMine();
    }

    public boolean canGrow(){
        return !this.isDormant && isMine() && size < 3;
    }
    // -1 jos ei naapuria
    public void castShadow(List<Cell> board){
        Cell currentCell = board.get(cellIndex);
        int shadowSize = size;
        while(shadowSize > 0){
            if(currentCell.neighbours[Game.sunDirection] == -1) return; // no neighbour so exit
            currentCell = board.get(currentCell.neighbours[Game.sunDirection]); // int
            currentCell.shadow = this.size;
            shadowSize--;
        }
    }
    // get all neighbours shadow size -> move to next
    // all napurit -> ja niistä naapurit ja lisää... tulee infinite loop
    // täytyykö kerätä solut jotka on jo tutkittu... kyl
    public void markSeedDistance(List<Cell> board){ // kato saako tän tehtyy 2 listal
        if(size==0)return;
        int distance = 0;
        List<Cell> visitedCells = new ArrayList<>(board.get(cellIndex).getNeighbouringCells(actor.game));
        Queue<Cell> nextNeighbours = new ArrayDeque<>(board.get(cellIndex).getNeighbouringCells(actor.game));
        visitedCells.add(board.get(cellIndex));
        List<Cell> nextDistanceCells = new ArrayList<>();
        // jos on koko 1 niin tuhlataan aika pali prosessointiaikaa

        // puu koko 2 -> size 1 naapurit
        while(!nextNeighbours.isEmpty()){
            Cell nextCell = nextNeighbours.remove();
            nextCell.seedPossible = true;
            List<Cell> newNeighbours = nextCell.getNeighbouringCells(actor.game);
            newNeighbours.removeAll(visitedCells);
            visitedCells.addAll(newNeighbours); // hommaa
            nextDistanceCells.addAll(newNeighbours);

            if(nextNeighbours.isEmpty()){
                distance++;
                if(distance >= size) break; // >= jotta ei tehä liikaa rangea +1
                newNeighbours.addAll(nextDistanceCells);
                nextDistanceCells.clear(); // puhistaa seuraavaa rangea varte
            }
        }





    }
}
