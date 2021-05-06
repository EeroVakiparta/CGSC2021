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
}
