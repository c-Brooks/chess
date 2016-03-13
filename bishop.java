
public class bishop {

	static boolean[] bMovesW = new boolean[64];
	static boolean[] bMovesB = new boolean[64];

	public static String movesW(int i) {
		String move = "";
		String newMove = "";
		String oldPiece = "";
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				if (j != 0 & k != 0) {
					try {
						while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) {
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							bMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

							if (!board8x8.whoseMove) // White to move
							{
								board8x8.makeMove(newMove);
								if (king.kingSafeW())
									move = move + newMove;
								board8x8.undoMove(newMove);
								moveCount++;
							}
						}
						bMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						if (Character
								.isLowerCase(board8x8.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
							oldPiece = board8x8.chessBoard[r + j * moveCount][c + k * moveCount];
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
							if (!board8x8.whoseMove) {
								board8x8.makeMove(newMove);
								if (king.kingSafeW())
									move = move + newMove;
								board8x8.undoMove(newMove);
							}
						}
					} catch (Exception e) {
					}
					moveCount = 1;
				}
			}
		}
		bb.bMovesW = bMovesW;
		return move;
	}

	public static String movesB(int i) {
		String move = "";
		String newMove = "";
		String oldPiece = "";
		int r = i / 8, c = i % 8;
		int moveCount = 1;

		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
				if (j != 0 & k != 0) {
					try {
						while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) 
						{
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							bMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

								board8x8.makeMove(newMove);
								if (king.kingSafeB())
									move = move + newMove;
								board8x8.undoMove(newMove);
								moveCount++;
						}
						bMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						if (Character
								.isUpperCase(board8x8.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
							oldPiece = board8x8.chessBoard[r + j * moveCount][c + k * moveCount];
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;

							if (board8x8.whoseMove) {
								board8x8.makeMove(newMove);
								if (king.kingSafeB())
									move = move + newMove;
								board8x8.undoMove(newMove);
							}
						}
					} catch (Exception e) {
					}
					moveCount = 1;
				}
			}
		}
		bb.bMovesB = bMovesB;
		return move;
	}

	public static void controlB(int i)
	{
		int r = i / 8, c = i % 8;
		int moveCount = 1;
		
		
		for (int j = 1; j >= -1; j--) {
			for (int k = 1; k >= -1; k--) {
			if (j != 0 & k != 0)
				{
					try {
						while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) 
						{
							bMovesB[(r+j*moveCount)*8+(c+k*moveCount)] = true;
							moveCount++;
						}
						bMovesB[(r+j*moveCount)*8+(c+k*moveCount)] = true;
					}catch(Exception e){}
				}		
			}
		}
	}
		public static void controlW(int i)
		{
			int r = i / 8, c = i % 8;
			int moveCount = 1;
			
			
			for (int j = 1; j >= -1; j--) {
				for (int k = 1; k >= -1; k--) {
				if (j != 0 & k != 0)
					{
						try {
							while (" ".equals(board8x8.chessBoard[r + j * moveCount][c + k * moveCount])) 
							{
								bMovesW[(r+j*moveCount)*8+(c+k*moveCount)] = true;
								moveCount++;
							}
							bMovesW[(r+j*moveCount)*8+(c+k*moveCount)] = true;
						}catch(Exception e){}
					}		
				}
			}
	}
}