/*
 * Controls the movement of pawns, including en passant and promotion
 * 
 * 
 * 
 */
public class pawn {

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
				if (" ".equals(board8x8.chessBoard[r - 2][c]) && " ".equals(board8x8.chessBoard[r - 1][c])) {
					pMovesW[(r-2)*8+c] = true;
					oldPiece = board8x8.chessBoard[r - 2][c];

					board8x8.makeMove("" + r + c + (r - 2) + c + oldPiece);
					if(king.kingSafeW())
						move = move + r + c + (r - 2) + c + oldPiece;
					board8x8.undoMove("" + r + c + (r - 2) + c + oldPiece);
//					enPassantW[c] = true;
				}
			}
			if (" ".equals(board8x8.chessBoard[r - 1][c])) {
				pMovesW[(r-1)*8+c] = true;
				oldPiece = board8x8.chessBoard[r - 1][c];
				
				board8x8.makeMove("" + r + c + (r - 1) + c + oldPiece);
				if(king.kingSafeW())
					move = move + r + c + (r - 1) + c + oldPiece;
				board8x8.undoMove("" + r + c + (r - 1) + c + oldPiece);	
				}
 //capturing diagonally
			for (int j = -1; j <= 1; j += 2) {
				if (Character.isLowerCase((board8x8.chessBoard[r - 1][c + j]).charAt(0))) {
					pTakesW[(r-1)*8+c] = true;
					oldPiece = board8x8.chessBoard[r - 1][c + j];
					board8x8.makeMove("" + r + c + (r - 1) + (c+j) + oldPiece);
					if(king.kingSafeW())
						move = move + r + c + (r - 1) + (c+j) + oldPiece;
					board8x8.undoMove("" + r + c + (r - 1) + (c+j) + oldPiece);					}
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
		bb.pMovesW=pMovesW;
		bb.pTakesW=pTakesW;
		return move;
	}

	public static String movesB(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;

		try {
			if (r == 1) {
				if (" ".equals(board8x8.chessBoard[r + 2][c]) && " ".equals(board8x8.chessBoard[r + 1][c])) {
					pMovesB[(r+2)*8+c] = true;
					oldPiece = board8x8.chessBoard[r + 2][c];
					board8x8.makeMove(""+r + c + (r + 2) + c + oldPiece);
					if(king.kingSafeB())
						move = move + r + c + (r + 2) + c + oldPiece;
					board8x8.undoMove(""+r + c + (r + 2) + c + oldPiece);
//					enPassantB[c] = true;
					
				}
			}
			if (" ".equals(board8x8.chessBoard[r + 1][c])) {
				pMovesB[(r+1)*8+c] = true;
				oldPiece = board8x8.chessBoard[r + 1][c];
				board8x8.makeMove(""+r + c + (r + 1) + c + oldPiece);
				if(king.kingSafeB())
					move = move + r + c + (r + 1) + c + oldPiece;
				board8x8.undoMove(""+r + c + (r + 1) + c + oldPiece);			
				}
			
//capturing diagonally
			for (int j = -1; j <= 1; j += 2) {
				if (Character.isUpperCase((board8x8.chessBoard[r + 1][c + j]).charAt(0))) {
					pTakesB[(r+1)*8+(c+j)] = true;
					oldPiece = board8x8.chessBoard[r + 1][c + j];
					board8x8.makeMove("" + r + c + (r + 1) + (c+j) + oldPiece);
					if(king.kingSafeB())
						move = move + r + c + (r + 1) + (c+j) + oldPiece;
					board8x8.undoMove("" + r + c + (r + 1) + (c+j) + oldPiece);			
				}
			}
			/*
			for (int j = -1; j <= 1; j += 2) {
				if ("P".equals((board8x8.chessBoard[r][c + j]).charAt(0)) && enPassantW[c + j] == true) {
					pTakesB[r*8+(c+j)] = true;
					oldPiece = board8x8.chessBoard[r][c + j];
					move = move + r + c + (r + 1) + (c + j) + "P";
				}
			}
			*/
		} catch (Exception e) {}
		bb.pMovesB=pMovesB;
		bb.pTakesB=pTakesB;
		return move;
	}
	
	public static void controlW(int i)
	{
		int r = i / 8, c = i % 8;
		for (int j = -1; j <= 1; j += 2) {
			try{
				pTakesW[(r-1)*8+(c+j)] = true;
//			if ("p".equals((board8x8.chessBoard[r][c + j]).charAt(0)) && enPassantW[c + j] == true) {
//				pTakesW[r*8+(c+j)] = true;
//				}
			}catch(Exception e){}
		}
	}	
	
	public static void controlB(int i)
	{
		int r = i / 8, c = i % 8;
		for (int j = -1; j <= 1; j += 2) {
			try{
				pTakesB[(r+1)*8+(c+j)] = true;
//			if ("p".equals((board8x8.chessBoard[r][c + j]).charAt(0)) && enPassantW[c + j] == true) {
//				pTakesB[r*8+(c+j)] = true;
//				}
			}catch(Exception e){}
		}
	}	
}
