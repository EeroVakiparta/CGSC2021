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
    Cell sourceTree = null;
    Tree tree = null;

    public Cell(int index, int richness, int[] neighbours) {
        this.index = index;
        this.richness = richness;
        this.neighbours = neighbours;
    }

    // MITÄ HITTIA PUU KASVAA MUT VARJO EI PELIS ?
    // pclean shadows between turns
    public void startTurn() {
        clear();
    }

    public void clear() {
        sourceTree = null;
        seedPossible = false;
        shadow = 0;
        tree = null; // Voiko samalle ruudulle plänttää samall roundil millä myi?
    }

    public boolean isEmpty() {
        return tree == null;
    }

    public boolean canGrowOnTile() {
        return isEmpty() && richness != 0 && seedPossible; // barrenis ei voi kasvatel ja tulevaisuudessa myös enemy
    }

    @Override
    public String toString() {
        return "Cell{" +
                "index=" + index +
                ", shadow=" + shadow +
                ", seedPossible=" + seedPossible +
                '}';
    }

    List<Cell> getNeighbouringCells(Game game) {
        List<Cell> neighbouringCells = new ArrayList<>();
        for (int neigbour : neighbours) {
            if (neigbour != -1) {
                neighbouringCells.add(game.board.get(neigbour));
            }
        }
        return neighbouringCells;
    }
}
