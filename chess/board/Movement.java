package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Movement {

    private int x, y;

    public Movement(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

}
