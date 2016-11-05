package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Rook extends Piece {
    public Rook(int x, int y, int color) {
        super(x, y, 5, color);
    }

    @Override
    public Movement[] movement(Piece[][] board) {return null;}
}
