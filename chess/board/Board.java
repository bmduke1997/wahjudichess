package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Board {

    Piece[][] playingBoard = new Piece[5][5];

    public Board(){
    }

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

        black = 10;
        white = 10;
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
        private Piece capturer, captured, morphed;
        private boolean isTransform;
        
        public Delta(int srcX, int srcY, int destX, int destY,
                     Piece capturer, Piece captured,
                     boolean isTransform) {
            this.srcX = srcX;
            this.srcY = srcY;
            this.destX = destX;
            this.destY = destY;
            this.capturer = capturer;
            this.captured = captured;
            this.isTransform = isTransform;
            this.morphed = null;
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

        public boolean isTransform() {
            return isTransform;
        }

        public Piece getMorphed() {
            return morphed;
        }

        public void setMorphed(Piece morphed) {
            this.morphed = morphed;
        }
    }

    /**
     * Returns whether a delta involved transformation.
     *
     * @param delta is an undo delta, as returned by the
     *        {@link #move(int, int, int, int) move} method.
     */
    public boolean wasTransform(Object delta) {
        return ((Delta)delta).isTransform();
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
    public int black = 10, white = 10;
    public Object move(int srcX, int srcY, int destX, int destY) {
        Piece capturer = playingBoard[srcY][srcX];
        Piece captured = playingBoard[destY][destX];

        boolean isTransform = capturer instanceof Pawn && (destY == 0
                                                           || destY == 4);

        if(( (restricted || capture)
            && captured != null)
           || (!restricted && !capture)) {
        
            /* If there is already a piece at the spot, remove it. */
            if (captured != null) {
                if (captured.getColor() == Piece.BLACK) black--;
                if (captured.getColor() == Piece.WHITE) white--;
                remove(destX, destY);
                captured.setPosition(-1, -1);
            }

            playingBoard[destY][destX] = capturer;

            capturer.setPosition(destX, destY);

            playingBoard[srcY][srcX] = null;

            turn++;

            return (Object)(new Delta(srcX, srcY, destX, destY,
                                      capturer, captured, isTransform));
        }

        return null;
    }


    private int turn;

    public void updateTurn(int i){
        turn = i;
    }

    public int getTurn(){
        return turn;
    }

    private boolean whiteAI = false , blackAI = false;

    public void setAI(boolean white,boolean black){
        whiteAI = white;
        blackAI = black;
    }

    public boolean someAi(){
        return whiteAI || blackAI;
    }

    public boolean pieceBelongsToCurrentPlayer(int x, int y){
        return  playingBoard[y][x].getColor() == turn%2;
    }
    /**
     * Link a transforming piece (always a King) to a delta.
     *
     * This should only be used after clicking and moving a pawn to one
     * of the ends of the board, i.e., when the pawn is transforming.
     *
     * @param delta is the delta object, as returned by the
     *        {@link #move(int, int, int, int) move} method.
     * @param king is the Piece (King) to link.
     */
    public void assocMorphed(Object delta, Piece king) {
        Delta _delta = (Delta)delta;

        _delta.setMorphed(king);
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
        
        if (_delta.isTransform()) {
            /* Delete the king that resulted from the transformation. */
            remove(_delta.getDestX(), _delta.getDestY());
        }

        capturer.setPosition(_delta.getSrcX(), _delta.getSrcY());
        
        if (captured != null) {
            if (captured.getColor() == Piece.BLACK) {
                black++;
            } else {
                white++;
            }
            captured.setPosition(_delta.getDestX(), _delta.getDestY());
        }
        
        playingBoard[_delta.getSrcY()][_delta.getSrcX()] = capturer;
        playingBoard[_delta.getDestY()][_delta.getDestX()] = captured;

        turn--;
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

        Piece piece = getPieceAt(_delta.getSrcX(), _delta.getSrcY());
        piece.clear();
        checkRestrictions(this, piece, piece.movement(getPlayingBoard()),
                          _delta.getDestX(), _delta.getDestY());
        teamCapture();
        
        if (move(_delta.getSrcX(), _delta.getSrcY(),
                 _delta.getDestX(), _delta.getDestY()) == null) {
            System.out.println("Move unsuccessful.");
        }

        if (_delta.isTransform()) {
            remove(_delta.getDestX(), _delta.getDestY());

            Piece king = _delta.getMorphed();

            king.setPosition(_delta.getDestX(), _delta.getDestY());

            put(king);
        }

        turn++;
    }

    public Object copyMove(Object delta) {
        Delta _delta = (Delta)delta;

        Piece piece = getPieceAt(_delta.getSrcX(), _delta.getSrcY());
        piece.clear();
        checkRestrictions(this, piece, piece.movement(getPlayingBoard()),
                          _delta.getDestX(), _delta.getDestY());
        teamCapture();
        
        Object o = move(_delta.getSrcX(), _delta.getSrcY(),
                        _delta.getDestX(), _delta.getDestY());

        if (o != null) {
            System.out.print ("[*] Successfully copied move: ");
            System.out.print ("(" + _delta.getSrcX() + ", " + _delta.getSrcY() + ")");
            System.out.println (" -> (" + _delta.getDestX() + ", " + _delta.getDestY() + ")");
            return o;
        } else {
            System.out.print ("[*] Move copy determined to be impossible: ");
            System.out.print ("(" + _delta.getSrcX() + ", " + _delta.getSrcY() + ")");
            System.out.println (" -> (" + _delta.getDestX() + ", " + _delta.getDestY() + ")");
            return o;
        }
    }

    public void remove(int x, int y) {
        if (playingBoard[y][x] == null) {
            return;
        }

        playingBoard[y][x].setPosition(-1, -1);

        playingBoard[y][x] = null;
    }

    public Piece getCapturer(Object delta) {
        Delta _delta = (Delta)delta;
        return _delta.getCapturer();
    }

    public void removeCapturer(Object delta) {
        Delta _delta = (Delta)delta;
        remove(_delta.getCapturer().getX(), _delta.getCapturer().getY());
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

        if (myMovements != null) {
            for (int i = 0; i < myMovements.length; i++) {
                if (myMovements[i] != null) {
                    if (board.getPieceAt(myMovements[i].getX(), myMovements[i].getY()) == null) {
                        continue;
                    } else if (board.getPieceAt(myMovements[i].getX(), myMovements[i].getY()).getColor() != piece.getColor()) {
                        restricted = true;
                        System.out.println("Is restricted!");
                        break;
                    }
                }
            }
            isLegalMove(board, piece, myMovements, targetX, targetY);
        }
        else hasLegalMove = false;
    }
    private boolean capture;
    public void teamCapture(){
        int color = turn%2;
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
