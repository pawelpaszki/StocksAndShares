/**    
 * 
 * This class creates a window, in which names of the players are assigned 
 * and passed to StocksAndShares class
 * 
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SubmitNames {

	private JFrame frame;
	private int playerNumber;
	private int numberOfPlayers;
	private ArrayList<String> playersNames;
	private JTextField playerName;
	private JButton submitName;
	private JLabel info;

	/**
	 * Create the application.
	 * @param numberOfPlayers is passed in and assigned to field with the same name
	 */
	public SubmitNames(int numberOfPlayers) {
		playerNumber = 1;
		playersNames = new ArrayList<String>();
		this.numberOfPlayers = numberOfPlayers;
		initialize();
	}

	/**
	 * Initialize the contents of the frame:
	 * frame is not resizable, is centered, with  purple background and 
	 * yellow font color. 
	 */
	private void initialize() {
		frame = new JFrame("Stocks and Shares");
		frame.setResizable(false);
		frame.setSize(450, 200);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(153, 0, 51));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		playerName = new JTextField(20);
		submitName = new JButton("Submit");

		playerName.setVisible(true);
		submitName.setVisible(true);
		info = new JLabel("Please Enter the Name of Player " + playerNumber);
		info.setForeground(Color.YELLOW);
		info.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 24));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(6, 6, 438, 40);
		playerName.setBounds(120, 60, 200, 25);
		playerName.setFont(new Font("Arial", Font.ITALIC, 14));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		submitName.setHorizontalAlignment(SwingConstants.CENTER);
		submitName.setBounds(120, 100, 200, 30);
		submitName.setEnabled(false);
		frame.add(info);
		frame.add(playerName);
		frame.add(submitName);
		// this anonymous class checks the String inside JTextField, whenever its 
		// value is updated (character removed, changed or added). WHen the length
		// of the String is between 3 and 15 characters - submit button becomes enabled
		// otherwise String cannot be passed as a name
		playerName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkName();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkName();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkName();
			}

			public void checkName() {
				String name = playerName.getText();
				if (name.length() > 2 && name.length() < 15) {
					submitName.setEnabled(true);
				} else {
					submitName.setEnabled(false);
				}
			}
		});
		
		// submit button ActionListener - takes a String (if between 3-5 in length)
		// and adds it to ArrayList of names, which will be passed as a parameter
		// when the ArrayList's size will equal the number of the Players in the 
		// game
		submitName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerNumber++;
				String name = playerName.getText().toString();
				playersNames.add(name);
				playerName.setText("");
				if (playersNames.size() == numberOfPlayers) {
					frame.setVisible(false);
					new StocksAndShares(playersNames);
				}
				info.setText("Please Enter the Name of Player " + playerNumber);
			}

		});

	}
}
