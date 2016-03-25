
public class Rook {
	
	public static boolean[] rMovesW = new boolean[64];
	public static boolean[] rMovesB = new boolean[64];
	
	public static String movesW(int i) {
		String move = "";
		String oldPiece = "";
		int r = i / 8, c = i % 8;
		int moveCount = 1;
		
		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				if (j != 0 ^ k != 0) {
					try {
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
							rMovesW [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
							
							Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");
							if(King.kingSafeW())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");

							moveCount++;
						}
						rMovesW [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
						if (Character.isLowerCase(Board.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0)))
						{
							oldPiece = Board.chessBoard[r + j * moveCount][c + k * moveCount];
							
							Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
							if(King.kingSafeW())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
							Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
						}
						} catch (Exception e) {
					}
					moveCount = 1;
				}
			}
		}
		return move;
	}

	public static String movesB(int i) {
		String move = "";
		String oldPiece = "";
		int r = i / 8, c = i % 8;
		int moveCount = 1;
		
		
		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				if (j != 0 ^ k != 0) {
					try {
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
							rMovesB [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
							Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");
							if(King.kingSafeB())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + " ");							moveCount++;
						}
						rMovesB [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
						if (Character.isUpperCase(Board.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0)))
						{
							oldPiece = Board.chessBoard[r + j * moveCount][c + k * moveCount];
							Board.makeMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);
							if(King.kingSafeB())
								move = move + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
							Board.undoMove(""+r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece);						}
						} catch (Exception e) {
					}
					moveCount = 1;
				}
			}
		}
//		rMovesB = BB.rMovesB;
		return move;
	}
	
	public static void controlW(int i)
	{
		int r = i / 8, c = i % 8;
		int moveCount = 1;
		
		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				if (j != 0 ^ k != 0) {
					try {
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
							BB.rMovesW [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
							moveCount++;
						}
						BB.rMovesW [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
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
				if (j != 0 ^ k != 0) {
					try {
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
							BB.rMovesB [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
							moveCount++;
						}
						BB.rMovesB [(r + j * moveCount)*8 + (c + k * moveCount)] = true;
						} catch (Exception e) {}
					moveCount = 1;
				}
			}
		}
	}
	
	
}