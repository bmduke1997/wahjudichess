package chess.board;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by brandon on 11/4/16.
 */
public abstract class Piece {
    private IntegerProperty x, y;
    private ObjectProperty position;
    private int value, color;
    //color 0=black, 1=white
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public Piece(int x, int y, int value, int color){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this. value = value;
        this.color = color;

        int[] tmp = new int[2];
        tmp[0] = x;
        tmp[1] = y;
        this.position = new SimpleObjectProperty(tmp);
    }

    public ObjectProperty positionProperty() { return position; }

    public int[] getPosition() { return (int[])position.get(); }

    public void setPosition(int x, int y) {
        int[] pair = new int[2];

        pair[0] = x;
        pair[1] = y;

        position.set((Object)pair);

        this.x.set(x);
        this.y.set(y);
    }

    public int getX(){
        return x.get();
    }

    public int getY(){
        return y.get();
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

    public abstract boolean hasCapture(Piece[][] board);

    public abstract void clear();
}
