/* 		King Safety - Evaluates safety of each player's king
 * Encourage castling & fianchettos
 * Friendly pieces around king (5x5 box) are safe
 * Enemy pieces pointed at the king are unsafe
 */


public class KingSafety {
	
	public static int kingSafetyW(String[][] pos) {
		int r = King.kingPosW / 8, c = King.kingPosW % 8;
		int eval = 0;

		if(true /* moveCount / material count */)
			if(r == 3 || r == 4  || r == 5) eval -= 25;
		if (!Board.kCastlingW) {
			if (BB.pawnsW[(r - 1)*8+c])// pawn in front of king
				eval += 10;
			else if (BB.pawnsW[(r + 2)*8+c] && BB.bishops[(r-1)*8+1])
				eval += 20;// fianchettos are safe
			else
				eval -= 30;
		}

		// Pieces around king
		for (int j = 0; j < 9; j++)
			for (int k = 1; k < 3; k++) {
				{

					int newRow = (r - 1 + j / 3) * k;
					int newCol = (c - 1 + j % 3) * k;

					try {
						if (BB.piecesW[newRow * k * 8 + newCol * k])
							eval += 5;

						if (BB.controlB()[newRow * 8 + newCol])
							eval -= 10;
						if (BB.controlW()[newRow * 8 + newCol])
							eval -= 5;
					} catch (Exception e) {
					}
				}
			}
		if (BB.controlB()[13]) eval+=20;
		return eval;
	}
	public static int kingSafetyB(String[][] pos) {
		int r = King.kingPosB / 8, c = King.kingPosB % 8;
		int eval = 0;


		if(true /* moveCount / material count */)
			if(r == 3 || r == 4  || r == 5) eval -= 25;
		
		if (!Board.kCastlingB) {
			if (BB.pawnsB[(r + 1)*8+c]) // pawn in front of king
				eval += 10;
			else if (BB.pawnsB[(r + 2)*8+c] && BB.bishops[(r-1)*8+1])
				eval += 20;             // fianchettos are safe
			else
				eval -= 30;
		}
		for (int j = 0; j < 9; j++) {
			for (int k=1; k<3; k++)
			try {
					if (BB.piecesW[(r - 1 + j / 3)*8+(c - 1 + j % 3)]
			 || BB.piecesW[(r - 1 + 2 * j / 3)*8+(c - 1 + 2 * j % 3)])
						eval += 5;

			int newRow = (r - 1 + j / 3);
			int newCol = (c - 1 + j % 3);

			if(BB.controlB()[newRow*8+newCol])
				eval-=10;
			if(BB.controlW()[newRow*8+newCol])
				eval-=5;
			}catch (Exception e){}
		}
		if(BB.controlW()[53]) eval += 20;
		return eval;
	}
}
