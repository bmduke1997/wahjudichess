package board;

/**
 * Created by brandon on 11/4/16.
 */
public class Queen extends Piece{

    private Movement[] myMovements = new Movement[16];

    public Queen(int x, int y, int value, int color) {
        super(x, y, value, color);
    }

    public Movement[] movement(Piece[][] board){
        int i = 0, tempX, tempY;

        // dx and dy represent the direction from the player which we are scanning
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                //skip the spot that the piece is on
                if (dx == 0 && dy == 0) continue;

                tempX = getX();
                tempY = getY();
                for (int step = 0; step < 5; step++) {
                    tempX = tempX + dx;
                    tempY = tempY + dy;

                    if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null){
                        myMovements[i++] = new Movement(tempX, tempY);
                    }
                    else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()){
                        myMovements[i++] = new Movement(tempX, tempY);
                        break;
                    }
                    else break;
                }
            }
        }
        return myMovements;
    }
}
