import java.util.List;

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
}
