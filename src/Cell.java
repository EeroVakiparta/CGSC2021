public class Cell {

    int index;
    int richness;
    int[] neighbours;

    public Cell(int index, int richness, int[] neighbours) {
        this.index = index;
        this.richness = richness;
        this.neighbours = neighbours;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRichness() {
        return richness;
    }

    public void setRichness(int richness) {
        this.richness = richness;
    }

    public int[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(int[] neighbours) {
        this.neighbours = neighbours;
    }
}
