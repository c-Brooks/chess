/* 		King Safety - Evaluates safety of each player's king
 * Encourage castling & fianchettos
 * Friendly pieces around king (5x5 box) are safe
 * Enemy pieces pointed at the king are unsafe
 */

/*
public class KingSafety {
	
	public static int kingSafetyW(String[][] pos) {
		int r = King.kingPosW / 8, c = King.kingPosW % 8;
		int eval = 0;

		if (!Board.kCastlingW) {
			if ("P".equals(Board.chessBoard[r - 1][c]))// pawn in front of king
				eval += 10;
			else if ("P".equals(Board.chessBoard[r - 2][c]) && "B".equals(Board.chessBoard[r - 1][c]))
				eval += 20;// fianchettos are safe
			else
				eval -= 30;
		}
		// Pieces around king
		for (int j = 0; j < 9; j++)
		{
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
			} catch (Exception e) {}
		}
		return eval;
	}
	public static int kingSafetyB(String[][] pos) {
		int r = King.kingPosB / 8, c = King.kingPosB % 8;
		int eval = 0;


		if (!Board.kCastlingB) {
			if ("p".equals(Board.chessBoard[r + 1][c]))// pawn in front of king
				eval += 10;
			else if ("p".equals(Board.chessBoard[r + 2][c]) && "b".equals(Board.chessBoard[r + 1][c]))
				eval += 20;// fianchettos are safe
			else
				eval -= 30;
		}
		for (int j = 0; j < 9; j++) {
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
		return eval;
	}
}*/
