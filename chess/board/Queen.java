package chess.board;

/**
 * Created by brandon on 11/4/16.
 */
public class Queen extends Piece{
    public Movement[] myMovements = new Movement[16];

    public Queen(int x, int y, int color) {
        super(x, y, 10, color);
    }

    @Override
    public Movement[] movement(Piece[][] board){
        int i = 0, tempX, tempY;

        // dx and dy represent the direction from the player which we are scanning
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                //skip the spot that the piece is on
                if (dx == 0 && dy == 0) continue;

                tempX = getX();
                tempY = getY();
                for (int step = 0; step < 5; step++) {
                    tempX = tempX + dx;
                    tempY = tempY + dy;

                    if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null){
                        myMovements[i++] = new Movement(tempY, tempX);
                    }
                    else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()){
                        myMovements[i++] = new Movement(tempY, tempX);
                        break;
                    }
                    else break;
                }
            }
        }
        return myMovements;
    }
}
