/**
 * Creates a frame with specified dimensions
 * 
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;


public class FinishGameFrame extends JFrame {

	/**
	 * Creates a frame with specified dimensions, makes it visible and puts at 
	 * the center of the screen
	 */
	public FinishGameFrame() {
		super("Game Over");
		setLayout(new GridBagLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = new Dimension(500, 300);
		setSize(dimension);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
