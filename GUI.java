/* 			Graphical User Interface
 * Takes care of graphics for board and pieces
 * Makes moves when pieces are dragged if the move is legal
 * Thoughts - implement 2-click moves?? squares would light up on 1st click?
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
@SuppressWarnings("serial")

public class GUI extends JPanel implements MouseListener, MouseMotionListener 
{
	Image piecesImg = new ImageIcon("ChessPieces.png").getImage();
	static int mouseX, mouseY, newMouseX, newMouseY;
	
	static int x = 0, y = 0;
	static int squareSize = 64;
	
		
	public void paintComponent(Graphics g)
	{
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		super.paintComponent(g);
	
// Drawing the chess board
	for(int i=0; i<64; i+=2)
		{
		g.setColor(Color.lightGray);
		g.fillRect((i%8+(i/8)%2)*squareSize, (i/8)*squareSize, squareSize, squareSize);
		g.setColor(new Color(150,50,30));
		g.fillRect(((i+1)%8-((i+1)/8)%2)*squareSize, ((i+1)/8)*squareSize, squareSize, squareSize);
		g.drawString(" ", x, y);
		}
	
// Displaying the pieces
	for (int i=0;i<64;i++)
	{
		int j=-1,k=-1;
		switch (Board.chessBoard[i/8][i%8])
		{
		case "K":	j=0; k=0;
			break;
		case "k":	j=0; k=1;
			break;
		case "Q":	j=1; k=0;
			break;
		case "q":	j=1; k=1;
			break;
		case "R":	j=2; k=0;
			break;
		case "r":	j=2; k=1;
			break;
		case "B":	j=3; k=0;
			break;
		case "b":	j=3; k=1;
			break;
		case "N":	j=4; k=0;
			break;
		case "n":	j=4; k=1;
			break;
		case "P":	j=5; k=0;
			break;
		case "p":	j=5; k=1;
			break;
		}
	if (j!=-1 && k!=-1)
		{
	g.drawImage(piecesImg, (i%8)*squareSize, (i/8)*squareSize, (i%8+1)*squareSize, 
			(i/8+1)*squareSize, j*64, k*64, (j+1)*64, (k+1)*64, this);
		}	
	}
}
	// dragMove is any move that user makes (even illegal ones)
String dragMove="";
	public void mouseMoved(MouseEvent e){}
	public void mousePressed(MouseEvent e){
		if(e.getX()<(8*squareSize)&&e.getY()<(8*squareSize))
		mouseX=e.getX();
		mouseY=e.getY();
		repaint();
	}
	public void mouseReleased(MouseEvent e){
		if(e.getX()<(8*squareSize)&&e.getY()<(8*squareSize))
		newMouseX=e.getX();
		newMouseY=e.getY();
		repaint();
		if (e.getButton()==MouseEvent.BUTTON1)
			dragMove=""+mouseY/squareSize+mouseX/squareSize+newMouseY/squareSize+newMouseX/squareSize;
		
		// dragMove must appear in possibleMoves for it to be a legal move
		String userPoss=Board.possibleMoves();
		if (userPoss.replaceAll(dragMove, "").length()<userPoss.length())
		{
			int i = userPoss.indexOf(dragMove)+4;
			dragMove+=userPoss.charAt(i);
			Board.recordMove(dragMove);
									
			long startTime = System.currentTimeMillis();
			
			AI.reset();

			AI.think(3, 3);
			Board.recordMove(AI.bestPath.substring(0,5));
			
			System.out.println("\n"+dragMove);
			
			BB.refreshMoves();
			printCntl();
				
			long finishTime = System.currentTimeMillis();

			System.out.println("\nThat took: "+(finishTime-startTime)+ " ms");

		}
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mouseDragged(MouseEvent e){
		if(e.getX()<(8*squareSize)&&e.getY()<(8*squareSize))
		newMouseX=e.getX();
		newMouseY=e.getY();
		repaint();
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	
public static void printCntl() // FOR TESTING PURPOSES
{	
	BB.refreshMoves();
	System.out.println("");
	for (int j=0; j<64; j++)
		{
		if(BB.controlW()[j] && BB.controlB()[j])
			System.out.print("# ");
		
   else if(BB.controlW()[j]==true)
			System.out.print("X ");
		
   else	if(BB.controlB()[j]==true)
			System.out.print("O ");
		
   else		System.out.print(". ");	
		
		if(j%8==7)
				System.out.println("|"+(8-j/8));
		}
	System.out.println("a b c d e f g h \n");
	}
	public static void printBoard() // FOR TESTING PURPOSES
	{
	// Print the board on the console
		for (int j=0; j<64; j++) 
		{
			System.out.print(" "+Board.chessBoard[j/8][j%8]);
			if(j%8==7)
				System.out.println("  |"+(8-j/8));
		}
	System.out.println(" ________________\n a b c d e f g h ");

	}
	
}

