/*
 * moves determines how the king can move, as well as castling. Castling rights are in Board.
 * kingSafe determines if the king is in check (legal move)
 * Position is stored as integer kingPos and kpTemp for speed 
 */

public class King 
{
	public static int kingPosW = 60, kingPosB = 4;
	
	public static boolean kingSafeW() 
	{
		BB.refreshMoves();
		if(BB.controlB()[kingPosW])
			return false;
		else
		return true;
	}
	
	public static boolean kingSafeB() 
	{
		BB.refreshMoves();
		if(BB.controlW()[kingPosB])
			return false;
		else
		return true;
	}

	
	//                                                ~~MOVES~~
	
	public static String movesW(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;
		int kpTemp = 0;
		
		for (int j = 0; j < 9; j++) {
			if (j != 4) {
				try {
					String newPos = Board.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos)) {
						oldPiece = newPos;
						Board.makeMove(""+r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece);
						kpTemp = kingPosW;
						kingPosW = (r - 1 + j / 3)*8 + (c - 1 + j % 3);
						if(King.kingSafeW())
							move = ""+move + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
						Board.undoMove(""+r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece);
						kingPosW = kpTemp;
					}
				} catch (Exception e) {}
			}
		}
		return move;
	}

	public static String movesB(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;
		int kpTemp = 0;

		for (int j = 0; j < 9; j++) {
			if (j != 4) {
				try {
					String newPos = Board.chessBoard[r - 1 + j / 3][c - 1 + j % 3];

					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos)) {
						oldPiece = newPos;
						kpTemp = kingPosB;
						Board.makeMove(""+r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece);
						kingPosB = (r - 1 + j / 3)*8 + (c - 1 + j % 3);
						if(King.kingSafeB())  
							move = ""+move + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
						Board.undoMove(""+r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece);
						kingPosB = kpTemp;
					}
				} catch (Exception e) {}
			}
		}
		return move;
	}

	public static void controlW(int i) {
		int r = i / 8, c = i % 8;

		for (int j = 0; j <= 9; j++) {
			if (j != 9) {
				try {
					BB.kMovesW[(r - 1 + j / 3) * 8 + (c - 1 + j % 3)] = true;
				} catch (Exception e) {}
			}
		}
	}

	public static void controlB(int i) {
		int r = i / 8, c = i % 8;

		for (int j = 0; j <= 9; j++) {
			if (j != 9) {
				try {
					BB.kMovesB[(r - 1 + j / 3) * 8 + (c - 1 + j % 3)] = true;
				} catch (Exception e) {}
			}
		}
	}
}