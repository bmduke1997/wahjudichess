package chess.board;

/**
 * Created by Brody on 11/4/16.
 */
public class Bishop extends Piece
{
	private Movement[] myMovements = new Movement[8];
	
	public Bishop(int x, int y, int color)
	{
		super(x, y, 3, color);
	}
	
	public Movement[] movement(Piece[][] board)
	{
		int i = -1;
		int tempX = getX();
		int tempY = getY();
		
		int dx = -1;
		int dy = -1;
		
		while (dx <= 1)
		{
			while (dy <= 1)
			{
				tempX += dx;
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
	            else if(dy == 1) 
	            {
	            	break;
	           	}
	            else if (tempY <= 0 || tempY >= 5)
	            {
	            	tempY = getY();
	            	dy = 1;
	            }
	            else break;
			}
			if (dx == 1) break;
			else
			{
				tempX = getX();
				dx = 1;
			}
		}
		
		return myMovements;
	}
}
