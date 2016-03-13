import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class checkMate {

    public static void main(String[] args) {
    FrameTest1 test_frame = new FrameTest1();
    test_frame.setContentString("CHECKMATE");
    }
}

class FrameTest1 extends JFrame {
    private static final long serialVersionUID = 1L;

    Painting painting = new Painting();

    public FrameTest1() {
        JFrame gui = new JFrame();
        gui.setTitle("Test Title");
        gui.setSize(400, 400);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = gui.getContentPane();
        pane.setLayout(new GridLayout(1, 1));

        pane.add(painting);
        gui.setVisible(true);

    }

    public void setContentString(String value) {
        painting.test_string = value;
    }
}

class Painting extends JPanel {
	
	Image piecesImg = new ImageIcon("ChessPieces.png").getImage();
	int squareSize = 64;
	
    private static final long serialVersionUID = 1L;
    String test_string;

    public Painting() {
        setBackground(Color.WHITE);
        this.test_string = "TEMP STRING FROM PANEL";
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
		g.fillRect(0, 0, squareSize*8, squareSize*8);
		g.setColor(new Color(150,50,30));	
        g.drawString(test_string, 9*squareSize/4, 3*squareSize);
    }
}

