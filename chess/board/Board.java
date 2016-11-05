package board;

/**
 * Created by brandon on 11/4/16.
 */
public class Board {

    Piece[][] playingBoard = new Piece[5][5];

    public Board(){}

    public Piece[][] getPlayingBoard(){
        return playingBoard;
    }
}
