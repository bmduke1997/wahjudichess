package chess.board;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by brandon on 11/4/16.
 */
public abstract class Piece {
    private IntegerProperty x, y;
    private int value, color;
    //color 0=black, 1=white
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public Piece(int x, int y, int value, int color){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this. value = value;
        this.color = color;
    }

    public int getX(){
        return x.get();
    }

    public int getY(){
        return y.get();
    }

    public void setX(int x){
        this.x.set(x);
    }

    public void setY(int y){
        this.y.set(y);
    }

    public IntegerProperty xProperty() { return x; }

    public IntegerProperty yProperty() { return y; }

    public int getValue(){
        return value;
    }

    public int getColor(){
        return color;
    }

    public abstract Movement[] movement(Piece[][] board);

}
