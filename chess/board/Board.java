package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Board {

    Piece[][] playingBoard = new Piece[5][5];

    public Board(){}

    public void put(Piece piece, int x, int y) {
         playingBoard[y][x] = piece;
    }

    public Piece[][] getPlayingBoard() {
        return playingBoard;
    }

    public Piece getPieceAt(int x, int y) {
        return playingBoard[y][x];
    }

    public boolean isLegalMove(Movement[] myMovements, int targetX, int targetY)
    {
        boolean found = false;
        for (int i = 0; i < myMovements.length; i++)
        {
            if (myMovements[i] == null) break;

            if (myMovements[i].getX() == targetX && myMovements[i].getY() == targetY)
            {
                found = true;
            }
        }
        if (found == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isCapture(Piece piece, Movement[] myMovements, int targetX, int targetY)
    {
        boolean capture = false;
        for (int i = 0; i < myMovements.length; i++)
        {
            if (myMovements[i].getX() == targetX && myMovements[i].getY() == targetY && playingBoard[targetY][targetX] != null && playingBoard[targetY][targetX].getColor() != piece.getColor())
            {
                capture = true;
            }
        }
        if (capture == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
