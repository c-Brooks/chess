
/*
 *								TODO:
 * 		Prog Deepening - assign # depending on where in candMoves the move is
 * search for the inverse of the # (use candMoves.length()-#)
 * 
 * 		If rating jumps by >100, search one more move - don't want to 
 * capture on the last evaluation
 */


// ##################################################################################### //


import java.util.*;



/**	    AI - This enables the engine to look ahead
 * 
 * It contains a branching algorithm known as Alpha-Beta.
 * It calls itself recursively, making a desicion tree
 * 		until a leaf node is reached.
 **/

public class AI 
{		
		static String currentPath = "";
		static String bestPath = "";
		static int alpha = -1000000000;
		static int beta  =  1000000000;
		
		/** Reset each turn **/
		public static void reset()
		{
			currentPath = "";
			bestPath = "";
			alpha = -1000000000;
			beta  =  1000000000;
		}
	
		/**				Alpha-Beta Algorithm:
		 * Get list of best (# = breadth) moves in order.  
		 * Make moves and evaluate by calling think recursively until depth = 0.  
		 * Alpha (white) is trying to maximize, while Beta (black) is trying to minimize.   
		 * Assuming black is CPU, select the lowest score of the end positions.  
		 **/
		
	public static int think(int depth, int breadth)
	{
					// FOR TESTING //
		
		System.out.println("\n\n#######################");
		System.out.println("# CURRENT:  " + currentPath);
		System.out.println("# BEST:     "    + bestPath);
		System.out.println("#######################\n\n");

// ------------------------------------------------------------------------------------- //
		
		String candMoves = listMoves(breadth);

		if("".equals(candMoves) && !Board.whoseMove) // White is checkmated
			return -1000000000;
		if("".equals(candMoves) && Board.whoseMove)  // Black is checkmated
			return  1000000000;
		
		
		// If it's a leaf node
	if (depth == 0 || breadth <= 1) 
	{
		int endVal = StratEval.globalEval(Board.chessBoard);

		System.out.println("\nEndVal:"+endVal+"\nCURRENT PATH:  " + currentPath + "\n");
		System.out.println("\n                   BEST PATH:    "  + bestPath    + "\n");
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
		
		// Alpha-Beta pruning - IT WORKS NOW
			
		if(Board.whoseMove && valTemp > beta)
			{
			System.out.println("Alpha > Beta"); beta = 1000000000; 
			currentPath = currentPath.replaceAll(candMoves.substring(i, i+5), ""); // Update currentPath
			return valTemp; 
			}
		if(!Board.whoseMove && valTemp < alpha)
			{ 
			System.out.println("Beta < Alpha"); alpha = -1000000000; 
			currentPath = currentPath.replaceAll(candMoves.substring(i, i+5), ""); // Update currentPath
			return valTemp; 
			}
		
		if(!Board.whoseMove && valTemp > value)
			alpha = value = valTemp; 
		if(Board.whoseMove && valTemp < value)
			{ beta = value = valTemp; bestPath = currentPath; }
		
		currentPath = currentPath.replaceAll(candMoves.substring(i, i+5), ""); // Update currentPath
		}
	}
	
	// Once all candidate moves are analyzed
	return value;
	}

// ------------------------------------------------------------------------------------- //
	
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
		LinkedList<Integer> rateTemp = new LinkedList<Integer>();
		
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
						valTest += (BB.valueOf(j)/2);
					if (King.kingSafeB()) // Examine checks
						valTest += 100;
				}
				if (!Board.whoseMove) // Black evaluation (WHITE TO MOVE)
				{
					valTest = StratEval.globalEval(Board.chessBoard); 
					int r = Character.getNumericValue(move.charAt(0));
					int c = Character.getNumericValue(move.charAt(1));
					int j = 8*r + c; 				   // The piece that is being moved
					if(BB.pTakesW[j] && BB.piecesB[j]) // If it is attacked by a pawn
						valTest += (BB.valueOf(j)/2);
					if (King.kingSafeW()) // Examine checks
						valTest -= 100;
				}
				rateTemp.add(valTest);
				Board.undoMove(list.substring(i, i + 5));
			} catch (Exception e) {}
		}
		if (length/5 < breadth) // Else it will try to evaluate moves that don't exist
			{ breadth = length/5; } // depth++ ?
		
		// SORTING //
		
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
	
	
	
	/** -- QuickSort - A faster algorithm to sort candidate moves -- **/
	// TODO: Make it deal with ratings as well as moves
	
	
	LinkedList<Integer> quickSort(LinkedList<Integer> arr, int left, int right) {
	     int index = partition(arr, left, right);
	     if (left < index - 1)
	           quickSort(arr, left, index - 1);
	     if (index < right)
	           quickSort(arr, index, right);
	     return arr;
	}

	int partition(LinkedList<Integer> arr, int left, int right)
	{
	      int i = left, j = right;
	      int tmp;
	      int pivot = (int) arr.get((left + right) / 2);
	     
	      while (i <= j) {
	            while ((int)arr.get(i) < pivot)
	                  i++;
	            while ((int)arr.get(j) > pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = (int)arr.get(i);
	                  arr.set(i, arr.get(j));
	                  arr.set(j, tmp);
	                  i++;
	                  j--;
	            }
	      };   
	      return i;
	}
}
