/*					Board8x8:
 * Contains the chessBoard, a 2x2 array of strings
 * Upper case indicates White, lower case indicates Black
 * 
 * Also contains makeMove and undoMove functions and possibleMoves
 * 
 * 					TO DO:
 * Take bitboard support out of each piece's mailbox functions
 * Bitboards don't work on white's move
 * 
 */

public class Board
	{
//	public static String colLUT[] = {"a", "b", "c", "d", "e", "f", "g", "h"};
//	public static String rowLUT[] = {"8", "7", "6", "5", "4", "3", "2", "1"};
	public static boolean whoseMove  = false; 		// False (0) means white to move
	public static boolean kCastlingW = true;
	public static boolean kCastlingB = true;
	public static boolean qCastlingW = true;
	public static boolean qCastlingB = true;

	public static String chessBoard[][] = {
			{"r", "n", "b", "q", "k", "b", "n", "r"},
			{"p", "p", "p", "p", "p", "p", "p", "p"},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{" ", " ", " ", " ", " ", " ", " ", " "},
			{"P", "P", "P", "P", "P", "P", "P", "P"},
			{"R", "N", "B", "Q", "K", "B", "N", "R"}};

	
	//--------------------------------------------------------------------------------------------------//
	
	public static void makeMove(String move)
	{
	chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]
   =chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
	chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
	whoseMove = !whoseMove;
	}
	
	public static void recordMove(String move)
	{	
		try{
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]
	   =chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
				
			whoseMove = !whoseMove;
			}catch (Exception e){System.out.println("Error at move: "+move);}


		// Moving rook when castling
		if("7476 ".equals(move))
		{
			makeMove("7775 ");
			whoseMove=!whoseMove;
		}
		if("0406 ".equals(move))
		{
			makeMove("0705 ");
			whoseMove = !whoseMove;
		}
		if("7472 ".equals(move))
		{
			makeMove("7073 ");
			whoseMove = !whoseMove;
		}
		if("0402 ".equals(move))
		{
			makeMove("0003 ");
			whoseMove = !whoseMove;
		}
			
	// Changing kingPos when king moves
	if ("K".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]))
		King.kingPosW=Character.getNumericValue(move.charAt(2))*8+Character.getNumericValue(move.charAt(3));
	if ("k".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]))
		King.kingPosB=Character.getNumericValue(move.charAt(2))*8+Character.getNumericValue(move.charAt(3));
	
	// Check if king or rooks are not in place, forfeit castling rights
	if (" ".equals(chessBoard[7][4]))
		kCastlingW=false; qCastlingW=false;
	if (" ".equals(chessBoard[0][4]))
		kCastlingB=false; qCastlingB=false;
	if (" ".equals(chessBoard[7][7]))
		kCastlingW=false;
	if (" ".equals(chessBoard[7][0]))
		qCastlingW=false;
	if (" ".equals(chessBoard[0][7]))
		kCastlingB=false;
	if (" ".equals(chessBoard[0][0]))
		qCastlingB=false;
	
// En passant
try{
	if((move.charAt(4))=='p' &&
	   "P".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])
		&& Pawn.enPassantB[Character.getNumericValue(move.charAt(2))]==true)
			{
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(3))]=" ";
			}
	if((move.charAt(4))=='P' &&
	   "p".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])
	   && Pawn.enPassantW[Character.getNumericValue(move.charAt(2))]==true)
			{
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(3))]=" ";
			}
	}catch(Exception e){};

// Promotion
	if("P".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])
		&& Character.getNumericValue(move.charAt(2))==0)
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]="Q";
	if("p".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])
	   && Character.getNumericValue(move.charAt(2))==7)
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]="q";
	
