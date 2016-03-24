/*
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

// ############################################################################################################ //

/*
import java.util.*;

public class AI 
{		
		static String currentPath = "";
		static String bestPath = "";

		public static void reset() // Reset each turn
		{
			currentPath = "";
			bestPath = "";
		}
	
	public static int think(int depth, int breadth)
	{
					// FOR TESTING //
		
		System.out.println("\n\n#################");
		System.out.println("CURRENT:  " + currentPath);
		System.out.println("BEST:     "    + bestPath);
		System.out.println("##################\n\n");

		// -------------------------------------------------------------------------------- //

		String candMoves = listMoves(depth, breadth);

	/*				Alpha-Beta Algorithm:
	 * Get list of best (# = breadth) moves in order 
	 * Make one move and evaluate by calling alphaBeta recursively 
	 * Alpha (white) is trying to maximize, while Beta (black) is trying to minimize 
	 * Assuming black is CPU, select the lowest score of the end positions 
	 * **Figure out how to prune 
	 * ^LOOK THIS UP^
	 */
			/*
		
// If it's a leaf node or calculation
	if (depth == 0 || breadth <= 1) 
	{
		int endVal = stratEval.globalEval(board8x8.chessBoard);

		System.out.println("\nEndVal:"+endVal+"\nCURRENT PATH:  "+currentPath+"\n");
		System.out.println("\n                   BEST PATH:    "+bestPath+"\n");
		System.out.println("");
	
		return endVal;
	}
	
	int valTemp = 0;
	int value = 0;
	if(!board8x8.whoseMove){value = -1000000000;}
	else                   {value =  1000000000;}
	if(!"".equals(candMoves))
	{
		for(int i=0; i<candMoves.length(); i+=5) // Make each move, going down each path
		{
			board8x8.makeMove(candMoves.substring(i, i+5)); System.out.println("MAKE:"+candMoves.substring(i, i+5));
			currentPath += candMoves.substring(i, i+5);
			valTemp = think(depth-1,breadth-1);
			board8x8.undoMove(candMoves.substring(i, i+5)); System.out.println("UNDO:"+candMoves.substring(i, i+5));
		if(!board8x8.whoseMove && valTemp > value)
			value = valTemp; 
		if(board8x8.whoseMove && valTemp < value)
			{value = valTemp; bestPath = currentPath;}
		currentPath = currentPath.replaceAll(candMoves.substring(i, i+5), ""); // Update currentPath
		}
	}
	// Once all candidate moves are analyzed
	
	
	
	
		 /*if (board8x8.whoseMove && value > val.alpha) 
		 {val.alpha = value; return value;}
	else if (!board8x8.whoseMove && value < val.beta ) 
		 {val.beta  = value; val.bestPath = val.currentPath; return value;}
	else */
	
//	return value;
//	}

			//alphaBeta-specific pruning :
			
			/*
			if (val.alpha <= val.beta && !board8x8.whoseMove) {
					val.alpha = val.beta;
					val.currentPath = val.alphaval.beta(depth+1, breadth+1, val.beta, val.alpha);
				}
			if (val.beta>val.alpha && board8x8.whoseMove) 
				{
				val.beta = val.alpha;
				val.currentPath = val.alphaval.beta(depth+1, breadth+1);
				}
			*/
			

	
	
	
	// ---------------------------------------------------------------------------------------------- //
	
