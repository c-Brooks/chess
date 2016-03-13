/*
 * moves determines how the king can move, as well as castling and castling rights
 * kingSafe determines if the king is in check (legal move)
 * position is stored as integer kingPos for speed 
 * 
 */

public class king {

	public static int kingPosW = 60, kingPosB = 4;
	
	public static boolean kingSafeW() {
			bb.refreshMoves();
			if(bb.controlB()[kingPosW])
			return false;
			else
			return true;
		}
		
	public static boolean kingSafeB() {
		bb.refreshMoves();
		if(bb.controlW()[kingPosB])
		return false;
		else
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
					String newPos = board8x8.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
						kMovesW[(r - 1 + j / 3) * 8 + (c - 1 + j % 3)] = true;
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos)) {
						oldPiece = newPos;
						if (!bb.controlB()[(r - 1 + j / 3)*8 + (c - 1 + j % 3)])
							move = move + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
					}

					if (board8x8.kCastlingW && " ".equals(board8x8.chessBoard[7][5])
							&& " ".equals(board8x8.chessBoard[7][6])) {
						move = move + r + c + 7 + 6 + " ";
					}
					if (board8x8.qCastlingW && " ".equals(board8x8.chessBoard[7][3])
							&& " ".equals(board8x8.chessBoard[7][2]) && " ".equals(board8x8.chessBoard[7][1])) {
						move = move + r + c + 7 + 2 + " ";
					}

				} catch (Exception e) {
				}
			}
		}
		bb.kMovesW = kMovesW;
		return move;
	}

	public static String movesB(int i) {
		String move = "", oldPiece;
		int r = i / 8, c = i % 8;

		for (int j = 0; j < 9; j++) {
			if (j != 4) {
				try {
					String newPos = board8x8.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
						kMovesB[(r - 1 + j / 3) * 8 + (c - 1 + j % 3)] = true;

					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos)) {
						oldPiece = newPos;
						if (!bb.controlW()[(r - 1 + j / 3)*8 + (c - 1 + j % 3)])
							move = move + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
					}

					if (board8x8.kCastlingB && " ".equals(board8x8.chessBoard[0][5])
							&& " ".equals(board8x8.chessBoard[0][6])) {
						move = move + r + c + 0 + 6 + " ";
					}
					if (board8x8.qCastlingB && " ".equals(board8x8.chessBoard[0][3])
							&& " ".equals(board8x8.chessBoard[0][2]) && " ".equals(board8x8.chessBoard[0][1])) {
						move = move + r + c + 0 + 2 + " ";
					}
				} catch (Exception e) {
				}
			}
		}
		bb.kMovesB = kMovesB;
		return move;
	}
}