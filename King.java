package board;

/**
 * Created by Brody on 11/4/16.
 */
public class King extends Piece
{
	private Movement[] myMovements = new Movement[8];
	
	public King(int x, int y, int value, int color)
	{
		super(x, y, value, color);
	}
	
	public Movement[] movement(Piece[][] board)
	{
		int i = -1;
		int j = -1;
		int k = -1;
		int tempX = getX();
		int tempY = getY();
		
		for (; j < 2; j++)
		{
			tempX += j;
			for (; k < 2; k++)
			{
				if(j == 0 && k == 0) continue;
				
				tempY += k;
				if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null)
				{
		            myMovements[i++] = new Movement(tempX, tempY);
		        }
		        else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor())
		        {
		            myMovements[i++] = new Movement(tempX, tempY);
		            break;
		        }
		        else break;
			}
		}
		return myMovements;
	}
}