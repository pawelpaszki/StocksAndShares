/**
 * This class creates the object Player holding details of
 *   name,
 *   the amount of money the player has(balance),
 *   a collection of their shares
 *   and the object card
 *  
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.util.ArrayList;


public class Player {

	private String name;
	private int balance;
	private ArrayList<Share> sharesHeld;
	private Card card;

	/**
	* Constructor for objects of class Player. 
	* @param name is passed as a Player name. Balance is 80 by default
	* Player gets ArrayList of shares (by default 0 shares of each type)
	*/
	public Player(String name) {
		this.name = name;
		this.balance = 80;
		sharesHeld = new ArrayList<Share>();
		for (SharesAndCards share : SharesAndCards.values()) {
			if (!(share.toString().equals("BULL"))
					&& !(share.toString().equals("BEAR")))
				sharesHeld.add(new Share(share.toString(), 0, 10));
		}
	}

	/** 
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * @return the balance of money the player has
	 */
	public int getBalance() {
		return balance;
	}
	
	/** 
	 * sets the players balance of money
	 * @param the amount of money the player now has
	 */
	public void setBalance(int change) {
		this.balance += change;
	}
	
	/** 
	 * using an array list of Shares
	 * @return a list of the shares the player has
	 */
	public ArrayList<Share> getSharedHeld() {
		return sharesHeld;
	}
	
	/** 
	 * @return the card the player has
	 */
	public Card getCard() {
		return card;
	}
	/** 
	 * sets the cards held by the user
	 * @param the card the player now holds
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	
	/** 	  
	 * @return true if the user has no shares, false otherwise
	 */
	public boolean noShares() {
		int numberOfShares = 0;
		for (Share share : sharesHeld) {
			numberOfShares += share.getQuantity();
		}
		return (numberOfShares == 0);
	}

	/** 
	 * tells the user the value of the shares using the quantity and price of shares
	 * @return the value of the shares 
	 */
	public int sharesValue() {
		int sharesValue = 0;
		for (Share share: sharesHeld) {
			sharesValue += (share.getQuantity() * share.getPrice());
		}
		return sharesValue;
	}
	
	/** 
	 * @return a String made of the quantity, name and value of the shares a player has
	 */
	public String displayPlayersSharesAndBalance() {
		String playersShares = "<br>";
		for (Share share : sharesHeld) {
			if (share.getName().length() < 8) {
				playersShares += share.getName() + ": &nbsp;" + share.getQuantity() + "<br>\n";
			} else {
				playersShares += share.getName() + ": &nbsp;" + share.getQuantity() + "<br>\n";
			}
			
		}
		playersShares += "<br>Balance: &nbsp;" + getBalance() + "<br>";
		return playersShares;
	}

	/** 
	 * @param quantity and
	 * @param price of the share are passed to this method and method 
	 * @return true, if Player's balance is not smaller than product of
	 * share's quantity and price
	 */
	public boolean canAfford(int quantity, int price) {
		return ((quantity * price) <= getBalance());
	}

	/** 
	 * @param shareIndex
	 * @param quantity and
	 * @param price are passed to the method to determine which share was bought, 
	 * what was the quantity and price. Balance of Player and sharesHeld ArrayList
	 * are adjusted accordingly
	 */
	public void buyShares(int shareIndex, int quantity, int price) {
		sharesHeld.get(shareIndex).setQuantity(quantity);
		setBalance(-price * quantity);
	}

	/** 
	 * @param shareIndex
	 * @param quantity and
	 * @param price are passed to the method to determine which share was sold, 
	 * what was the quantity and price. Balance of Player and sharesHeld ArrayList
	 * are adjusted accordingly
	 */
	public void sellShares(int shareIndex, int quantity, int price) {
		sharesHeld.get(shareIndex).setQuantity(-quantity);
		setBalance(price * quantity);
	}

	/** 
	 * @param shareIndex indicates the share. this method 
	 * @return true if Player has specified share or not
	 */
	public boolean haveThisShare(int shareIndex) {
		for (int i = 0; i < sharesHeld.size(); i++) {
			if (i == shareIndex) {
				return (sharesHeld.get(i).getQuantity() > 0);
			}
		}
		return false;
	}

	/** 
	 * This method is used to adjust values of shares after each round is 
	 * completed.
	 * @param dealtCards ArrayList is passed to do so
	 */
	public void adjustShareValues(ArrayList<Card> dealtCards) {
		for (int i = 0; i < dealtCards.size(); i++) {
			for (Share share : sharesHeld) {
				if (dealtCards.get(i).getName().equals(share.getName())) {
					share.setPrice(dealtCards.get(i).getValue());
				} else if ((dealtCards.get(i).getName()
						.equalsIgnoreCase("bull"))
						|| (dealtCards.get(i).getName()
								.equalsIgnoreCase("bear"))) {
					share.setPrice(dealtCards.get(i).getValue());
				}
			}
		}
	}
	
	/** 
	 * @return total balance - all cash + all shares value combined
	 */
	public int getTotalMoney() {
		return (sharesValue() + getBalance());
	}
	
	/** 
	 * @return number of types of shares from sharesHeld ArrayList
	 */
	public int ShareTypesNumber() {
		return sharesHeld.size();
	}
}
