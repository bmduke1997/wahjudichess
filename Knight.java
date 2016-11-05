package board;

/**
 * Created by Brody on 11/4/16.
 */
public class Knight extends Piece
{
    private Movement[] myMovements = new Movement[8];

    public Knight(int x, int y, int value, int color)
    {
        super(x, y, value, color);
    }

    public Movement[] movement(Piece[][] board)
    {
        int i = -1;
        int tempX = getX();
        int tempY = getY();
        
        int dx = -1;
        int dy = -2;
        
        while (dx <= 1)
        {
        	tempX += dx;
        	while (dy <= 2)
        	{
	            tempY += dy;
	            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null)
	            {
	                myMovements[i++] = new Movement(tempX, tempY);
	            }
	            else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor())
	            {
	                myMovements[i++] = new Movement(tempX, tempY);
	                break;
	            }
	            else if(dy == -2)
	            {
	                tempY = getY();
	                dy = 2;
	            }
	            else break;
        	}
        	if(dx == 1) break;
        	else
        	{
        		tempX = getX();
        		dx = 1;
        		dy = -2;
        	}
        }
        
        dx = -2;
        dy = -1;
        
        while (dx <= 2)
        {
        	tempX += dx;
        	while (dy <= 1)
        	{
	            tempY += dy;
	            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null){
	                myMovements[i++] = new Movement(tempX, tempY);
	            }
	            else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()){
	                myMovements[i++] = new Movement(tempX, tempY);
	                break;
	            }
	            else if(dy == -1)
	            {
	                tempY = getY();
	                dy = 1;
	            }
	            else break;
        	}
        	if(dx == 2) break;
        	else
        	{
        		tempX = getX();
        		dx = 2;
        		dy = -1;
        	}
        }
        
        return myMovements;
    }
}
