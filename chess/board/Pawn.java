package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Pawn extends Piece {

    private Movement[] myMovements = new Movement[3];

    public Pawn(int x, int y, int color) {
        super(x, y, 1, color);
    }

    @Override
    public Movement[] movement(Piece[][] board) {
        int i = 0;
        int j = 0;
        int tempX = getX();
        int tempY = getY();

        if (getColor() == Piece.BLACK) { // Top to bottom
            j = 1;
        }
        else if(getColor() == Piece.WHITE){  //Bottom to top
            j = -1;
        }
        if(tempY + j < 5 && board[tempY+j][tempX] == null){
            myMovements[i++]= new Movement (tempY+j, tempX);
        }
        try {
            if (tempY + j < 5 && tempX + j < 5 && board[tempY+j][tempX+j].getColor() != getColor()){
                myMovements[i++]= new Movement (tempY+j, tempX+j);
            }
        }
        catch (Exception e){

        }
        try {
            if (tempY + j < 5 && tempX - j > -1 && board[tempY+j][tempX-j].getColor() != getColor()){
                myMovements[i++]= new Movement (tempY+j, tempX-j);
            }
        }
        catch (Exception e) {

        }
        return myMovements;
    }
    public void clear() {
        for (int i = 0; i < myMovements.length; i++) {
            myMovements[i] = null;
        }
    }
}
