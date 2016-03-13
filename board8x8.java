/*					Board8x8:
 * Contains the chessBoard, a 2x2 array of strings
 * Upper case indicates White, lower case indicates Black
 * 
 * Also contains makeMove and undoMove functions and possibleMoves
 * 
 * 		
 */

public class board8x8
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
			
	// Changing kingPos when king movevs
	if ("K".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]))
		king.kingPosW=Character.getNumericValue(move.charAt(2))*8+Character.getNumericValue(move.charAt(3));
	if ("k".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]))
		king.kingPosB=Character.getNumericValue(move.charAt(2))*8+Character.getNumericValue(move.charAt(3));
	
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
		&& pawn.enPassantB[Character.getNumericValue(move.charAt(2))]==true)
			{
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(3))]=" ";
			}
	if((move.charAt(4))=='P' &&
	   "p".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])
	   && pawn.enPassantW[Character.getNumericValue(move.charAt(2))]==true)
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
		pawn.enPassantW[c]=false;
	if(whoseMove)
		pawn.enPassantB[c]=false;
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
			move+=king.movesW(i);
		break;
	case "k":	
		if(whoseMove)
			move+=king.movesB(i);
		break;
	case "Q":	
		if(!whoseMove)
			move+=queen.movesW(i);
		break;
	case "q":	
		if(whoseMove)
			move+=queen.movesB(i);
		break;
	case "R":	
		if(!whoseMove)
			move+=rook.movesW(i);
		break;
	case "r":	
		if(whoseMove)
			move+=rook.movesB(i);
		break;
		
	case "B":	
		if(!whoseMove)
			move+=bishop.movesW(i);
		break;
	case "b":	
		if(whoseMove)
			move+=bishop.movesB(i);
		break;
	
	case "N":	
		if(!whoseMove)
			move+=knight.movesW(i);
		break;
	case "n":	
		if(whoseMove)
			move+=knight.movesB(i);
		break;
		
	case "P":	
		if(!whoseMove)
			move+=pawn.movesW(i);
		break;
	case "p":	
		if(whoseMove)
			move+=pawn.movesB(i);
		break;
			}
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
    	
    	bb.refreshMoves();

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
    	if("7476 ".equals(move))
    	{
    		chessBoard[7][7] = "R";
    	}
    	if("0406 ".equals(move))
    	{
//    		undoMove("0705 ");
    		chessBoard[7][0] = "R";
//    		whoseMove = !whoseMove;
    	}
    	if("7472 ".equals(move))
    	{
//    		undoMove("7073 ");
    		chessBoard[0][7] = "r";
//    		whoseMove = !whoseMove;
    	}
    	if("0402 ".equals(move))
    	{
//    		undoMove("0003 ");
    		chessBoard[0][0] = "r";
//    		whoseMove = !whoseMove;
    	}
    }
}
