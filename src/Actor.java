import java.util.ArrayList;
import java.util.List;

public class Actor {
    int sunPoints = 0;
    int scorePoints = 0;
    List<Tree> trees = new ArrayList<>();
    ActorType actorType;

    public Actor(ActorType actorType){
    }

    public void startTurn(){
        trees.clear();
    }

}
