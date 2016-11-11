package chess.board;

/**
 * Created by Brody on 11/4/16.
 */
public class Rook extends Piece
{
    private Movement[] myMovements = new Movement[8];

    public Rook (int x, int y, int color)
    {

        super(x, y, 3, color);
    }

    public Movement[] movement(Piece[][] board)
    {
        int tempX = getX();
        int tempY = getY();
        int i = 0;

        int dx = -1;
        int dy = -1;

        //Checks Left to right
        while (dx <= 1)
        {
            tempX += dx;
            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null)
            {
                myMovements[i++] = new Movement(tempY, tempX);
            }
            else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor())
            {
                myMovements[i++] = new Movement(tempY, tempX);
                if(dx == -1)
                {
                    tempX = getX();
                    dx = 1;
                }
                else break;
            }
            else if(dx == -1)
            {
                tempX = getX();
                dx = 1;
            }
            else break;
        }

        tempX = getX();

        //Checks Top to bottom
        while (dy <= 1)
        {
            tempY += dy;
            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null)
            {
                myMovements[i++] = new Movement(tempY, tempX);
            }
            else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor())
            {
                myMovements[i++] = new Movement(tempY, tempX);
                if(dy == -1)
                {
                    tempY = getY();
                    dy = 1;
                }
                else break;
            }
            else if(dy == -1)
            {
                tempY = getY();
                dy = 1;
            }
            else break;
        }
        return myMovements;
    }
    public void clear() {
        for (int i = 0; i < myMovements.length; i++) {
            myMovements[i] = null;
        }
    }

    public boolean hasCapture(Piece[][] board)
    {
        boolean capture = false;
        int tempX = getX();
        int tempY = getY();
        int i = 0;

        int dx = -1;
        int dy = -1;

        //Checks Left to right
        while (dx <= 1)
        {
            tempX += dx;
            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor())
            {
                capture = true;
                break;
            }
            else if (board[tempY][tempX] == null  && tempX < 5 && tempX > -1) continue;
            else if(dx == -1)
            {
                tempX = getX();
                dx = 1;
            }
            else break;
        }

        tempX = getX();

        //Checks Top to bottom
        while (dy <= 1)
        {
            if (capture) break;
            tempY += dy;
            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor())
            {
                capture = true;
                break;
            }
            else if (board[tempY][tempX] == null  && tempY < 5 && tempY > -1) continue;
            else if(dy == -1)
            {
                tempY = getY();
                dy = 1;
            }
            else break;
        }
        return capture;
    }
}
