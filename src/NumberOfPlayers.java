/**
 * 
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NumberOfPlayers {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public NumberOfPlayers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Stocks and Shares");
		frame.getContentPane().setBackground(new Color(153, 0, 51));
		frame.setSize(450, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Pick the Number of Players");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBackground(new Color(204, 51, 0));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 438, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnThreePlayers = new JButton("Three Players");
		btnThreePlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int numberOfPlayers = 3;
				frame.setVisible(false);
				new SubmitNames(numberOfPlayers);
			}
		});
		btnThreePlayers.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		btnThreePlayers.setBounds(15, 70, 200, 79);
		frame.getContentPane().add(btnThreePlayers);
		
		JButton btnFourPlayers = new JButton("Four Players");
		btnFourPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int numberOfPlayers = 4;
				frame.setVisible(false);
				new SubmitNames(numberOfPlayers);
			}
		});
		btnFourPlayers.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		btnFourPlayers.setBounds(225, 70, 200, 79);
		frame.getContentPane().add(btnFourPlayers);
		
		JButton btnFivePlayers = new JButton("Five Players");
		btnFivePlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				int numberOfPlayers = 5;
				frame.setVisible(false);
				new SubmitNames(numberOfPlayers);
			}
		});
		btnFivePlayers.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		btnFivePlayers.setBounds(15, 160, 200, 79);
		frame.getContentPane().add(btnFivePlayers);
		
		JButton btnSixPlayers = new JButton("Six Players");
		btnSixPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int numberOfPlayers = 6;
				frame.setVisible(false);
				new SubmitNames(numberOfPlayers);
			}
		});
		btnSixPlayers.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		btnSixPlayers.setBounds(225, 160, 200, 79);
		frame.getContentPane().add(btnSixPlayers);
	}

}  

