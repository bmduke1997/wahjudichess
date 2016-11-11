package chess.board;

/**
 * Created by Brody on 11/4/16.
 */
public class Knight extends Piece
{
    private Movement[] myMovements = new Movement[8];

    public Knight(int x, int y, int color)
    {
        super(x, y, 5, color);
    }

    public Movement[] movement(Piece[][] board)
    {
        int i = 0;
        int tempX = getX();
        int tempY = getY();
        
        int dy;
        int dx;
        
        for(dy = -1; dy <= 1; dy++)
        {
			tempY += dy;
			for (dx = -2; dx <= 2; dx++)
			{
				tempX += dx;
				if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null) {
					myMovements[i++] = new Movement(tempY, tempX);
				}
				else if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()) {
					myMovements[i++] = new Movement(tempY, tempX);
				}
				if (dx == -2) {
					tempX = getX();
					dx = 1;
				}
			}
			if (dy == -1)
			{
				tempY = getY();
				tempX = getX();
				dy = 0;
			}
        }

        tempY = getY();
		tempX = getX();

        for (dy = -2; dy <= 2; dy++)
        {
        	tempY += dy;
        	for(dx = -1; dx <= 1; dx++)
        	{
	            tempX += dx;
	            if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null){
	                myMovements[i++] = new Movement(tempY, tempX);
	            }
	            else if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()){
	                myMovements[i++] = new Movement(tempY, tempX);
	            }
	            if(dx == -1)
	            {
	                tempX = getX();
	                dx = 0;
	            }
        	}
        	if(dy == -2) {
				tempY = getY();
				tempX = getX();
				dy = 1;
			}
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
		int i = 0;
		int tempX = getX();
		int tempY = getY();

		int dy;
		int dx;

		for(dy = -1; dy <= 1; dy++)
		{
			if (capture) break;
			tempY += dy;
			for (dx = -2; dx <= 2; dx++)
			{
				tempX += dx;
				if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()) {
					capture = true;
					break;
				}
				if (dx == -2) {
					tempX = getX();
					dx = 1;
				}
			}
			if (dy == -1)
			{
				tempY = getY();
				tempX = getX();
				dy = 0;
			}
		}

		tempY = getY();
		tempX = getX();

		for (dy = -2; dy <= 2; dy++)
		{
			if (capture) break;
			tempY += dy;
			for(dx = -1; dx <= 1; dx++)
			{
				tempX += dx;
				if(tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()){
					capture = true;
					break;
				}
				if(dx == -1)
				{
					tempX = getX();
					dx = 0;
				}
			}
			if(dy == -2) {
				tempY = getY();
				tempX = getX();
				dy = 1;
			}
		}

		return capture;
	}
}
