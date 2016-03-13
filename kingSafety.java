/* 		King Safety - Evaluates safety of each player's king
 * Encourage castling & fianchettos
 * Friendly pieces around king (5x5 box) are safe
 * Enemy pieces pointed at the king are unsafe
 */

public class kingSafety {
	
	public static int kingSafetyW(String[][] pos) {
		int r = king.kingPosW / 8, c = king.kingPosW % 8;
		int eval = 0;

		if (!board8x8.kCastlingW) {
			if ("P".equals(board8x8.chessBoard[r - 1][c]))// pawn in front of king
				eval += 10;
			else if ("P".equals(board8x8.chessBoard[r - 2][c]) && "B".equals(board8x8.chessBoard[r - 1][c]))
				eval += 20;// fianchettos are safe
			else
				eval -= 30;
		}
		// Pieces around king
		for (int j = 0; j < 9; j++)
		{
			try {
				if (bb.piecesW[(r - 1 + j / 3)*8+(c - 1 + j % 3)]
		 || bb.piecesW[(r - 1 + 2 * j / 3)*8+(c - 1 + 2 * j % 3)])
					eval += 5;
			
	
			int newRow = (r - 1 + j / 3);
			int newCol = (c - 1 + j % 3);
			
			if(bb.controlB()[newRow*8+newCol])
				eval-=10;
			if(bb.controlW()[newRow*8+newCol])
				eval-=5;
			} catch (Exception e) {}
		}
		return eval;
	}
	public static int kingSafetyB(String[][] pos) {
		int r = king.kingPosB / 8, c = king.kingPosB % 8;
		int eval = 0;


		if (!board8x8.kCastlingB) {
			if ("p".equals(board8x8.chessBoard[r + 1][c]))// pawn in front of king
				eval += 10;
			else if ("p".equals(board8x8.chessBoard[r + 2][c]) && "b".equals(board8x8.chessBoard[r + 1][c]))
				eval += 20;// fianchettos are safe
			else
				eval -= 30;
		}
		for (int j = 0; j < 9; j++) {
			try {
					if (bb.piecesW[(r - 1 + j / 3)*8+(c - 1 + j % 3)]
			 || bb.piecesW[(r - 1 + 2 * j / 3)*8+(c - 1 + 2 * j % 3)])
						eval += 5;

			int newRow = (r - 1 + j / 3);
			int newCol = (c - 1 + j % 3);

			if(bb.controlB()[newRow*8+newCol])
				eval-=10;
			if(bb.controlW()[newRow*8+newCol])
				eval-=5;
			}catch (Exception e){}
		}
		return eval;
	}
}
