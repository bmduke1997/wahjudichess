package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class King extends Piece {
    public King(int x, int y, int color) {
        super(x, y, 6, color);
    }

    @Override
    public Movement[] movement(Piece[][] board) {return null;}
}
