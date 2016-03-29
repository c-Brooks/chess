
/*	    AI - This enables the engine to look ahead
 * 
 * It contains a branching algorithm known as Alpha-Beta
 * Implement Progressive Deepening (??)
 */

//								IDEAS
/*
 * 		Prog Deepening - assign # depending on where in candMoves the move is
 * search for the inverse of the # (use candMoves.length()-#)
 * 
 * 		If rating jumps by >100, search one more move - don't want to 
 * capture on the last evaluation
 */


// ##################################################################################### //


import java.util.*;

public class AI 
{		
		static String currentPath = "";
		static String bestPath = "";
		static int alpha = -1000000000;
		static int beta  =  1000000000;
		
		public static void reset() // Reset each turn
		{
			currentPath = "";
			bestPath = "";
			alpha = -1000000000;
			beta  =  1000000000;
		}
	
	public static int think(int depth, int breadth)
	{
					// FOR TESTING //
		
		System.out.println("\n\n#######################");
		System.out.println("# CURRENT:  " + currentPath);
		System.out.println("# BEST:     "    + bestPath);
		System.out.println("#######################\n\n");

// ------------------------------------------------------------------------------------- //


	/*				Alpha-Beta Algorithm:
	 * Get list of best (# = breadth) moves in order 
	 * Make one move and evaluate by calling alphaBeta recursively 
	 * Alpha (white) is trying to maximize, while Beta (black) is trying to minimize 
	 * Assuming black is CPU, select the lowest score of the end positions 
	 */
			
		
		String candMoves = listMoves(breadth);

		
		// If it's a leaf node
	if (depth == 0 || breadth <= 1) 
	{
		int endVal = StratEval.globalEval(Board.chessBoard);

		System.out.println("\nEndVal:"+endVal+"\nCURRENT PATH:  "+currentPath+"\n");
		System.out.println("\n                   BEST PATH:    "+bestPath+"\n");
		System.out.println("");
	
		return endVal;
	}
	
	int valTemp = 0;
	int value = 0;
	if(!Board.whoseMove)   {value = -1000000000;}
	else                   {value =  1000000000;}
	if(!"".equals(candMoves))
	{
		for(int i=0; i<candMoves.length(); i+=5) // Make each move, going down each path
		{
			Board.makeMove(candMoves.substring(i, i+5)); System.out.println("MAKE:"+candMoves.substring(i, i+5));
			currentPath += candMoves.substring(i, i+5);
			valTemp = think(depth-1,breadth-1);
			Board.undoMove(candMoves.substring(i, i+5)); System.out.println("UNDO:"+candMoves.substring(i, i+5));
		/*	
			// Alpha-Beta pruning
			if(Board.whoseMove && valTemp > beta)
				{ System.out.println("Alpha > Beta"); beta = 1000000000; return valTemp; }
			if(!Board.whoseMove && valTemp < alpha)
				{ System.out.println("Beta < Alpha"); alpha = -1000000000; return valTemp; }
			*/
		if(!Board.whoseMove && valTemp > value)
			alpha = value = valTemp; 
		if(Board.whoseMove && valTemp < value)
			{ beta = value = valTemp; bestPath = currentPath; }
		
		currentPath = currentPath.replaceAll(candMoves.substring(i, i+5), ""); // Update currentPath
		}
	}
	
	// Once all candidate moves are analyzed
	if(!Board.whoseMove)
	{ return value;}
	return value;
	}

// ------------------------------------------------------------------------------------- //
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
public static String listMoves(int breadth)
	{
		
		/*					Sorting:
		 * Each move is rated and sorted from best to worst
		 * The top moves (# = breadth) are considered the candMoves
		 * Sorting algorithm is simple - think about implementing Quick Sort
		 */
		
		String list = Board.possibleMoves();
		int length = list.length();
		String temp = "";
		String candMoves = "";
		LinkedList rateTemp = new LinkedList();
		
		int maxRateW = -1000000000;
		int maxRateB =  1000000000;
		
		
		for (int i = 0; i < length; i += 5) // go through every move substring and rate
		{
			try {
				String move = list.substring(i, i + 5);
				Board.makeMove(move);
		
				// ------------------ \\ Custom Evaluation // ------------------ \\
				
				// To motivate the engine to investigate moving pieces.
				// This is not included in StratEval because it does not effect the position.
				
				int valTest = 0;
				
				if (Board.whoseMove) // White evaluation (BLACK TO MOVE)
				{
					valTest = StratEval.globalEval(Board.chessBoard); 
					int r = Character.getNumericValue(move.charAt(0));
					int c = Character.getNumericValue(move.charAt(1));
					int j = 8*r + c; 				   // The piece that is being moved
					if(BB.pTakesB[j] && BB.piecesW[j]) // If it is attacked by a pawn
						valTest += 50;
				}
				if (!Board.whoseMove) // Black evaluation (WHITE TO MOVE)
				{
					valTest = StratEval.globalEval(Board.chessBoard); 
					int r = Character.getNumericValue(move.charAt(0));
					int c = Character.getNumericValue(move.charAt(1));
					int j = 8*r + c; 				   // The piece that is being moved
					if(BB.pTakesW[j] && BB.piecesB[j]) // If it is attacked by a pawn
						valTest -= 50;
				}
				rateTemp.add(valTest);
				Board.undoMove(list.substring(i, i + 5));
			} catch (Exception e) {}
		}
		if (length/5 < breadth) // Else it will try to evaluate moves that don't exist
			breadth = length/5;
		
		for (int j = 0; j < breadth; j++) {
			Object rate = 0;
			for (int k = 0; k < length; k += 5)
			{
				try {
					rate = rateTemp.get(k / 5);
				} catch (Exception e) {}
				
				if(Board.whoseMove)				// Black tries for lowest score
				{
				if ((int) rate < maxRateB) {
					maxRateB = (int) rate;
					try {
					temp = list.substring(k, k + 5);
					} catch (Exception e) {}
					list = list.replaceAll(temp, "");
					rateTemp.remove(rate);
					}
				}
				
				else {								// White tries for highest score
					if (((int)rate > maxRateW)) {
						maxRateW = (int)rate;
						try {
							temp = list.substring(k, k + 5);
							} catch (Exception e) {}
						list = list.replaceAll(temp, "");
						rateTemp.remove(rate);
					}
				}
			}
			candMoves += temp;
			list = list.replaceAll(temp, "");
			maxRateW = -1000000000;
			maxRateB =  1000000000;
		}	
		System.out.println("CANDIDATE MOVES:  "+candMoves);
		return candMoves;
	}
}
