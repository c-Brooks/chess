
public class Queen {

	public static boolean[] qMovesW = new boolean[64];
	public static boolean[] qMovesB = new boolean[64];

	public static String movesW(int i) {
		String move = "";
		String oldPiece = "";
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				{ try {
					while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
						qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

						Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");
						if(King.kingSafeW())
							move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
						Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");							qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						moveCount++;
					}
					qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
					if (Character.isLowerCase(Board.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
						oldPiece = Board.chessBoard[r + j * moveCount][c + k * moveCount];
						
						Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
						if(King.kingSafeW())
							move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
						Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);							}
					} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
//		BB.qMovesW = qMovesW;
		return move;
	}

	public static String movesB(int i) {
		String move = "";
		String oldPiece = "";
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				{ try {
					
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {		
							qMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

							Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");
							if(King.kingSafeB())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");								moveCount++;
						}
							qMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						if (Character.isUpperCase(Board.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
							oldPiece = Board.chessBoard[r + j * moveCount][c + k * moveCount];
							Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
							if(King.kingSafeB())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
							Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);							}
					} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
//		BB.qMovesB = qMovesB;
		return move;
	}
	public static void controlW(int i)
	{
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				{ try {
					while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
						qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						moveCount++;
						}
					qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
					} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
	}
	public static void controlB(int i)
	{
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				{ try {
					while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
						qMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						moveCount++;
						}
					qMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
					} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
	}
}