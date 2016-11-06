package chess.board;

/**
 * Created by Brody on 11/4/16.
 */
public class Bishop extends Piece
{
	public Movement[] myMovements = new Movement[8];
	
	public Bishop(int x, int y, int color)
	{
		super(x, y, 3, color);
	}
	
	public Movement[] movement(Piece[][] board)
	{
		int i = 0;
		int j = 0;
		int tempX = getX();
		int tempY = getY();
		
		int dx = -1;
		int dy = -1;
		
		while (dy <= 1)
		{
			while (dx <= 1)
			{
				tempX += dx;
				tempY += dy;
				if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null) {
					myMovements[i++] = new Movement(tempY, tempX);
				} else if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()) {
					myMovements[i++] = new Movement(tempY, tempX);
					break;
				}
				else if(dx == 1)
				{
					break;
				}
				else if (tempX <= 0 || tempX >= 5) {
					tempX = getX();
					tempY = getY();
					dx = 1;
				}
				else continue; 

			}
			if (dy == 1) break;
			else
			{
				tempY = getY();
				tempX = getX();
				dy = 1;
				dx = -1;
			}
		}
		
		return myMovements;
	}
}
