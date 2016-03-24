/*
 * moves determines how the king can move, as well as castling and castling rights
 * kingSafe determines if the king is in check (legal move)
 * position is stored as integer kingPos for speed 
 * 
 */

public class King {

	public static int kingPosW = 60, kingPosB = 4;
	
	public static boolean kingSafeW() {
		/*
			BB.refreshMoves();
			if(BB.controlB()[kingPosW])
			return false;
			else
			return true;
		}
		
	public static boolean kingSafeB() {
		BB.refreshMoves();
		if(BB.controlW()[kingPosB])
		return false;
		else
		*/
		return true;
	}

	public static boolean kingSafeB() {
		/*
			BB.refreshMoves();
			if(BB.controlB()[kingPosW])
			return false;
			else
			return true;
		}
		
	public static boolean kingSafeB() {
		BB.refreshMoves();
		if(BB.controlW()[kingPosB])
		return false;
		else
		*/
		return true;
	}

	
	
	//                                                   ~~MOVES~~
	static boolean[] kMovesW = new boolean[64];
	static boolean[] kMovesB = new boolean[64];

	public static String movesW(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;

		for (int j = 0; j < 9; j++) {
			if (j != 4) {
				try {
					String newPos = Board.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
						kMovesW[(r - 1 + j / 3) * 8 + (c - 1 + j % 3)] = true;
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos)) {
						oldPiece = newPos;
						Board.makeMove(move);
						if(King.kingSafeW())
							move = move + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
						Board.undoMove(move);
					}
				} catch (Exception e) {
				}
			}
		}
//		BB.kMovesW = kMovesW;
		return move;
	}

	public static String movesB(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;

		for (int j = 0; j < 9; j++) {
			if (j != 4) {
				try {
					String newPos = Board.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
						kMovesB[(r - 1 + j / 3) * 8 + (c - 1 + j % 3)] = true;

					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos)) {
						oldPiece = newPos;
						Board.makeMove(move);
	// FIX !!!! 
						if(King.kingSafeW())  
							move = move + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
						Board.undoMove(move);
					}
				} catch (Exception e) {
				}
			}
		}
//		BB.kMovesB = kMovesB;
		return move;
	}
}