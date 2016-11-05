package board;

/**
 * Created by brandon on 11/4/16.
 */
public class Pawn extends Piece {
    private Movement[] myMovements = new Movement[3];

    public Pawn(int x, int y, int value, int color) {
        super(x, y, value, color);
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
            myMovements[i++]= new Movement (tempX, tempY+j);
        }
        try {
            if (tempY + j < 5 && tempX + j < 5&& board[tempY+j][tempX+j].getColor() != getColor()){
                myMovements[i++]= new Movement (tempX+j, tempY+j);
            }
        }
        catch (Exception e){

        }
        try {
            if (tempY + j < 5 && tempX - j > 0 && board[tempY+j][tempX-j].getColor() != getColor()){
                myMovements[i++]= new Movement (tempX-j, tempY+j);
            }
        }
        catch (Exception e) {

        }
        return myMovements;
    }
}
