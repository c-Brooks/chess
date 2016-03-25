/*
 * Controls the movement of pawns, including en passant and promotion
 * 
 * 
 * 
 */
public class Pawn {

	public static boolean[] pMovesW = new boolean[64];
	public static boolean[] pMovesB = new boolean[64];
	
	public static boolean[] pTakesW = new boolean[64];
	public static boolean[] pTakesB = new boolean[64];
	
	public static boolean[] enPassantW = new boolean[8];
	public static boolean[] enPassantB = new boolean[8];

	public static String movesW(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;

		try {
			if (r == 6) {
				if (" ".equals(Board.chessBoard[r - 2][c]) && " ".equals(Board.chessBoard[r - 1][c])) {
					pMovesW[(r-2)*8+c] = true;
					oldPiece = Board.chessBoard[r - 2][c];

					Board.makeMove("" + r + c + (r - 2) + c + oldPiece);
					if(King.kingSafeW())
						move = move + r + c + (r - 2) + c + oldPiece;
					Board.undoMove("" + r + c + (r - 2) + c + oldPiece);
//					enPassantW[c] = true;
				}
			}
			if (" ".equals(Board.chessBoard[r - 1][c])) {
				pMovesW[(r-1)*8+c] = true;
				oldPiece = Board.chessBoard[r - 1][c];
				
				Board.makeMove("" + r + c + (r - 1) + c + oldPiece);
				if(King.kingSafeW())
					move = move + r + c + (r - 1) + c + oldPiece;
				Board.undoMove("" + r + c + (r - 1) + c + oldPiece);	
				}
 //capturing diagonally
			for (int j = -1; j <= 1; j += 2) {
				try{
				if (Character.isLowerCase((Board.chessBoard[r - 1][c + j]).charAt(0))) {
					pTakesW[(r-1)*8+c] = true;
					oldPiece = Board.chessBoard[r - 1][c + j];
					Board.makeMove("" + r + c + (r - 1) + (c+j) + oldPiece);
					if(King.kingSafeW())
						move = move + r + c + (r - 1) + (c+j) + oldPiece;
					Board.undoMove("" + r + c + (r - 1) + (c+j) + oldPiece);	
				}
				} catch(Exception e){}
			}
//en passant
			/*
			for (int j = -1; j <= 1; j += 2) {
				if ("p".equals((board8x8.chessBoard[r][c + j]).charAt(0)) && enPassantB[c + j] == true) {
					pTakesW[(r)*8+(c+j)] = true;
					oldPiece = board8x8.chessBoard[r][c + j];
					move = move + r + c + (r - 1) + (c + j) + "p";
					}
				}
				*/
		} catch (Exception e) {}
		return move;
	}

	public static String movesB(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;

		try {
			if (r == 1) {
				if (" ".equals(Board.chessBoard[r + 2][c]) && " ".equals(Board.chessBoard[r + 1][c])) {
					pMovesB[(r+2)*8+c] = true;
					oldPiece = Board.chessBoard[r + 2][c];
					Board.makeMove(""+r + c + (r + 2) + c + oldPiece);
					if(King.kingSafeB())
						move = move + r + c + (r + 2) + c + oldPiece;
					Board.undoMove(""+r + c + (r + 2) + c + oldPiece);
//					enPassantB[c] = true;
					
				}
			}
			if (" ".equals(Board.chessBoard[r + 1][c])) {
				pMovesB[(r+1)*8+c] = true;
				oldPiece = Board.chessBoard[r + 1][c];
				Board.makeMove(""+r + c + (r + 1) + c + oldPiece);
				if(King.kingSafeB())
					move = move + r + c + (r + 1) + c + oldPiece;
				Board.undoMove(""+r + c + (r + 1) + c + oldPiece);			
				}
			
//capturing diagonally
			for (int j = -1; j <= 1; j += 2) {
				try{
				if (Character.isUpperCase((Board.chessBoard[r + 1][c + j]).charAt(0))) {
					pTakesB[(r+1)*8+(c+j)] = true;
					oldPiece = Board.chessBoard[r + 1][c + j];
					Board.makeMove("" + r + c + (r + 1) + (c+j) + oldPiece);
					if(King.kingSafeB())
						move = move + r + c + (r + 1) + (c+j) + oldPiece;
					Board.undoMove("" + r + c + (r + 1) + (c+j) + oldPiece);			
				}
				} catch(Exception e){}
			}
			
			// TEST //
			
			/*
			for (int j = -1; j <= 1; j += 2) {
				if ("P".equals((Board.chessBoard[r][c + j]).charAt(0)) && enPassantW[c + j] == true) {
					pTakesB[r*8+(c+j)] = true;
					oldPiece = Board.chessBoard[r][c + j];
					move = move + r + c + (r + 1) + (c + j) + "P";
				}
			}
			*/
			
		} catch (Exception e) {}
		return move;
	}
	
	public static void controlW(int i)
	{
		int r = i / 8, c = i % 8;
		for (int j = -1; j <= 1; j += 2) {
			try{
				BB.pTakesW[(r-1)*8+(c+j)] = true;
//			if ("p".equals((Board.chessBoard[r][c + j]).charAt(0)) && enPassantW[c + j] == true) {
//				BB.pTakesW[r*8+(c+j)] = true;
//				}
			}catch(Exception e){}
		}
	}	
	
	public static void controlB(int i)
	{
		int r = i / 8, c = i % 8;
		for (int j = -1; j <= 1; j += 2) {
			try{
				BB.pTakesB[(r+1)*8+(c+j)] = true;
//			if ("p".equals((Board.chessBoard[r][c + j]).charAt(0)) && enPassantW[c + j] == true) {
//				BB.pTakesB[r*8+(c+j)] = true;
//				}
			}catch(Exception e){}
		}
	}	
}
