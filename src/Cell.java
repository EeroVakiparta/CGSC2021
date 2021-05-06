public class Cell {

    int index;
    int richness;
    int[] neighbours;
    int shadow;

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
        shadow = 0;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "index=" + index +
                ", shadow=" + shadow +
                '}';
    }
}
