//import java.awt.*;
import javax.swing.*;

public class main1 {
	public static void main(String[] args) {
		JFrame f = new JFrame("Chess");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI ui = new GUI();
		f.add(ui);
		f.setSize(64*9, 64*9);
		f.setVisible(true);
	}
}