/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
public static String listMoves(int depth, int breadth)
	{
		
		/*					Sorting:
		 * Each move is rated and sorted from best to worst
		 * The top moves (# = breadth) are considered the candMoves
		 */
/*		
		String list = board8x8.possibleMoves();
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
				board8x8.makeMove(move);
		
				// ------------------ // Custom Evaluation // ------------------ //
				int valTest = stratEval.globalEval(board8x8.chessBoard);
				
				if (board8x8.whoseMove) // White evaluation (BLACK TO MOVE)
				{
					valTest = stratEval.globalEval(board8x8.chessBoard); 
					int r = Character.getNumericValue(move.charAt(0));
					int c = Character.getNumericValue(move.charAt(1));
					int j = 8*r + c; // The piece that is being moved
						if(bb.pTakesB[j] && bb.piecesW[j]) // If it is attacked by a pawn
							valTest += 200;
				}
				
				
				if (!board8x8.whoseMove) // Black evaluation (WHITE TO MOVE)
				{
					valTest = stratEval.globalEval(board8x8.chessBoard); 
					int r = Character.getNumericValue(move.charAt(0));
					int c = Character.getNumericValue(move.charAt(1));
					int j = 8*r + c; // The piece that is being moved
					{
						if(bb.pTakesW[j] && bb.piecesB[j]) // If it is attacked by a pawn
							valTest -= 200;
					}
				}
				
				rateTemp.add(valTest);
				board8x8.undoMove(list.substring(i, i + 5));
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
				
				if(board8x8.whoseMove)				// Black tries for lowest score
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
*/






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

// ############################################################################################################ //


import java.util.*;

public class AI 
{		
		static String currentPath = "";
		static String bestPath = "";

		public static void reset() // Reset each turn
		{
			currentPath = "";
			bestPath = "";
		}

	
	
	public static int think(int depth, int breadth)
	{
		// FOR TESTING //
		
		System.out.println("\n\n#################");
//		System.out.println("val.alpha:      " + val.alpha);
//		System.out.println("val.beta:       "  + val.beta);
		System.out.println("CURRENT:  " + currentPath);
		System.out.println("BEST:     "    + bestPath);
		System.out.println("##################\n\n");

		// -------------------------------------------------------------------------------- //

		String candMoves = listMoves(depth, breadth);

	/*				Alpha-Beta Algorithm:
	 * Get list of best (# = breadth) moves in order 
	 * Make one move and evaluate by calling alphaBeta recursively 
	 * Alpha (white) is trying to maximize, while Beta (black) is trying to minimize 
	 * Assuming black is CPU, select the lowest score of the end positions 
	 * **Figure out how to prune 
	 * ^LOOK THIS UP^
	 */
			
		
// If it's a leaf node or calculation
	if (depth == 0 || breadth <= 1) 
	{
		int endVal = StratEval.globalEval(Board.chessBoard);

//		if("".equals(val.bestPath))		// To ensure there is always an answer
//			val.bestPath = val.currentPath;          // COMMENT OUT?

		System.out.println("\nEndVal:"+endVal+"\nCURRENT PATH:  "+currentPath+"\n");
//		System.out.println("\nBestVal:"+val.beta+"\nBEST PATH:    "+val.bestPath+"\n");
		System.out.println("");
	
		return endVal;
	}
	
	int valTemp = 0;
	int value = 0;
	if(!Board.whoseMove){value = -1000000000;}
	else                   {value =  1000000000;}
	if(!"".equals(candMoves))
	{
		for(int i=0; i<candMoves.length(); i+=5) // Make each move, going down each path
		{
			Board.makeMove(candMoves.substring(i, i+5)); System.out.println("MAKE:"+candMoves.substring(i, i+5));
			currentPath += candMoves.substring(i, i+5);
			valTemp = think(depth-1,breadth-1);
			Board.undoMove(candMoves.substring(i, i+5)); System.out.println("UNDO:"+candMoves.substring(i, i+5));
		if(!Board.whoseMove && valTemp > value)
			value = valTemp; 
		if(Board.whoseMove && valTemp < value)
			{value = valTemp; bestPath = currentPath;}
		currentPath = currentPath.replaceAll(candMoves.substring(i, i+5), ""); // Update currentPath
		}
	}
	// Once all candidate moves are analyzed
	
	
	
	
		 /*if (board8x8.whoseMove && value > val.alpha) 
		 {val.alpha = value; return value;}
	else if (!board8x8.whoseMove && value < val.beta ) 
		 {val.beta  = value; val.bestPath = val.currentPath; return value;}
	else */
	
	return value;
	}

			//alphaBeta-specific pruning :
			
			/*
			if (val.alpha <= val.beta && !board8x8.whoseMove) {
					val.alpha = val.beta;
					val.currentPath = val.alphaval.beta(depth+1, breadth+1, val.beta, val.alpha);
				}
			if (val.beta>val.alpha && board8x8.whoseMove) 
				{
				val.beta = val.alpha;
				val.currentPath = val.alphaval.beta(depth+1, breadth+1);
				}
			*/
			

	
	
	
	// ---------------------------------------------------------------------------------------------- //
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
public static String listMoves(int depth, int breadth)
	{
		
		/*					Sorting:
		 * Each move is rated and sorted from best to worst
		 * The top moves (# = breadth) are considered the candMoves
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
				Board.makeMove(list.substring(i, i + 5));
				int valTest = StratEval.globalEval(Board.chessBoard)*depth; // Captures soon are rewarded
				
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
