
public class Bishop {

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
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) {
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							bMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

							if (!Board.whoseMove) // White to move
							{
								Board.makeMove(newMove);
								if (King.kingSafeW())
									move = move + newMove;
								Board.undoMove(newMove);
								moveCount++;
							}
						}
						bMovesW[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						if (Character
								.isLowerCase(Board.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
							oldPiece = Board.chessBoard[r + j * moveCount][c + k * moveCount];
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;
							if (!Board.whoseMove) {
								Board.makeMove(newMove);
								if (King.kingSafeW())
									move = move + newMove;
								Board.undoMove(newMove);
							}
						}
					} catch (Exception e) {
					}
					moveCount = 1;
				}
			}
		}
//		BB.bMovesW = bMovesW;
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
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) 
						{
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + " ";
							bMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;

								Board.makeMove(newMove);
								if (King.kingSafeB())
									move = move + newMove;
								Board.undoMove(newMove);
								moveCount++;
						}
						bMovesB[(r + j * moveCount) * 8 + (c + k * moveCount)] = true;
						if (Character
								.isUpperCase(Board.chessBoard[r + j * moveCount][c + k * moveCount].charAt(0))) {
							oldPiece = Board.chessBoard[r + j * moveCount][c + k * moveCount];
							newMove = "" + r + c + (r + j * moveCount) + (c + k * moveCount) + oldPiece;

							if (Board.whoseMove) {
								Board.makeMove(newMove);
								if (King.kingSafeB())
									move = move + newMove;
								Board.undoMove(newMove);
							}
						}
					} catch (Exception e) {
					}
					moveCount = 1;
				}
			}
		}
//		BB.bMovesB = bMovesB;
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
						while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) 
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
							while (" ".equals(Board.chessBoard[r + j * moveCount][c + k * moveCount])) 
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