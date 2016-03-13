/* 
 * Space: encourage cramping
 * Structure: encourage pawn duo, discourage backward pawn & isolated pawn
 * Implement basic plans (minority attack, creating OPP)
 * Center: factor in moves like c5, judge tension
 */

public class pawnStructure {
	

	public static boolean isBackward(int i) { //put in work
		int r = i / 8;
		int c = i % 8;
		boolean flag = true;

		try{
			if ("P".equals(board8x8.chessBoard[r][c]) && !"p".equals(board8x8.chessBoard[r-1][c]))
				for (int j = 7; j >= r; j--)
			if (!"P".equals(board8x8.chessBoard[j][c + 1]) && !"P".equals(board8x8.chessBoard[j][c - 1]))
				flag = false;
			
	if ("p".equals(board8x8.chessBoard[r][c]) && !"P".equals(board8x8.chessBoard[r+1][c]))
		for (int k = 0; k <= r; k++) 
	if(!"p".equals(board8x8.chessBoard[k][c + 1]) && !"p".equals(board8x8.chessBoard[k][c - 1]))
				flag=false;
	
		}catch(Exception e){/*	Outside pawns will never be backward	*/}
		return flag;
	}
	

	public static int spaceW(String[][] pos) {
		int spaceEvalW = 0;
		for (int i = 0; i < 64; i++) {
			int r = i / 8;
			int c = i % 8;
			if (bb.pawnsW[i])
				try {
					if (bb.pawnsW[i+7] || bb.pawnsW[i+9] && r < 6) // protected
						spaceEvalW += 3 * (-(r - 7));
					if (bb.pawnsW[i+1] || bb.pawnsW[i-1] && r < 6) // pawn duo
						spaceEvalW += 6 * (-(r - 7));
				} catch (Exception e) {}
		}
		return spaceEvalW;
	}

	public static int spaceB(String[][] pos) {
		int spaceEvalB = 0;
		for (int i = 0; i < 64; i++) {
			int r = i / 8;
			int c = i % 8;
			if ("p".equals(pos[r][c]))
				try {
					if ((bb.pawnsB[i-7] || bb.pawnsB[i-9]) && r > 2) // protected
						spaceEvalB += 3 * r;
					if ((bb.pawnsB[i+1] || bb.pawnsB[i-1]) && r > 2) // pawn duo
						spaceEvalB += 6 * r;
				} catch (Exception e) {}
		}
		return spaceEvalB;
	}

	public static int pawnCenter(String[][] pos) {
		int centerEval  = 0;
		int centerEvalW = 0;
		int centerEvalB = 0;

		int[] center = { 27, 28, 35, 36 };
		for (int i = 0; i < 4; i++) {
			if (bb.pawnsW[center[i]])
				centerEvalW += 200;
			if (bb.pawnsB[center[i]])
				centerEvalB += 200;
			if(bb.controlW()[center[i]])
				centerEvalW += 50;
			if(bb.controlB()[center[i]])
				centerEvalB += 50;

			
			
		centerEval = (centerEvalW - centerEvalB);
		}
		return centerEval;
	}
	
//	public static int centerControl()
	{
//		long controlW = bb.controlW()&(bb.center);
//		long controlB = bb.controlB()&(bb.center);
		
//		return ((int)(Long.bitCount(controlW)-Long.bitCount(controlB))*100);
	}



}
