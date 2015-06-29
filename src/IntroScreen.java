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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class IntroScreen {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public IntroScreen() {
		initialize();
	}

	/**
	 * Initializes the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Stocks and Shares");
		frame.setResizable(false);
		frame.setSize(450, 300);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(153, 0, 51));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		JLabel lblWelcomeTo = new JLabel("Welcome to");
		lblWelcomeTo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblWelcomeTo.setForeground(Color.YELLOW);
		lblWelcomeTo.setBounds(0, 20, 450, 16);
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblWelcomeTo);

		JLabel lblStocksAndShares = new JLabel("Stocks and Shares");
		lblStocksAndShares.setForeground(Color.YELLOW);
		lblStocksAndShares.setHorizontalAlignment(SwingConstants.CENTER);
		lblStocksAndShares.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 42));
		lblStocksAndShares.setBounds(0, 32, 434, 136);
		frame.getContentPane().add(lblStocksAndShares);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NumberOfPlayers();
				frame.setVisible(false);
			}
		});
		btnNewGame.setBounds(39, 152, 154, 78);
		frame.getContentPane().add(btnNewGame);

		JButton btnRules = new JButton("Rules");
		btnRules.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"1.  There are 4 types of stock commodity which all have 28 shares.\n2."
										+ "  All commodities have a starting price of 10 pounds which can go up down on the stock market.\n3. "
										+ " The game consists of 12 rounds.\n4. "
										+ " All players start with 80 pounds and no shares.\n5. "
										+ " At the beginning of each round each player is given a card, which will remain face down until each player has bought, sold or decided to skip these phases.\n6. "
										+ " When these phases are complete the players turn over the card they received which will determine the stock market prices for the next round.\n7. "
										+ " Each commodity has a max of 20 pounds and a min price of 0 pounds.\n8. "
										+ " If a player has no money and no shares they are eliminated from the game.\n9. "
										+ " The player with the most money after shares have been sold in round 12 wins the game.");
			}
		});
		btnRules.setBounds(252, 152, 154, 78);
		frame.getContentPane().add(btnRules);
	}
}
