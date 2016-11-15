package chess.board;

/**
 * @author brandon
 * @version 11/14/16
 */
public class AI {
    private Piece[][] board;
    private int color;

    public AI() {

    }
    public AI (Piece[][] board, int color){
        this.board = board;
        this.color = color;
    }

    //Used to call AI piece selection in controller in place of selection
    public Piece selection;
    private Piece tempSelection;
    public Movement[] AImovements;
    public int[] locations;
    //Get board, playing board which is piece array
    public int[] AImove(Board board, int color) {
        System.out.println();
        System.out.println("WHAT AI SEES");
        for(int j = 0; j<5; j++){
            for(int k = 0; k<5;k++){
                if(board.getPieceAt(k,j) != null)
                    System.out.print(board.getPieceAt(k,j).getValue()+ " ");
                else
                    System.out.print("_");
            }
            System.out.println();
        }
        locations = new int[4];
        int pieceX = 0;
        int pieceY = 0;
        int movementX = 0;
        int movementY = 0;
        int captureX = 0;
        int captureY = 0;
        int firstSelection = 0;
        boolean hasCapture = false;
        //Get our highest rated piece that has a legit movement
        for(int y = 0; y < 5; y++) {
            for(int x = 0; x < 5; x++){
                //Check for location being null
                if(board.getPieceAt(x, y) != null) {
                    //Check for selection being null
                    if(selection != null) {
                        if (board.getPieceAt(x, y).getValue() > selection.getValue()) {
                            tempSelection = board.getPieceAt(x, y);
                            if (tempSelection.movement(board.getPlayingBoard()) != null) {
                                selection = board.getPieceAt(x, y);
                                pieceX = x;
                                pieceY = y;
                            }
                        }
                    }
                    //Gets first piece with available moves
                    else if (board.getPieceAt(x, y).getColor() == color &&
                            board.getPieceAt(x, y).movement(board.getPlayingBoard()) != null) {
                        selection = board.getPieceAt(x, y);
                        if (firstSelection == 0) {
                            pieceX = x;
                            pieceY = y;
                            firstSelection++;
                        }
                    }
                }
            }
        }

        AImovements = selection.movement(board.getPlayingBoard());

        firstSelection = 0;

        //Check for captures
        for (int i = 0; i < AImovements.length; i++) {
            //Check for null location movements
            if(AImovements[i] != null) {
                //Check for null location
                if (board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()) != null) {
                    //Check for comparable selection being null
                    if (tempSelection != null) {
                        if ((board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()).getValue() > tempSelection.getValue())) {
                            captureX = AImovements[i].getX();
                            captureY = AImovements[i].getY();
                            tempSelection = board.getPieceAt(AImovements[i].getX(), AImovements[i].getY());
                        }
                    }
                    //Get first movement available with selected piece
                    else if (board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()).getColor() != color) {
                        tempSelection = board.getPieceAt(AImovements[i].getX(), AImovements[i].getY());
                        if (firstSelection == 0) {
                            captureX = AImovements[i].getX();
                            captureY = AImovements[i].getY();
                            firstSelection++;
                            hasCapture = true;
                        }
                    }
                }
            }
        }

        //Check for movements
        if(!hasCapture) {
            for (int i = 0; i < AImovements.length; i++) {
                //Check for null location movements
                if(AImovements[i] != null) {
                    //Check for null location
                    if (board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()) == null) {
                        //Check for comparable selection being null
                        if (tempSelection != null) {
                            if (board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()).movement(board.getPlayingBoard()).length
                                    > tempSelection.movement(board.getPlayingBoard()).length) {
                                movementX = AImovements[i].getX();
                                movementY = AImovements[i].getY();
                                tempSelection = board.getPieceAt(AImovements[i].getX(), AImovements[i].getY());
                            }
                        }
                        //Get first movement available with selected piece
                        else {
                            tempSelection = board.getPieceAt(AImovements[i].getX(), AImovements[i].getY());
                            if (firstSelection == 0) {
                                movementX = AImovements[i].getX();
                                movementY = AImovements[i].getY();
                                firstSelection++;
                                hasCapture = true;
                            }
                        }
                    }
                }
            }
        }

        //Use location values
        locations[0] = pieceX;
        locations[1] = pieceY;
        if(!hasCapture) {
            locations[2] = movementX;
            locations[3] = movementY;
        }
        else{
            locations[2] = captureX;
            locations[3] = captureY;
        }

        return locations;
    }
}
