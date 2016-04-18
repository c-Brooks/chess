/** 		Activity - evaluates position based on piece activity.
 * 
 * Development: develop, get castled early.
 * Piece placement: bishops need diagonals, rooks need files, knights need outposts.
 * Pawn breaks: determine if a pawn break will unleash pieces.
 * To take is a mistake!
 **/

public class Activity 
{
	public static int development() {
		
		int dev = 0;
		BB.getPieces();
		BB.refreshMoves();
		for (int i = 0; i < 64; i++)
		{
			if(BB.piecesW[i] && !BB.controlB()[i]) // WHITE
				dev += BB.valueOf(i)/6*(7-i)/8;
			if(BB.piecesB[i] && !BB.controlW()[i]) // BLACK
				dev += BB.valueOf(i)/6*i/8;
		}
		return dev;
	}

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
			nEval -= 10;
	try{  
		if (BB.pawnsB[i+8] && !BB.pawnsB[i+7] && !BB.pawnsB[i+9])
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
			nEval -= 10;
	try{
		if (BB.pawnsW[i-8] && !BB.pawnsW[i-7] && !BB.pawnsW[i-9])
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
		
		if(BB.pawnsW[i-8] /* bishop blocking pawn */)
		bEval -= 15;
		
		if (c == 0 || c == 7 || r == 0 || r == 7)
			bEval -= 10;
		if (BB.pawnsB[(r-1)*8 + c+1] || BB.pawnsB[(r-1)*8 + c-1])
			bEval-=50;
		
		for (int j=0; j<64; j++){
			if(BB.bMovesW[j]) bEval+=2;
			if(BB.bMovesW[j] && BB.piecesB[j]) bEval+=5;
		}
		return bEval;
	}
	
	
	public static int bishopEvalB(int i)
	{	int r = i / 8;
		int c = i % 8;
		int bEval=0;

		if(BB.pawnsB[i-8] /* bishop blocking pawn */)
		bEval -= 15;
		
		if (c == 0 || c == 7 || r == 0 || r == 7)
			bEval -= 10;
		if (BB.pawnsW[(r-1)*8 + c+1] || BB.pawnsW[(r-1)*8 + c-1])
			bEval-=50;
		
		for (int j=0; j<64; j++){
			if(BB.bMovesW[j]) bEval+=2;
			if(BB.bMovesW[j] && BB.piecesB[j]) bEval+=5;
		}
		return bEval;
	}
	

	public static int rookEvalW(int i) {
		int r = i / 8;
		int c = i % 8;
		int rEval = 0;
		for (int j = 0; j < 64; j++) {
			try {
//				if (BB.fileOf(i)[j] && BB.pawnsW[j]) 			   // on a closed file
//					rEval -= j * 4;
				if (BB.rMovesW[j] && BB.rooks[j] && BB.piecesW[j]) // rooks are doubled / connected
					rEval += 10;
//				if (BB.rankOf(i)[j] && BB.pawnsB[j])			   // Opponent pawns on same rank as rook
//					rEval+=5;
				if ("p".equals(Board.chessBoard[r][j]) && 
					PawnStructure.isBackward(j*8+c))			   // backward pawn on semi-open file AND BLACK
					rEval += 20;
				if ("p".equals(Board.chessBoard[r-1][c+1]) || "p".equals(Board.chessBoard[r-1][c-1])) // R attacked
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
//				if (BB.fileOf(i)[j] && BB.pawnsB[j]) 			   // on a closed file
//					rEval -= (7-j) * 4;
				if (BB.rMovesB[j] && BB.rooks[j] && BB.piecesB[j]) // rooks are doubled / connected
					rEval += 10;
//				if (BB.rankOf(i)[j] && BB.pawnsW[j])			   // Opponent pawns on same rank as rook
//					rEval+=5;
				if (PawnStructure.isBackward(j+c*8))			   // backward pawn on semi-open file
					rEval += 20;
				if ("P".equals(Board.chessBoard[r+1][c+1]) || "P".equals(Board.chessBoard[r+1][c-1]))
					rEval-=50;
			} catch (Exception e) {}

		}
		return rEval;
	}
}
