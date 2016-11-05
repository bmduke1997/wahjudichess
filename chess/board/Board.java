package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Board {

    Piece[][] playingBoard = new Piece[5][5];

    public Board(){}

    public void put(Piece piece, int x, int y) {
         playingBoard[x][y] = piece;
    }

    public Piece[][] getPlayingBoard(){
        return playingBoard;
    }
}
