package chess.board;

/**
 * Created by Brody on 11/4/16.
 */
public class King extends Piece
{
	private Movement[] myMovements = new Movement[8];

	public King(int x, int y, int color)
	{
		super(x, y, 2, color);
	}

	public Movement[] movement(Piece[][] board)
	{
		int i = 0;
		int tempX = getX();
		int tempY = getY();

		for (int j = -1; j < 2; j++)
		{
			tempY +=j;
			for (int k = -1; k < 2; k++) {
				tempX += k;
				if(j == 0 && k == 0) continue;
				else if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX] == null) {
					myMovements[i++] = new Movement(tempY, tempX);
				} else if (tempY > -1 && tempX > -1 && tempY < 5 && tempX < 5 && board[tempY][tempX].getColor() != getColor()) {
					myMovements[i++] = new Movement(tempY, tempX);
				}
				tempX = getX();
			}
			tempY = getY();
		}
		return myMovements;
	}
}
