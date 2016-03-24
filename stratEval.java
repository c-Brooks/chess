
public class StratEval {

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
		
 		eval+=materialBalance(pos)+Activity.piecePlacement(Board.chessBoard)
		+PawnStructure.spaceW(Board.chessBoard)-PawnStructure.spaceB(Board.chessBoard)
		+PawnStructure.pawnCenter(Board.chessBoard);

		/*
		for (int i=0; i<64; i++)
		{
		if(BB.controlB()[i] && BB.piecesW[i] && !BB.controlW()[i]) // attackedW
			eval-=20;
		if(BB.controlW()[i] && BB.piecesB[i] && !BB.controlB()[i]) // attackedB
			eval+=20;
		if(BB.pTakesB[i] && BB.piecesW[i])
			eval+=40;
		if(BB.pTakesW[i] && BB.piecesB[i])
			eval-=40;
		}
		*/
 		
	    if(Board.kCastlingW) eval+=30;
	    if(Board.qCastlingW) eval+=10;
	    if(Board.kCastlingB) eval-=30;
	    if(Board.qCastlingB) eval-=10;
	    
		return eval;
	}
}