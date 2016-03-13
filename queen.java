
public class queen {

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
					while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) {
						qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

						board8x8.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");
						if(king.kingSafeW())
							move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
						board8x8.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");							qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						moveCount++;
					}
					qMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
					if (Character.isLowerCase(board8x8.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
						oldPiece = board8x8.chessBoard[r + j * moveCount][c + k * moveCount];
						
						board8x8.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
						if(king.kingSafeW())
							move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
						board8x8.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);							}
					} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
		bb.qMovesW = qMovesW;
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
					
						while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) {		
							qMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

							board8x8.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");
							if(king.kingSafeB())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							board8x8.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");								moveCount++;
						}
							qMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						if (Character.isUpperCase(board8x8.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
							oldPiece = board8x8.chessBoard[r + j * moveCount][c + k * moveCount];
							board8x8.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
							if(king.kingSafeB())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
							board8x8.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);							}
					} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
		bb.qMovesB = qMovesB;
		return move;
	}
	public static void controlW(int i)
	{
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				{ try {
					while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) {
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
					while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) {
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