// After each move, switch sides to move and get rid of en passant rights
		for (int c=0; c<8; c++){
	if(!whoseMove)
		Pawn.enPassantW[c]=false;
	if(whoseMove)
		Pawn.enPassantB[c]=false;
		}
	}	


	public static String possibleMoves()
	{
	String move="";
	for (int i=0; i<64; i++)
		{
	switch (chessBoard[i/8][i%8])
			{
	case "K":	
		if(!whoseMove)
			move+=King.movesW(i);
		break;
	case "k":	
		if(whoseMove)
			move+=King.movesB(i);
		break;
	case "Q":	
		if(!whoseMove)
			move+=Queen.movesW(i);
		break;
	case "q":	
		if(whoseMove)
			move+=Queen.movesB(i);
		break;
	case "R":	
		if(!whoseMove)
			move+=Rook.movesW(i);
		break;
	case "r":	
		if(whoseMove)
			move+=Rook.movesB(i);
		break;
		
	case "B":	
		if(!whoseMove)
			move+=Bishop.movesW(i);
		break;
	case "b":	
		if(whoseMove)
			move+=Bishop.movesB(i);
		break;
	
	case "N":	
		if(!whoseMove)
			move+=Knight.movesW(i);
		break;
	case "n":	
		if(whoseMove)
			move+=Knight.movesB(i);
		break;
		
	case "P":	
		if(!whoseMove)
			move+=Pawn.movesW(i);
		break;
	case "p":	
		if(whoseMove)
			move+=Pawn.movesB(i);
		break;
			}
		}
		
		if(!whoseMove) // White castling
		{
			BB.getPieces();
			if (Board.kCastlingW && !(BB.piecesW[61] | BB.piecesB[61] | BB.controlB()[61])
								 && !(BB.piecesW[62] | BB.piecesB[62] | BB.controlB()[62]))
				move = move + 7 + 4 + 7 + 6 + " ";
			
			if (Board.qCastlingW && !(BB.piecesW[59] | BB.piecesB[59] | BB.controlB()[59])
								 && !(BB.piecesW[58] | BB.piecesB[58] | BB.controlB()[58])
								 && !(BB.piecesW[57] | BB.piecesB[57] | BB.controlB()[57]))
				move = move + 7 + 4 + 7 + 2 + " ";
		}
		if(whoseMove) // Black castling
		{
			BB.getPieces();
			if (Board.kCastlingB && !(BB.piecesB[5] | BB.piecesB[5] | BB.controlW()[5])
					 			 && !(BB.piecesB[6] | BB.piecesB[6] | BB.controlW()[6]))
				move = move + 0 + 4 + 0 + 6 + " ";
		
			if (Board.qCastlingB && !(BB.piecesW[1] | BB.piecesB[1] | BB.controlB()[1])
								 && !(BB.piecesW[2] | BB.piecesB[2] | BB.controlB()[2])
								 && !(BB.piecesW[3] | BB.piecesB[3] | BB.controlB()[3]))
				move = move + 0 + 4 + 0 + 2 + " ";
		}
		
	return move;
	}

    public static void undoMove(String move) {

    	// 0102P (chessBoard[][])
    	// 01234 (charAt)
    	//(1) move 02(23) to 01 (01)
    	//(2) put P (4) at 23
    	
    	chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]
    	=chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
    	           
    	chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]
    	 =String.valueOf(move.charAt(4));

    	whoseMove=!whoseMove;
    	
//    	BB.refreshMoves();

   /* 	
   	try{ if(!("P".equals(chessBoard[move.charAt(0)][move.charAt(1)]) 
        		 &&  (move.charAt(3)!='0' || move.charAt(3)!='7'))) 
       {
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]
           =chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]
           =String.valueOf(move.charAt(4));
            
        } 
       
        else {
  //          if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))]="P";
            chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(2));
        }
        whoseMove=!whoseMove;
       }catch(Exception e){System.out.println("~~~~~~~~~~~ERROR~~~~~~~~~~~");}
 */
    	
    	
    	
    	// Moving rook when castling
    	if("7476 ".equals(move) && ("K".equals(chessBoard[Character.getNumericValue(move.charAt(2))]
    			[Character.getNumericValue(move.charAt(3))]))
)
    	{
    		chessBoard[7][7] = "R";
    	}
    	if("0406 ".equals(move) && ("K".equals(chessBoard[Character.getNumericValue(move.charAt(2))]
    			[Character.getNumericValue(move.charAt(3))])))
    	{
//    		undoMove("0705 ");
    		chessBoard[7][0] = "R";
//    		whoseMove = !whoseMove;
    	}
    	if("7472 ".equals(move) && ("k".equals(chessBoard[Character.getNumericValue(move.charAt(2))]
    			[Character.getNumericValue(move.charAt(3))])))
    	{
//    		undoMove("7073 ");
    		chessBoard[0][7] = "r";
//    		whoseMove = !whoseMove;
    	}
    	if("0402 ".equals(move) && ("k".equals(chessBoard[Character.getNumericValue(move.charAt(2))]
    			[Character.getNumericValue(move.charAt(3))])))
    	{
//    		undoMove("0003 ");
    		chessBoard[0][0] = "r";
//    		whoseMove = !whoseMove;
    	}
    }
}
