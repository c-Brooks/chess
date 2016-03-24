/* 		Activity - evaluates position based on piece activity
 * 
 * Development: develop, get castled early
 * Piece placement: bishops need diagonals, rooks need files, knights need outposts 
 * Pawn breaks: determine if a pawn break will unleash pieces
 * To take is a mistake!
 */

public class Activity {
	public static int development(String[][] pos) {

		return 1;
	}
	static int TEST=20;
	public static int piecePlacement(String[][] pos) {
		int pieceEvalW = 0;
		int pieceEvalB = 0;
		
		for (int i = 0; i < 64; i++) {
			switch (pos[i / 8][i % 8]) {
			case "N":
				pieceEvalW += knightEvalW(i);
				break;
			case "n":
				pieceEvalB += knightEvalB(i);
				break;
			case "B":
				pieceEvalW += knightEvalW(i);
				break;
			case "b":
				pieceEvalB += knightEvalW(i);
				break;
			case "R":
				pieceEvalW += rookEvalW(i);
				break;
			case "r":
				pieceEvalB += rookEvalB(i);
				break;
			}
		}
		return (pieceEvalW-pieceEvalB);
	}

	public static int knightEvalW(int i) 
	{
		int nEval = 0;
		int r = i / 8;
		int c = i % 8;
		if (c == 0 || c == 7 || r == 7 || r==0)
			nEval -=TEST;
	try{  //if (BB.pawnsW[i+8])
		{
		nEval+=20;
		if(PawnStructure.isBackward((r+1)*8+c))
			nEval+=30;
		}
	}catch (Exception e){}
	return nEval;
	}
	
	public static int knightEvalB(int i) 
	{
		int nEval = 0;
		int r = i / 8;
		int c = i % 8;
		if (c == 0 || c == 7 || r == 0 || r == 7)
			nEval -= TEST;
	try{//  if (BB.pawnsW[i-8])
		{
		nEval+=20;
		if(PawnStructure.isBackward((r-1)*8+c))
			nEval+=30;
		}
	}catch (Exception e){}
	return nEval;
	}
	
	public static int bishopEvalW(int i)
	{	int r = i / 8;
		int c = i % 8;
		int bEval=0;

		
		
		
		if (c == 0 || c == 7 || r == 0 || r == 7)
			bEval -= TEST;
		if ("p".equals(Board.chessBoard[r-1][c+1]) || "p".equals(Board.chessBoard[r-1][c-1]))
			bEval-=50;
		return bEval;
	}
	
	
	public static int bishopEvalB(int i)
	{	int r = i / 8;
		int c = i % 8;
		int bEval=0;

		
				

		if (c == 0 || c == 7 || r == 0 || r == 7)
			bEval -= TEST;
		if ("P".equals(Board.chessBoard[r+1][c+1]) || "P".equals(Board.chessBoard[r+1][c-1]))
			bEval-=50;
		return bEval;
	}
	

	public static int rookEvalW(int i) {
		int r = i / 8;
		int c = i % 8;
		int rEval = 0;
		for (int j = 0; j < 8; j++) {
			try {
				if ("P".equals(Board.chessBoard[j][c])) // on a closed file
					rEval -= j * 4;
				if ("R".equals(Board.chessBoard[j][c])) // rooks are doubled
					rEval += 30;
				if ("R".equals(Board.chessBoard[r][j])) // rooks connected
					rEval += 15;
//				if ("p".equals(board8x8.chessBoard[r][j]) && 
//					pawnStructure.isBackward(j*8+c)) // backward pawn on semi-open file AND BLACK
//					rEval += 20;
				if ("p".equals(Board.chessBoard[r-1][c+1]) || "p".equals(Board.chessBoard[r-1][c-1]))
					rEval-=50;
			} catch (Exception e) {}
		}
		return rEval;
	}

	public static int rookEvalB(int i) {
		int r = i / 8;
		int c = i % 8;
		int rEval = 0;
		for (int j = 0; j < 8; j++) {
			try {
				if ("p".equals(Board.chessBoard[j][c])) // on a closed file
					rEval -= (7-j) * 4;
				if ("r".equals(Board.chessBoard[j][c])) // rooks are doubled
					rEval += 30;
				if ("r".equals(Board.chessBoard[r][j])) // rooks connected
					rEval += 15;
//				if (pawnStructure.isBackward(j+c*8)) // backward pawn on semi-open file
	//				rEval += 20;
				if ("P".equals(Board.chessBoard[r+1][c+1]) || "P".equals(Board.chessBoard[r+1][c-1]))
					rEval-=50;
			} catch (Exception e) {}

		}
		return rEval;
	}
}
