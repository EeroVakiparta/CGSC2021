import java.util.ArrayList;
import java.util.List;

public class Cell {
    // pitääk varjo yhdistää tähän??
    // has tree ? cell?
    int index;
    int richness;
    int[] neighbours;
    int shadow;
    // tulevaisuudessa puut jotenki voi connectaa tuleviin celleihi
    boolean seedPossible = false;
    Tree tree = null;

    public Cell(int index, int richness, int[] neighbours) {
        this.index = index;
        this.richness = richness;
        this.neighbours = neighbours;
    }
    // MITÄ HITTIA PUU KASVAA MUT VARJO EI PELIS ?
    // pclean shadows between turns
    public void startTurn(){
        clear();
    }

    public void clear(){
        seedPossible = false;
        shadow = 0;
        tree = null; // Voiko samalle ruudulle plänttää samall roundil millä myi?
    }

    public boolean isEmpty(){
        return tree == null;
    }

    public boolean canGrowOnTile(){
        return isEmpty() && richness != 0; // barrenis ei voi kasvatel
    }

    @Override
    public String toString() {
        return "Cell{" +
                "index=" + index +
                ", shadow=" + shadow +
                '}';
    }

    List<Cell> neighbouringCells(){
        List<Cell> neighbouringCells = new ArrayList<>();
        for(Cell cell : neighbours){
            if(neigbour != -1){
                neighbouringCells.add(game.board.get)
            }
        }
    }
}
