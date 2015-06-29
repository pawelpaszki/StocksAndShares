/**      
 * 
 * This class controls the game, after all information needed to start 
 * it is gathered. It contains number of Swing elements and instance variables
 * of all classes needed to "play the game". Java docs are ommited, because no 
 * other class accesses methods from this class and therefore these methods are
 * private. short description is provided for all methods written in this class
 * 
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StocksAndShares {

	private ArrayList<Player> players;
	private ArrayList<Player> winners;
	private Deck deck;
	private Stock stock;
	private int numberOfRounds;
	private int roundNumber;
	private int qtyPickedToBuy;
	private int sharePickedToBuy;
	private int qtyPickedToSell;
	private int sharePickedToSell;
	private int turnNumber;
	private HashSet<Integer> validQuantities;
	private ArrayList<Integer> playersTurn;
	private int numberOfPlayers;

	// components:
	private JLabel player1Info;
	private JPanel player1;
	private JPanel centerTop;
	private JPanel shares;
	private JLabel stockAvailable;
	private JPanel roundIndicator;
	private JPanel player2;
	private JLabel player2Info;
	private JPanel player3;
	private JLabel player3Info;
	private JPanel dealtCards;
	private JPanel player4;
	private JLabel player4Info;
	private JPanel player5;
	private JLabel player5Info;
	private JPanel console;
	private JPanel pickCard;
	private JPanel gameControls;
	private JPanel player6;
	private JLabel player6Info;
	private JButton buyShares;
	private JButton disabledBuy;
	private JButton sellShares;
	private JButton disabledSell;
	private JButton finishTurn;
	private JButton finishRound;
	private JButton finishGame;
	private JButton player1Card;
	private JButton player2Card;
	private JButton player3Card;
	private JButton player4Card;
	private JButton player5Card;
	private JButton player6Card;
	private ArrayList<JLabel> playersInfo;
	private ArrayList<JLabel> names;
	private ArrayList<JButton> playersCards;
	private ArrayList<JPanel> playersPanels;
	private JLabel roundNumberLabel;
	private JButton cardPicker;
	private JFrame finishGameFrame;
	private JLabel congratulations;
	private JLabel winnersLabel;
	private JPanel congrats;
	private JPanel winnersPanel;
	private JFrame gameFrame;

	private JComboBox<String> shareBuyComboBox;
	private JTextField shareQtyToBuy;
	private DefaultComboBoxModel<String> buyShareModel;
	private JComboBox<String> shareSellComboBox;
	private JTextField shareQtyToSell;
	private DefaultComboBoxModel<String> sellShareModel;
	private JPanel buying;
	private JPanel selling;
	private JPanel done;
	private JPanel completeRound;

	/**
	 * Constructor for the fields in StocksAndShares Class.
	 * @param playersNames ArrayList is used to generate ArrayList
	 * of Players with the names sent in the parameter. Number of 
	 * rounds in the game is set to 12.  
	 */
	public StocksAndShares(ArrayList<String> playersNames) {
		numberOfPlayers = playersNames.size();
		players = new ArrayList<Player>();
		names = new ArrayList<JLabel>();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player(playersNames.get(i).toString()));
			names.add(new JLabel());
			names.get(i).setForeground(Color.YELLOW);
		}

		winners = new ArrayList<Player>();
		deck = new Deck();
		stock = new Stock();
		numberOfRounds = 12;
		roundNumber = 1;
		setPlayersTurn(numberOfPlayers);
		initBoard();

	}

	/*
	 * creates ArrayList, which contains Integers, which will be used
	 * to determine, which Player has his turn
	 */
	
	private void setPlayersTurn(int numberOfPlayers) {
		playersTurn = new ArrayList<Integer>();
		for (int i = 1; i <= numberOfPlayers; i++) {
			playersTurn.add(i);
		}
	}

	/*
	 * New frame is created. This frame is the main frame in the project. 
	 * It displays all info about Players balance, their names and held shares, 
	 * shares available in stock and their prices, round number, game console is 
	 * also displayed. Player can buy shares, if has enough money(when buying more
	 * than 5 shares at a time - only multiples of 5 are allowed as a quantity to buy).
	 */
	private void initBoard() {
		gameFrame = new JFrame("Stocks and Shares");
		gameFrame.setVisible(true);
		gameFrame.setLayout(new GridBagLayout());

		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = new Dimension(1000, 600);
		gameFrame.setSize(dimension);
		gameFrame.setLocationRelativeTo(null);
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;

		playersPanels = new ArrayList<JPanel>();
		playersCards = new ArrayList<JButton>();
		playersInfo = new ArrayList<JLabel>();
		player1 = createSquareJPanel(new Color(153, 0, 51), 200, 200);
		player1.setLayout(new BoxLayout(player1, 1));
		player1.add(Box.createRigidArea(new Dimension(20, 10)));
		player1Info = new JLabel();
		player1Card = new JButton();
		playersCards.add(player1Card);
		playersInfo.add(player1Info);
		player1Info.setText("<html>"
				+ players.get(0).displayPlayersSharesAndBalance() + "</html>");
		player1Info.setForeground(Color.YELLOW);
		names.get(0).setText(players.get(0).getName());
		names.get(0).setFont(new Font("Arial", Font.BOLD, 14));
		names.get(0).setForeground(Color.GREEN);
		player1.add(names.get(0));
		player1.add(player1Info);

		Border lined = BorderFactory.createLineBorder(Color.YELLOW, 1);
		Border innerPlayer1 = BorderFactory.createTitledBorder(lined,
				"Player 1:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border thinOuterBorder = BorderFactory.createEmptyBorder(5, 5, 7, 5);
		player1.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerPlayer1));
		gc.gridx = 0;
		gc.gridy = 0;
		playersPanels.add(player1);
		gameFrame.add(player1, gc);

		centerTop = createSquareJPanel(new Color(153, 0, 51), 600, 200);
		centerTop.setBorder(BorderFactory.createEmptyBorder());
		shares = createSquareJPanel(new Color(153, 0, 51), 394, 194);
		stockAvailable = new JLabel();
		Border sharesIndicator = BorderFactory.
				createTitledBorder(lined,
						"Shares Available:", 2, 2, new Font("Arial", Font.ITALIC, 12),
						Color.YELLOW);
		shares.setBorder(BorderFactory.createCompoundBorder(thinOuterBorder,
				sharesIndicator));
		stockAvailable.setText("<html>" + stock.toString() + "</html>");
		stockAvailable.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		stockAvailable.setForeground(Color.YELLOW);
		shares.add(stockAvailable);
		roundIndicator = createSquareJPanel(new Color(153, 0, 51), 194, 194);
		roundIndicator.setLayout(new FlowLayout(FlowLayout.CENTER));
		Border roundNumIndicator = BorderFactory.createTitledBorder(lined,
				"Round Number:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		roundIndicator.setBorder(BorderFactory.createCompoundBorder(
				thinOuterBorder, roundNumIndicator));
		roundNumberLabel = new JLabel();
		roundNumberLabel.setText(String.valueOf(roundNumber));
		roundNumberLabel.setFont(new Font("Courier New", Font.BOLD, 120));
		roundNumberLabel.setForeground(Color.YELLOW);
		roundIndicator.add(roundNumberLabel);
		gc.gridx = 1;
		centerTop.add(shares);
		centerTop.add(roundIndicator);
		gameFrame.add(centerTop, gc);

		player2 = createSquareJPanel(new Color(153, 0, 51), 200, 200);
		player2.setLayout(new BoxLayout(player2, 1));
		player2.add(Box.createRigidArea(new Dimension(20, 10)));
		player2Info = new JLabel();
		player2Card = new JButton();
		playersCards.add(player2Card);
		playersInfo.add(player2Info);
		player2Info.setText("<html>"
				+ players.get(1).displayPlayersSharesAndBalance() + "</html>");
		player2Info.setForeground(Color.YELLOW);
		names.get(1).setText(players.get(1).getName());
		names.get(1).setFont(new Font("Arial", Font.BOLD, 14));
		player2.add(names.get(1));
		player2.add(player2Info);
		Border innerPlayer2 = BorderFactory.createTitledBorder(lined,
				"Player 2:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		player2.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerPlayer2));
		gc.gridx = 2;
		playersPanels.add(player2);
		gameFrame.add(player2, gc);

		player3 = createSquareJPanel(new Color(153, 0, 51), 200, 200);
		player3.setLayout(new BoxLayout(player3, 1));
		player3.add(Box.createRigidArea(new Dimension(20, 10)));
		player3Info = new JLabel();
		player3Card = new JButton();
		playersCards.add(player3Card);
		playersInfo.add(player3Info);
		player3Info.setText("<html>"
				+ players.get(2).displayPlayersSharesAndBalance() + "</html>");
		player3Info.setForeground(Color.YELLOW);
		names.get(2).setText(players.get(2).getName());
		names.get(2).setFont(new Font("Arial", Font.BOLD, 14));
		player3.add(names.get(2));
		player3.add(player3Info);
		Border innerPlayer3 = BorderFactory.createTitledBorder(lined,
				"Player 3:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		player3.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerPlayer3));
		gc.gridx = 0;
		gc.gridy = 1;
		playersPanels.add(player3);
		gameFrame.add(player3, gc);

		dealtCards = createSquareJPanel(new Color(153, 0, 51), 600, 200);
		Border innerDealtCards = BorderFactory
				.createTitledBorder(lined,
						"Dealt Cards:", 2, 2, new Font("Arial", Font.ITALIC, 12),
						Color.YELLOW);
		dealtCards.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerDealtCards));
		gc.gridx = 1;
		gameFrame.add(dealtCards, gc);

		player4 = createSquareJPanel(new Color(153, 0, 51), 200, 200);
		player4.setLayout(new BoxLayout(player4, 1));
		player4.add(Box.createRigidArea(new Dimension(20, 10)));
		if (players.size() >= 4) {
			player4Info = new JLabel();
			player4Card = new JButton();
			playersCards.add(player4Card);
			playersInfo.add(player4Info);
			player4Info.setText("<html>"
					+ players.get(3).displayPlayersSharesAndBalance()
					+ "</html>");
			player4Info.setForeground(Color.YELLOW);
			names.get(3).setText(players.get(3).getName());
			names.get(3).setFont(new Font("Arial", Font.BOLD, 14));
			player4.add(names.get(3));
			player4.add(player4Info);
			playersPanels.add(player4);
			gameFrame.add(player4, gc);
		} else {
			gameFrame.add(player4, gc);
		}
		Border innerPlayer4 = BorderFactory.createTitledBorder(lined,
				"Player 4:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		player4.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerPlayer4));
		gc.gridx = 2;
		gc.gridy = 1;
		gameFrame.add(player4, gc);

		player5 = createSquareJPanel(new Color(153, 0, 51), 200, 200);
		player5.setLayout(new BoxLayout(player5, 1));
		player5.add(Box.createRigidArea(new Dimension(20, 10)));
		if (players.size() >= 5) {
			player5Info = new JLabel();
			player5Card = new JButton();
			playersCards.add(player5Card);
			playersInfo.add(player5Info);
			player5Info.setText("<html>"
					+ players.get(4).displayPlayersSharesAndBalance()
					+ "</html>");
			player5Info.setForeground(Color.YELLOW);
			names.get(4).setText(players.get(4).getName());
			names.get(4).setFont(new Font("Arial", Font.BOLD, 14));
			player5.add(names.get(4));
			player5.add(player5Info);
			playersPanels.add(player5);
			gameFrame.add(player5, gc);
		} else {
			gameFrame.add(player5, gc);
		}
		Border innerPlayer5 = BorderFactory.createTitledBorder(lined,
				"Player 5:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);

		player5.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerPlayer5));
		gc.gridx = 0;
		gc.gridy = 2;
		gameFrame.add(player5, gc);

		console = createSquareJPanel(new Color(153, 0, 51), 600, 200);
		gameControls = createSquareJPanel(new Color(153, 0, 51), 394, 194);
		buying = createSquareJPanel(new Color(153, 0, 51), 370, 35);
		selling = createSquareJPanel(new Color(153, 0, 51), 370, 35);
		finishTurn = new JButton("done");
		Dimension buttonDim = new Dimension(173, 25);
		finishTurn.setPreferredSize(buttonDim);
		finishTurn.setEnabled(false);
		finishRound = new JButton("finish round");
		finishRound.setPreferredSize(buttonDim);
		finishRound.setVisible(false);
		done = createSquareJPanel(new Color(153, 0, 51), 370, 35);
		completeRound = createSquareJPanel(new Color(153, 0, 51), 370, 35);
		pickCard = createSquareJPanel(new Color(153, 0, 51), 194, 194);
		Border gameControllers = BorderFactory
				.createTitledBorder(lined,
						"Game Controllers:", 2, 2, new Font("Arial", Font.ITALIC, 12),
						Color.YELLOW);
		Border playersCard = BorderFactory.createTitledBorder(lined,
				"Pick a Card:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		gameControls.setBorder(BorderFactory.createCompoundBorder(
				thinOuterBorder, gameControllers));
		pickCard.setBorder(BorderFactory.createCompoundBorder(thinOuterBorder,
				playersCard));

		cardPicker = new JButton();
		cardPicker.setText("<html> pick<br>card</html>");

		Dimension cardPickerDim = new Dimension(60, 100);
		cardPicker.setPreferredSize(cardPickerDim);
		pickCard.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 30;
		gbc.ipady = 20;
		pickCard.add(cardPicker, gbc);
		gc.gridx = 1;

		buyShareModel = new DefaultComboBoxModel<String>();
		for (Share share : stock.getStock()) {
			if (players.get(playersTurn.get(turnNumber) - 1).getBalance() > share
					.getPrice() && share.isAvailable()) {
				buyShareModel.addElement(share.getName());
			}
		}
		sellShareModel = new DefaultComboBoxModel<String>();

		buyShares = new JButton("buy");
		disabledBuy = new JButton("buy");
		sellShares = new JButton("sell");
		disabledSell = new JButton("sell");
		disabledBuy.setEnabled(false);
		buyShares.setVisible(false);
		disabledSell.setEnabled(false);
		sellShares.setVisible(false);

		shareBuyComboBox = new JComboBox<String>();
		shareQtyToBuy = new JTextField(2);

		shareSellComboBox = new JComboBox<String>();
		shareQtyToSell = new JTextField(2);

		shareBuyComboBox.setModel(buyShareModel);
		shareSellComboBox.setModel(sellShareModel);
		shareBuyComboBox.setEditable(false);
		shareSellComboBox.setPreferredSize(new Dimension(82, 25));
		shareSellComboBox.setEditable(false);
		shareBuyComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tempShare = String.valueOf(shareBuyComboBox
						.getSelectedItem());
				setSharePickedToBuy(tempShare);
				shareQtyToBuy.setText("");
				shareQtyToSell.setText("");
			}
		});

		shareSellComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tempShare = String.valueOf(shareSellComboBox
						.getSelectedItem());
				setSharePickedToSell(tempShare);
				shareQtyToBuy.setText("");
				shareQtyToSell.setText("");
			}

		});
		shareQtyToBuy.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				checkValue();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				checkValue();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				checkValue();
			}

			public void checkValue() {
				String value = shareQtyToBuy.getText();
				int tempQty = 0;
				try {
					tempQty = Integer.parseInt(value);
				} catch (Exception e) {
					
				}
				validQuantities = stock.allowedQuantitites(
						players.get(playersTurn.get(turnNumber) - 1)
								.getBalance(), getSharePickedToBuy());
				if (validQuantities.contains(tempQty)) {
					qtyPickedToBuy = tempQty;
					disabledBuy.setVisible(false);
					buyShares.setVisible(true);

				} else {
					qtyPickedToBuy = 0;
					buyShares.setVisible(false);
					disabledBuy.setVisible(true);
				}
			}
		});
		shareQtyToSell.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent e) {
						checkValue();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						checkValue();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						checkValue();
					}

					public void checkValue() {
						String value = shareQtyToSell.getText();
						int tempQty = 0;
						try {
							tempQty = Integer.parseInt(value);
						} catch (Exception e) {
							
						}
						if (tempQty > 0
								&& tempQty <= players
										.get(playersTurn.get(turnNumber) - 1)
										.getSharedHeld().get(getSharePickedToSell())
										.getQuantity()) {
							qtyPickedToSell = tempQty;
							sellShares.setVisible(true);
							disabledSell.setVisible(false);
						} else {
							sellShares.setVisible(false);
							disabledSell.setVisible(true);
						}
					}
				});

		buying.add(shareBuyComboBox);
		buying.add(shareQtyToBuy);

		buyShares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				players.get(playersTurn.get(turnNumber) - 1).buyShares(
						getSharePickedToBuy(), qtyPickedToBuy,
						stock.getStock().get(getSharePickedToBuy()).getPrice());
				stock.buyShares(getSharePickedToBuy(), qtyPickedToBuy);
				playersInfo.get(playersTurn.get(turnNumber) - 1).setText(
						"<html>"
								+ players.get(playersTurn.get(turnNumber) - 1)
										.displayPlayersSharesAndBalance()
								+ "</html>");
				stockAvailable.setText("<html>" + stock.toString() + "</html>");
				shareQtyToBuy.setText("");
				sellShareModel.removeAllElements();
				for (Share share : players.get(playersTurn.get(turnNumber) - 1)
						.getSharedHeld()) {
					if (share.getQuantity() > 0) {
						sellShareModel.addElement(share.getName());
					}
				}
			}
		});
		sellShares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				players.get(playersTurn.get(turnNumber) - 1).sellShares(
						getSharePickedToSell(), qtyPickedToSell,
						stock.getStock().get(getSharePickedToSell()).getPrice());
				stock.sellShares(getSharePickedToSell(), qtyPickedToSell);
				playersInfo.get(playersTurn.get(turnNumber) - 1).setText(
						"<html>"
								+ players.get(playersTurn.get(turnNumber) - 1)
										.displayPlayersSharesAndBalance()
								+ "</html>");
				stockAvailable.setText("<html>" + stock.toString() + "</html>");
				shareQtyToBuy.setText("");
				shareQtyToSell.setText("");
				sellShareModel.removeAllElements();
				for (Share share : players.get(playersTurn.get(turnNumber) - 1)
						.getSharedHeld()) {
					if (share.getQuantity() > 0) {
						sellShareModel.addElement(share.getName());
					}
				}

			}
		});

		cardPicker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				players.get(playersTurn.get(turnNumber) - 1).setCard(
						deck.dealCard());
				cardPicker.setEnabled(false);
				cardPicker.setText(players.get(playersTurn.get(turnNumber) - 1)
						.getCard().toString());
				finishTurn.setEnabled(true);
				playersCards.get(playersTurn.get(turnNumber) - 1).setEnabled(
						false);
				playersCards.get(playersTurn.get(turnNumber) - 1).setText("?");
				playersCards.get(playersTurn.get(turnNumber) - 1).setFont(
						new Font("Arial", Font.BOLD, 70));
			}

		});

		finishTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cardPicker.setText("<html> pick<br>card</html>");
				cardPicker.setEnabled(true);
				finishTurn.setEnabled(false);
				names.get(playersTurn.get(turnNumber) - 1).setForeground(Color.YELLOW);
				turnNumber++;
				shareQtyToBuy.setText("");
				shareQtyToSell.setText("");
				if (turnNumber == players.size()) {
					finishRound.setVisible(true);
					shareQtyToBuy.setEnabled(false);
					shareQtyToSell.setEnabled(false);
					cardPicker.setEnabled(false);
					for (int i = 0; i < playersCards.size(); i++) {
						playersCards.get(i).setFont(
								new Font("Arial", Font.BOLD, 12));
						playersCards.get(i).setText(
								players.get(i).getCard().toString());
					}
				} else {
					names.get(playersTurn.get(turnNumber) - 1).setForeground(Color.GREEN);
					sellShareModel.removeAllElements();
					for (Share share : players.get(
							playersTurn.get(turnNumber) - 1).getSharedHeld()) {
						if (share.getQuantity() > 0) {
							sellShareModel.addElement(share.getName());
						}
					}
				}
			}
		});
		finishGame = new JButton("Finish Game");
		finishGame.setVisible(false);
		finishGame.setPreferredSize(buttonDim);
		finishRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JLabel name: names) {
					name.setForeground(Color.YELLOW);
				}
				finishTurn.setEnabled(false);
				shareQtyToBuy.setText("");
				shareQtyToSell.setText("");
				finishRound.setVisible(false);
				roundNumber++;
				int temp = playersTurn.get(0);
				playersTurn.remove(0);
				playersTurn.add(temp);
				turnNumber = 0;
				playersPanels.get(playersTurn.get(turnNumber) - 1).setForeground(
						Color.GREEN);
				if (roundNumber > numberOfRounds) {
					finishGame.setVisible(true);
				} else {
					names.get(playersTurn.get(turnNumber) - 1).setForeground(Color.GREEN);
					roundNumberLabel.setText(String.valueOf(roundNumber));
					cardPicker.setEnabled(true);
					shareQtyToBuy.setEnabled(true);
					shareQtyToSell.setEnabled(true);
					for (JButton card : playersCards) {
						card.setEnabled(true);
						card.setText("");
					}
				}
				for (Player player : players) {
					player.adjustShareValues(deck.getDealtCards());
				}
				stock.AdjustSharePrices(deck.getDealtCards());
				deck.collectDealtCards();
				stockAvailable.setText("<html>" + stock.toString() + "</html>");
				sellShareModel.removeAllElements();
				for (Share share : players.get(playersTurn.get(turnNumber) - 1)
						.getSharedHeld()) {
					if (share.getQuantity() > 0 && share.getPrice() > 0) {
						sellShareModel.addElement(share.getName());
					}
				}
			}
		});

		finishGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				finishGameFrame = new FinishGameFrame();
				finishGameFrame.setLayout(new GridBagLayout());
				GridBagConstraints gbct = new GridBagConstraints();
				gameFrame.setEnabled(false);

				for (Player player : players) {
					if (player.getTotalMoney() == highestTotal()) {
						winners.add(player);
					}
				}
				congrats = createSquareJPanel(new Color(153, 0, 51), 500, 50);
				congratulations = new JLabel();
				congratulations.setText("<html>" + "<center>"
						+ "Congratulations!" + "</center>" + "</html>");
				congratulations.setFont(new Font("Bradley Hand ITC", Font.ITALIC | Font.BOLD, 36));
				congratulations.setForeground(Color.YELLOW);
				congrats.add(congratulations);
				winnersPanel = createSquareJPanel(new Color(153, 0, 51), 500, 250);
				winnersLabel = new JLabel();
				winnersLabel.setText("<html>" + getWinners() + "</html>");
				winnersLabel.setFont(new Font("Arial", Font.BOLD, 16));
				winnersLabel.setForeground(Color.YELLOW);
				winnersPanel.add(winnersLabel);
				gbct.gridy = 0;
				finishGameFrame.add(congrats, gbct);
				gbct.gridy = 1;
				finishGameFrame.add(winnersPanel, gbct);
				finishGameFrame.pack();

			}

		});

		buying.add(buyShares);
		buying.add(disabledBuy);
		selling.add(shareSellComboBox);
		selling.add(shareQtyToSell);
		selling.add(sellShares);
		selling.add(disabledSell);

		gameControls.add(buying);
		gameControls.add(selling);
		done.add(finishTurn);
		gameControls.add(done);
		completeRound.add(finishRound);
		completeRound.add(finishGame);
		gameControls.add(completeRound);

		console.add(gameControls);
		console.add(pickCard);

		gameFrame.add(console, gc);

		player6 = createSquareJPanel(new Color(153, 0, 51), 200, 200);
		player6.setLayout(new BoxLayout(player6, 1));
		player6.add(Box.createRigidArea(new Dimension(20, 10)));
		if (players.size() >= 6) {
			player6Info = new JLabel();
			player6Card = new JButton();
			playersCards.add(player6Card);
			playersInfo.add(player6Info);
			player6Info.setText("<html>"
					+ players.get(5).displayPlayersSharesAndBalance()
					+ "</html>");
			player6Info.setForeground(Color.YELLOW);
			names.get(5).setText(players.get(5).getName());
			names.get(5).setFont(new Font("Arial", Font.BOLD, 14));
			player6.add(names.get(5));
			player6.add(player6Info);
			playersPanels.add(player6);
			gameFrame.add(player6, gc);
		} else {
			gameFrame.add(player6, gc);
		}
		Border innerPlayer6 = BorderFactory.createTitledBorder(lined,
				"Player 6:", 2, 2, new Font("Arial", Font.ITALIC, 12),
				Color.YELLOW);
		player6.setBorder(BorderFactory.createCompoundBorder(outerBorder,
				innerPlayer6));
		gc.gridx = 2;
		gc.gridy = 2;
		gameFrame.add(player6, gc);
		Dimension cards = new Dimension(80, 120);
		for (JButton playerCard : playersCards) {
			playerCard.setPreferredSize(cards);
			dealtCards.add(playerCard);
		}
		gameFrame.pack();
	}

	/*
	 * in this method ArrayList of Players is iterated and highest balance 
	 * (current shares value + cash) is returned as an int
	 */
	private int highestTotal() {
		int highestTotal = players.get(0).getTotalMoney();
		for (Player player : players) {
			if (player.getTotalMoney() > highestTotal) {
				highestTotal = player.getTotalMoney();
			}
		}
		return highestTotal;
	}

	/*
	 * This method takes a String from combobox and determines, which share
	 * has the same name as passed String. Index of this share is saved 
	 */
	private void setSharePickedToBuy(String name) {
		for (int i = 0; i < stock.getStock().size(); i++) {
			if (stock.getStock().get(i).getName().equals(name)) {
				sharePickedToBuy = i;
				break;
			}
		}
	}
	
	/*
	 * This method takes a String from combobox and determines, which share
	 * has the same name as passed String. Index of this share is saved 
	 */
	private void setSharePickedToSell(String name) {
		for (int i = 0; i < stock.getStock().size(); i++) {
			if (stock.getStock().get(i).getName().equals(name)) {
				sharePickedToSell = i;
				break;
			}
		}
	}
	
	/*
	 * returns an index of a share picked in the combobox to buy 
	 */
	private int getSharePickedToBuy() {
		return sharePickedToBuy;
	}

	/*
	 * returns an index of a share picked in the combobox to sell 
	 */
	private int getSharePickedToSell() {
		return sharePickedToSell;
	}
	
	/*
	 * Returns String representation of ArrayList of winners to display in the final
	 * window after the game is finished
	 */
	private String getWinners() {
		String printWinners = "<br>";
		for (Player winner : winners) {
			printWinners += winner.getName() + "&nbsp;("
					+ winner.getTotalMoney() + "&nbsp;pounds)<br><br>";
		}
		return printWinners;
	}

	/*
	 * this method takes in parameters: color, width and height and creates
	 * and passes back new JPanel with specified characteristics
	 */
	private JPanel createSquareJPanel(Color color, int width, int height) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(width, height));
		tempPanel.setMaximumSize(new Dimension(width, height));
		tempPanel.setPreferredSize(new Dimension(width, height));
		return tempPanel;
	}
}
