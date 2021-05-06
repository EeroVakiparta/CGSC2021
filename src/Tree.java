public class Tree {

    int cellIndex;
    int size;
    boolean isMine;
    boolean isDormant;

    public Tree(int cellIndex, int size, boolean isMine, boolean isDormant) {
        this.cellIndex = cellIndex;
        this.size = size;
        this.isMine = isMine;
        this.isDormant = isDormant;
    }

    public boolean canComplete(){
        return size >= 3 && isMine;
    }

    public boolean canGrow(){
        return !this.isDormant && isMine;
    }
}
