package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Bishop extends Piece {
    public Bishop(int x, int y, int color) {
        super(x, y, 3, color);
    }

    @Override
    public Movement[] movement(Piece[][] board) {return null;}
}
