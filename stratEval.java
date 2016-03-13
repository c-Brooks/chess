
public class stratEval {

	public static int materialBalance(String[][] pos)
	{
		int materialEval=0; 
		int materialW=0; int materialB=0;
		
		for(int i=0; i<64; i++)
		{
		switch (pos[i/8][i%8])
			{
		case "P":
			materialW+=100;
			break;
		case "B":
			materialW+=340;
			break;
		case "N":
			materialW+=320;
			break;
		case "R":
			materialW+=500;
			break;
		case "Q":
			materialW+=900;
			break;
		case "K":
			materialW+=100000;
			break;
		case "p":
			materialB+=100;
			break;
		case "b":
			materialB+=340;
			break;
		case "n":
			materialB+=320;
			break;
		case "r":
			materialB+=500;
			break;
		case "q":
			materialB+=900;
			break;
		case "k":
			materialB+=100000;
			break;
			}
		}
		materialEval = 2*(materialW-materialB);
		return materialEval;
	}
	
	public static int globalEval(String[][] pos)
	{
		int eval=0;
		
 		eval+=materialBalance(pos)+kingSafety.kingSafetyW(board8x8.chessBoard)
		-kingSafety.kingSafetyB(board8x8.chessBoard)+activity.piecePlacement(board8x8.chessBoard)
		+pawnStructure.spaceW(board8x8.chessBoard)-pawnStructure.spaceB(board8x8.chessBoard)
		+pawnStructure.pawnCenter(board8x8.chessBoard);

		
		for (int i=0; i<64; i++)
		{
		if(bb.controlB()[i] && bb.piecesW[i] && !bb.controlW()[i]) // attackedW
			eval-=20;
		if(bb.controlW()[i] && bb.piecesB[i] && !bb.controlB()[i]) // attackedB
			eval+=20;
		if(bb.pTakesB[i] && bb.piecesW[i])
			eval+=40;
		if(bb.pTakesW[i] && bb.piecesB[i])
			eval-=40;
		}
		
	    if(board8x8.kCastlingW) eval+=30;
	    if(board8x8.qCastlingW) eval+=10;
	    if(board8x8.kCastlingB) eval-=30;
	    if(board8x8.qCastlingB) eval-=10;
	    
		return eval;
	}
}