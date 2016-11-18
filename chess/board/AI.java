package chess.board;

import java.util.Arrays;

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
    public Movement[] tempAImovements;
    public int[] locations;

    //Get board, playing board which is piece array
    public int[] AImove(Board board, int color) {
        System.out.println();
        System.out.println("WHAT AI SEES");
        for(int j = 0; j<5; j++){
            for(int k = 0; k<5;k++){
                if(board.getPieceAt(k,j) != null)
                    System.out.print(board.getPieceAt(k,j).getColor()+ " ");
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

        selection = null;
        tempSelection = null;
        AImovements = null;
        tempAImovements = null;

        //Get highest rated piece that has a capture
        for(int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                //Check for location being null
                if (board.getPieceAt(x, y) != null && board.getPieceAt(x, y).getColor() == color) {

                    tempSelection = board.getPieceAt(x, y);
                    tempSelection.clear();
                    tempAImovements = null;
                    tempAImovements = tempSelection.movement(board.getPlayingBoard());
                    System.out.println(Arrays.toString(tempAImovements));
                    if (selection != null && tempAImovements[0] != null) {

                        for(int i = 0; i < tempAImovements.length; i++)
                        {
                            if(tempAImovements[i] != null)
                            {
                                if(board.getPieceAt(tempAImovements[i].getX(),tempAImovements[i].getY()) != null)
                                {
                                    if (tempSelection.getValue() > selection.getValue()) {
                                        hasCapture = true;
                                        selection = tempSelection;
                                        selection.clear();
                                        AImovements = null;
                                        AImovements = selection.movement(board.getPlayingBoard());
                                        System.out.println("Current piece with capture: " + selection.getValue());
                                        /*//Might need this stuff
                                        selection.clear();
                                        AImovements = selection.movement(board.getPlayingBoard());*/

                                        pieceX = x;
                                        pieceY = y;
                                        captureX = AImovements[i].getX();
                                        captureY = AImovements[i].getY();
                                    }
                                }
                            }
                        }
                    }
                    //Gets first piece with available capture
                    else if (firstSelection == 0 && tempAImovements[0] != null) {
                        System.out.println("First piece found!");
                        for(int i = 0; i < tempAImovements.length; i++)
                        {
                            if(tempAImovements[i] != null)
                            {
                                if(board.getPieceAt(tempAImovements[i].getX(),tempAImovements[i].getY()) != null)
                                {
                                    hasCapture = true;
                                    selection = tempSelection;
                                    selection.clear();
                                    AImovements = null;
                                    AImovements = selection.movement(board.getPlayingBoard());
                                    System.out.println("Current piece with capture: " + selection.getValue());
                                    pieceX = x;
                                    pieceY = y;
                                    captureX = AImovements[i].getX();
                                    captureY = AImovements[i].getY();
                                    firstSelection++;
                                }
                            }
                        }
                    }
                }
            }
        }

        firstSelection = 0;

        //Get highest rated piece that has a legit movement if no capture
        if(!hasCapture) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    //Check for location being null
                    if (board.getPieceAt(x, y) != null && board.getPieceAt(x, y).getColor() == color) {

                        tempSelection = board.getPieceAt(x, y);
                        tempSelection.clear();
                        tempAImovements = null;
                        tempAImovements = tempSelection.movement(board.getPlayingBoard());

                        if (selection != null && tempAImovements[0] != null) {
                            if (tempSelection.getValue() > selection.getValue()) {

                                selection = tempSelection;
                                selection.clear();
                                AImovements = null;
                                AImovements = selection.movement(board.getPlayingBoard());

                                System.out.println("Current piece: " + selection.getValue());
                                pieceX = x;
                                pieceY = y;
                                movementX = AImovements[0].getX();
                                movementY = AImovements[0].getY();
                                for(int i = 0; i < AImovements.length; i++)
                                {
                                    if(AImovements[i]!= null && board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()) != null) {
                                        if (board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()).getValue()
                                                < board.getPieceAt(movementX, movementY).getValue()) {
                                            movementX = AImovements[i].getX();
                                            movementY = AImovements[i].getY();
                                        }
                                    }
                                }
                            }
                        }
                        //Gets first piece with available moves
                        else if (firstSelection == 0 && tempAImovements[0] != null) {

                            selection = tempSelection;
                            selection.clear();
                            AImovements = null;
                            AImovements = selection.movement(board.getPlayingBoard());

                            System.out.println("First piece: " + selection.getValue());
                            pieceX = x;
                            pieceY = y;
                            movementX = AImovements[0].getX();
                            movementY = AImovements[0].getY();
                            for(int i = 0; i < AImovements.length; i++)
                            {
                                if(AImovements[i]!=null && board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()) != null) {
                                    if (board.getPieceAt(AImovements[i].getX(), AImovements[i].getY()).getValue()
                                            < board.getPieceAt(movementX, movementY).getValue()) {
                                        movementX = AImovements[i].getX();
                                        movementY = AImovements[i].getY();
                                    }
                                }
                            }
                            firstSelection++;
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

        System.out.println("Capture X:" + captureX);
        System.out.println("Capture Y:" + captureY);
        System.out.println("Movement X:" + movementX);
        System.out.println("Movement Y:" + movementY);

        return locations;
    }
}
