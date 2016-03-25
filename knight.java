
public class Knight {

	public static boolean[] nMovesW= new boolean[64];
	public static boolean[] nMovesB= new boolean[64];
	
	
	public static String movesW(int i)
	{	
		String move = "", oldPiece;
		String newPos = "";
		int r=i/8, c=i%8;

		for (int j=1; j>=-1; j-=2)
		{
			for (int k=1; k>=-1; k-=2)
			{
			if (j!=0 || k!=0) 
				{
				try {
					if(((r+2*j)*8+(c+k)) != 64)
						nMovesW[(r+2*j)*8+(c+k)] = true;
					newPos = Board.chessBoard[(r+2*j)][(c+k)];
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						Board.makeMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						if (King.kingSafeW())
							move = move+r+c+((r+2*j))+(c+k)+oldPiece;
						Board.undoMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						}
					newPos = Board.chessBoard[(r+j)][(c+2*k)];
					if(((r+j)*8+(c+2*k) != 64))
						nMovesW[(r+j)*8+(c+2*k)] = true;
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						Board.makeMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						if (King.kingSafeW())
							move = move+r+c+((r+j))+(c+2*k)+oldPiece;
						Board.undoMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						}
					} catch (Exception e) {};
				}
			}
		}
//		BB.nMovesW=nMovesW;
		return move;
	}
	
	public static String movesB(int i)
	{	
		String move = "", oldPiece;
		String newPos = "";
		int r=i/8, c=i%8;

		for (int j=1; j>=-1; j-=2)
		{
			for (int k=1; k>=-1; k-=2)
			{
			if (j!=0 || k!=0) 
				{
				try {
					newPos = Board.chessBoard[(r+2*j)][(c+k)];
						nMovesB[(r+2*j)*8+(c+k)] = true;
					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						Board.makeMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						if (King.kingSafeB())
							move = move+r+c+((r+2*j))+(c+k)+oldPiece;
						Board.undoMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						}
					newPos = Board.chessBoard[(r+j)][(c+2*k)];
						nMovesB[(r+j)*8+(c+2*k)] = true;
					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						Board.makeMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						if (King.kingSafeB())
							move = move+r+c+((r+j))+(c+2*k)+oldPiece;
						Board.undoMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						}
					} catch (Exception e) {};
				}
			}
		}
//		BB.nMovesB=nMovesB;
		return move;
	}
	public static void controlW(int i)
	{
		int r=i/8, c=i%8;

		for (int j=1; j>=-1; j-=2)
		{
			for (int k=1; k>=-1; k-=2)
			{
			if (j!=0 || k!=0) 
				{
				try {
						BB.nMovesW[(r+2*j)*8+(c+k)] = true;
						BB.nMovesW[(r+j)*8+(c+2*k)] = true;
					} catch (Exception e) {};
				}
			}
		}
	}
	public static void controlB(int i)
	{
		int r=i/8, c=i%8;

		for (int j=1; j>=-1; j-=2)
		{
			for (int k=1; k>=-1; k-=2)
			{
			if (j!=0 || k!=0) 
				{
				try {
						BB.nMovesB[(r+2*j)*8+(c+k)] = true;
						BB.nMovesB[(r+j)*8+(c+2*k)] = true;
					} catch (Exception e) {};
				}
			}
		}
	}
}