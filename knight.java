
public class knight {

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
					newPos = board8x8.chessBoard[(r+2*j)][(c+k)];
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						board8x8.makeMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						if (king.kingSafeW())
							move = move+r+c+((r+2*j))+(c+k)+oldPiece;
						board8x8.undoMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						}
					newPos = board8x8.chessBoard[(r+j)][(c+2*k)];
					if(((r+j)*8+(c+2*k) != 64))
						nMovesW[(r+j)*8+(c+2*k)] = true;
					if (Character.isLowerCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						board8x8.makeMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						if (king.kingSafeW())
							move = move+r+c+((r+j))+(c+2*k)+oldPiece;
						board8x8.undoMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						}
					} catch (Exception e) {};
				}
			}
		}
		bb.nMovesW=nMovesW;
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
					newPos = board8x8.chessBoard[(r+2*j)][(c+k)];
						nMovesB[(r+2*j)*8+(c+k)] = true;
					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						board8x8.makeMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						if (king.kingSafeB())
							move = move+r+c+((r+2*j))+(c+k)+oldPiece;
						board8x8.undoMove(""+r+c+((r+2*j))+(c+k)+oldPiece);
						}
					newPos = board8x8.chessBoard[(r+j)][(c+2*k)];
						nMovesB[(r+j)*8+(c+2*k)] = true;
					if (Character.isUpperCase(newPos.charAt(0)) || " ".equals(newPos))
						{
						oldPiece=newPos;
						board8x8.makeMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						if (king.kingSafeB())
							move = move+r+c+((r+j))+(c+2*k)+oldPiece;
						board8x8.undoMove(""+r+c+((r+j))+(c+2*k)+oldPiece);
						}
					} catch (Exception e) {};
				}
			}
		}
		bb.nMovesB=nMovesB;
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
						nMovesW[(r+2*j)*8+(c+k)] = true;
						nMovesW[(r+j)*8+(c+2*k)] = true;
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
						nMovesB[(r+2*j)*8+(c+k)] = true;
						nMovesB[(r+j)*8+(c+2*k)] = true;
					} catch (Exception e) {};
				}
			}
		}
	}
}