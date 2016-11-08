package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Board {

    Piece[][] playingBoard = new Piece[5][5];

    public Board(){}

    public void put(Piece piece) {
        playingBoard[piece.getY()][piece.getX()] = piece;
    }

    public Piece[][] getPlayingBoard() {
        return playingBoard;
    }

    public void clear() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                remove(j, i);
            }
        }
    }

    public void move(int srcX, int srcY, int destX, int destY) {
        System.out.println("Executing move...");

        Piece piece = playingBoard[srcY][srcX];

        /* If there is already a piece at the spot, remove it. */
        if (playingBoard[destY][destX] != null) {
            remove(destX, destY);
        }

        playingBoard[destY][destX] = piece;

        piece.setPosition(destX, destY);

        playingBoard[srcY][srcX] = null;
    }

    public void remove(int x, int y) {
        if (playingBoard[y][x] == null)
            return;

        playingBoard[y][x].setPosition(-1, -1);

        playingBoard[y][x] = null;
    }

    public Piece getPieceAt(int x, int y) {
        return playingBoard[y][x];
    }

    public boolean isLegalMove(Board board, Piece piece, Movement[] myMovements, int targetX, int targetY)
    {
        boolean found = false;

        for (int i = 0; i < myMovements.length; i++) {
            if (myMovements[i] == null) break;

            else if ( board.getPieceAt(targetX, targetY) == null
                    && myMovements[i].getX() == targetX && myMovements[i].getY() == targetY) {
                found = true;
            }
            else if (myMovements[i].getX() == targetX && myMovements[i].getY() == targetY
                    && board.getPieceAt(targetX, targetY).getColor() != piece.getColor()) {
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