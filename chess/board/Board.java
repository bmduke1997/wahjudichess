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
    
    /**
     * A Delta is a cookie used for undoing a move.
     * 
     * Deltas contain information about a single move, allowing that
     * move to be reversed when the time comes.
     * 
     * The name "Delta" was chosen because, in mathematics, the Greek
     * letter delta often denotes a "difference".
     */
    private class Delta {
        private int srcX, srcY, destX, destY;
        private Piece capturer, captured;
        
        public Delta(int srcX, int srcY, int destX, int destY,
                     Piece capturer, Piece captured) {
            this.srcX = srcX;
            this.srcY = srcY;
            this.destX = destX;
            this.destY = destY;
            this.capturer = capturer;
            this.captured = captured;
        }

        public int getSrcX() {
            return srcX;
        }

        public int getSrcY() {
            return srcY;
        }

        public int getDestX() {
            return destX;
        }

        public int getDestY() {
            return destY;
        }

        public Piece getCapturer() {
            return capturer;
        }

        public Piece getCaptured() {
            return captured;
        }
    }

    /**
     * Attempt to move a piece on the board and return an "undo delta".
     * 
     * @param srcX is the x position of the moving piece.
     * @param srcY is the y position of the moving piece.
     * @param destX is the target x position.
     * @param destY is the target y position.
     * @return a delta which can be used to undo the move, or null if the
     *         was illegal.
     * @see #undo(Object)
     */
    public Object move(int srcX, int srcY, int destX, int destY) {
        Piece capturer = playingBoard[srcY][srcX];
        Piece captured = playingBoard[destY][destX];
        
        if(( (restricted || capture)
            && captured != null)
           || (!restricted && !capture)) {
        
            System.out.println("Executing move...");

            /* If there is already a piece at the spot, remove it. */
            if (captured != null) {
                remove(destX, destY);
                captured.setPosition(-1, -1);
            }

            playingBoard[destY][destX] = capturer;

            capturer.setPosition(destX, destY);

            playingBoard[srcY][srcX] = null;
        
            return (Object)(new Delta(srcX, srcY, destX, destY,
                                      capturer, captured));
        }

        return null;
    }
    
    /**
     * Undo a move using a delta object.
     * 
     * @param delta is the delta object, as returned by the
     *        {@link #move(int, int, int, int) move} method.
     * @see #move(int, int, int, int)
     */
    public void undo(Object delta) {
        Delta _delta = (Delta)delta;
        Piece capturer = _delta.getCapturer();
        Piece captured = _delta.getCaptured();
        
        capturer.setPosition(_delta.getSrcX(), _delta.getSrcY());
        
        if (captured != null) {
            captured.setPosition(_delta.getDestX(), _delta.getDestY());
        }
        
        playingBoard[_delta.getSrcY()][_delta.getSrcX()] = capturer;
        playingBoard[_delta.getDestY()][_delta.getDestX()] = captured;
    }
    
    /**
     * Redo a move using a delta object.
     * 
     * The same delta that was used to undo a move can be used to redo it.
     * 
     * @param delta is the delta object, as returned by the
     *        {@link #move(int, int, int, int) move} method.
     * @see #move(int, int, int, int)
     */
    public void redo(Object delta) {
        Delta _delta = (Delta)delta;
        System.out.println(_delta.getSrcX() + "," + _delta.getSrcY() + " -> " +
                           _delta.getDestX() + "," + _delta.getDestY());
        
        Piece piece = getPieceAt(_delta.getSrcX(), _delta.getSrcY());
        piece.clear();
        checkRestrictions(this, piece, piece.movement(getPlayingBoard()),
                          _delta.getDestX(), _delta.getDestY());
        
        move(_delta.getSrcX(), _delta.getSrcY(),
             _delta.getDestX(), _delta.getDestY());
    }

    public void remove(int x, int y) {
        if (playingBoard[y][x] == null) {
            return;
        }
        playingBoard[y][x].setPosition(-1, -1);

        playingBoard[y][x] = null;
    }

    public Piece getPieceAt(int x, int y) {
        return playingBoard[y][x];
    }

    /* Check if desired move is legal */
    public boolean hasLegalMove;
    public void isLegalMove(Board board, Piece piece, Movement[] myMovements, int targetX, int targetY) {

        hasLegalMove = false;

        for (int i = 0; i < myMovements.length; i++) {
            if (myMovements[i] == null) break;

            else if ( board.getPieceAt(targetX, targetY) == null
                    && myMovements[i].getX() == targetX && myMovements[i].getY() == targetY) {
                    hasLegalMove = true;
            }
            else if (myMovements[i].getX() == targetX && myMovements[i].getY() == targetY
                    && board.getPieceAt(targetX, targetY).getColor() != piece.getColor()) {
                hasLegalMove = true;
            }
        }
    }

    /* Check for captures for selected piece */
    public boolean restricted;
    public void checkRestrictions(Board board, Piece piece, Movement[] myMovements, int targetX, int targetY) {

        restricted = false;

        for (int i = 0; i < myMovements.length; i++) {
            if (myMovements[i] != null) {
                if(board.getPieceAt(myMovements[i].getX(), myMovements[i].getY()) == null) {
                    continue;
                }
                else if (board.getPieceAt(myMovements[i].getX(), myMovements[i].getY()).getColor() != piece.getColor()) {
                    restricted = true;
                    System.out.println("Is restricted!");
                    break;
                }
            }
        }
        isLegalMove(board, piece, myMovements, targetX, targetY);
    }
    private boolean capture;
    public void teamCapture(int color){
        capture = false;

        for (int y = 0; y < 5; y++){
            if(capture) break;
            for (int x = 0; x < 5; x++){
                //TODO watch out for null spaces
                try {
                    if (playingBoard[y][x].getColor() == color) {
                        capture = playingBoard[y][x].hasCapture(playingBoard);
                        if(capture) {
                            System.out.println("Team has capture. " + "Color: " + color);
                            break;
                        }
                    }
                }
                catch (Exception e){
                    //get rid of this error
                }
            }
        }
    }
}
