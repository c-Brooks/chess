
/*				Bitboards:
 * Bitboards are represented arrays of 64 booleans
 *   since Java doesn't support 64-bit unsigned longs
 * We can preform bitwise operations on these boards
 *   by cycling through the 64 indices
 */


@SuppressWarnings("unused")
public class BB {
	

// MOVES: Also covers own pieces that are protected
	public static boolean[] pTakesW = new boolean[64];
	public static boolean[] pTakesB = new boolean[64];
	public static boolean[] pMovesW = new boolean[64];
	public static boolean[] pMovesB = new boolean[64];
	public static boolean[] bMovesW = new boolean[64];
	public static boolean[] bMovesB = new boolean[64];
	public static boolean[] nMovesW = new boolean[64];
	public static boolean[] nMovesB = new boolean[64];
	public static boolean[] rMovesW = new boolean[64];
	public static boolean[] rMovesB = new boolean[64];
	public static boolean[] qMovesW = new boolean[64];
	public static boolean[] qMovesB = new boolean[64];
	public static boolean[] kMovesW = new boolean[64];
	public static boolean[] kMovesB = new boolean[64];
	
// PIECES: Stored independent of colour for memory
	public static boolean[] pawnsW  = new boolean[64];
	public static boolean[] pawnsB  = new boolean[64];
	public static boolean[] bishops = new boolean[64];
	public static boolean[] knights = new boolean[64];
	public static boolean[] rooks   = new boolean[64];
	public static boolean[] queens  = new boolean[64];
	public static boolean[] kings   = new boolean[64];
	
	public static boolean[] piecesW  = new boolean[64];
	public static boolean[] piecesB  = new boolean[64];


	
// BOARD: Board properties for evaluation  FIX TO ACCOMODATE BOOLEAN ARRAYS
	
	/*
	private static long empty        = 0L;  
	private static long lightSquares = 0x5555555555555555L;
	private static long darkSquares  = 0x2AAAAAAAAAAAAAAAL;
	private static long aFile = 0x0101010101010101L;
	private static long bFile = 0x0202020202020202L;
	private static long cFile = 0x0404040404040404L;
	private static long dFile = 0x0808080808080808L;
	private static long eFile = 0x1010101010101010L;
	private static long fFile = 0x2020202020202020L;
	private static long gFile = 0x4040404040404040L;
	private static long hFile = 0x8080808080808080L;
	private static long center = 0x0000001818000000L;
	*/

	public static void getPieces()
	{
		boolean[] pieces = new boolean[64];
		for (int i=0; i<64; i++)
		{
			switch (Board.chessBoard[i/8][i%8])
			{
case "K":	
	kings[i]   = true;
	piecesW[i] = true;
	break;
case "k":	
	kings[i]   = true;
	piecesB[i] = true;
	break;
case "Q":	
	queens[i]  = true;	
	piecesW[i] = true;
	break;
case "q":	
	queens[i]  = true;
	piecesB[i] = true;
	break;
case "R":	
	rooks[i]   = true;
	piecesW[i] = true;
	break;
case "r":	
	rooks[i]   = true;
	piecesB[i] = true;
	break;
case "B":	
	bishops[i] = true;
	piecesW[i] = true;
	break;
case "b":	
	bishops[i] = true;
	piecesB[i] = true;
	break;
case "N":	
	knights[i] = true;
	piecesW[i] = true;
	break;
case "n":	
	knights[i] = true;
	piecesB[i] = true;
	break;
case "P":	
	pawnsW[i]  = true;
	break;
case "p":	
	pawnsB[i]  = true;
	break;
			}	
		}
	}
	
	public static boolean[] controlW()
	{
		boolean[] controlW = new boolean[64];
		for (int i=0; i<64; i++)
		controlW[i] = bMovesW[i]|nMovesW[i]|rMovesW[i]|qMovesW[i]|kMovesW[i]|pTakesW[i]|kMovesW[i];
		return controlW;
	}
	public static boolean[] controlB()
	{
		boolean[] controlB = new boolean[64];
		for (int i=0; i<64; i++)
		controlB[i] = bMovesB[i]|nMovesB[i]|rMovesB[i]|qMovesB[i]|kMovesB[i]|pTakesB[i]|kMovesB[i];
		return controlB;
	}
	
/*
	public static long attackedW() // Does not count pawn attacks
		{ return (controlB() & piecesW()); }
	
	public static long attackedB() // Does not count pawn attacks
		{ return (controlW() & piecesB()); }
	
	public static long defendedW()
		{ return (controlW() & piecesW()); }
	
	public static long defendedB()
		{ return (controlW() & piecesB()); }
*/
	
	
	public static void clearBB() // Reset bitboards
	{
		for (int i = 0; i<64; i++)
		{
		pTakesW[i] = false;
		pMovesW[i] = false;
		bMovesW[i] = false;
		nMovesW[i] = false;
		rMovesW[i] = false;
		qMovesW[i] = false;
		kMovesW[i] = false;

		pTakesB[i] = false;
		pMovesB[i] = false;
		bMovesB[i] = false;
		nMovesB[i] = false;
		rMovesB[i] = false;
		qMovesB[i] = false;
		kMovesB[i] = false;
		}
	}

	public static void refreshMoves() 
	{
		clearBB();

		for (int i = 0; i < 64; i++) {
			switch (Board.chessBoard[i / 8][i % 8]) 
			{
			case "K":
				King.controlW(i);
				break;
			case "k":
				King.controlB(i);
				break;
			case "Q":
				Queen.controlW(i);
				break;
			case "q":
				Queen.controlB(i);
				break;
			case "R":
				Rook.controlW(i);
				break;
			case "r":
				Rook.controlB(i);
				break;
			case "B":
				Bishop.controlW(i);
				break;
			case "b":
				Bishop.controlB(i);
				break;
			case "N":
				Knight.controlW(i);
				break;
			case "n":
				Knight.controlB(i);
				break;
			case "P":
				Pawn.controlW(i);
				break;
			case "p":
				Pawn.controlB(i);
				break;
			}
		}
	}

	public static void print(boolean[] bb) // For testing purposes
	{
		System.out.println("");
		for (int i = 0; i < 64; i++) {
			if (bb[i] == true)
				System.out.print(" X");
			if (bb[i] == false)
				System.out.print(" .");

			if (i % 8 == 7)
				System.out.println("  |"+(8-i/8));
		}
		System.out.println(" ________________\n a b c d e f g h ");
	}
}
