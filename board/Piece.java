package board;

/**
 * Created by brandon on 11/4/16.
 */
public abstract class Piece {
    private int x, y, value, color;

    public Piece(int x, int y, int value, int color){
        this.x = x;
        this.y = y;
        this. value = value;
         this.color = color;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getValue(){
        return value;
    }

    public int getColor(){
        return color;
    }

    public abstract Movement[] movement(Piece[][] board);

}
