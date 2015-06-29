 /**
 *This class holds details relating to the stock market. It has one field
 *    ArrayList of shares
 *     
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Stock {

	private ArrayList<Share> shares;

	/**
	* Constructor for the arrayList oh shares, for the Stock object. SharesAndCards 
	* enum values are iterated over and each share is assigned one of their values, 
	* except bull and bear 
	*/
	public Stock() {
		shares = new ArrayList<Share>();
		for (SharesAndCards share : SharesAndCards.values()) {
			if (!share.toString().equals("BULL") && !share.toString().equals("BEAR"))
			shares.add(new Share(share.toString(), 28, 10));
		}
	}

	/** 
	 * @return an ArrayList of Shares
	 */
	public ArrayList<Share> getStock() {
		return shares;
	}

	/** 
	 * This method is used to adjust values of shares after each round is 
	 * completed.
	 * @param dealtCards ArrayList is passed to do so
	 */
	public void AdjustSharePrices(ArrayList<Card> dealtCards) {
		for (int i = 0; i < dealtCards.size(); i++) {
			for (Share share : shares) {
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
	 * If all the shares in the Stock are no longer available 
	 * @return true, false otherwise
	 */
	public boolean noSharesInStock() {
		int numberOfShares = 0;
		for (Share share: shares) {
			 numberOfShares += share.getQuantity();
		}
		return (numberOfShares == 0);
	}
	
	/**
	 * This method is used to iterate over ArrayList of shares and find out, 
	 * which share has the lowest price and 
	 * @return the value of it
	 */
	public int cheapestAvailableShare() {
		Share cheapestShare = shares.get(0);
		for (Share share: shares) {
			 if (share.isAvailable() && share.getPrice() < cheapestShare.getPrice()) {
				 cheapestShare = share;
			 }
		}
		return cheapestShare.getPrice();
	}
	
	/** 
	 * @return number of types of shares from sharesHeld ArrayList
	 */
	public int numberOfShareTypes() {
		return shares.size() ;
	}
	
	/** 
	 * @param playersBalance and 
	 * @param shareIndex are passed to determine the quantities of share, which can be purchased
	 * at one buying operation (if over 5 - only multiples of 5 are available)
	 * @return HashSet of quantities available
	 */
	public HashSet<Integer> allowedQuantitites(int playersBalance, int shareIndex) {
		HashSet<Integer> allowedQuantities = new HashSet<Integer>();
		if (shares.get(shareIndex).getQuantity() > 0 && !(shares.get(shareIndex).getPrice() > playersBalance) && shares.get(shareIndex).getPrice() > 0) {
			int maxQty = playersBalance / shares.get(shareIndex).getPrice();
			for (int i = 1; i <= maxQty; i++) {
				if (i <= 5 || i % 5 == 0) {
					if (i <= shares.get(shareIndex).getQuantity())
					allowedQuantities.add(i);
				}
			}
		}
		return allowedQuantities;
	}
	
	/** 
	 * @param quantity is passed to the method. 
	 * @return true, if quantity is not greater than 5 or if divisible 5 
	 * with no remainder
	 */
	public boolean correctQuantity(int quantity) {
		if (quantity > 5 && quantity % 5 == 0) {
			return true;
		} else if (quantity <= 5) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 
	 * @param shareIndex and 
	 * @param quantity are passed to the method. ArrayList of Shares is adjusted
	 * accordingly
	 */
	public void buyShares(int shareIndex, int quantity) {
		shares.get(shareIndex).setQuantity(-quantity);
	}
	
	/** 
	 * @param shareIndex and 
	 * @param quantity are passed to the method. ArrayList of Shares is adjusted
	 * accordingly
	 */
	public void sellShares(int shareIndex, int quantity) {
		shares.get(shareIndex).setQuantity(quantity);
	}

	public String getPrices(){
		String prices = "";
		for (Share share: shares) {
			prices += share.getPrice() + " ";
		}
		return prices;
	}
	
	/** 
	 * @return String representation of Stock
	 */
	public String toString() {
		String stock = "<br>";
		for (Share share : shares) {
				stock += share.getName() + ":   " + share.getQuantity()
						+ " shares : " + share.getPrice() + " pounds/share\n<br><br>";
		}
		return stock;
	}
}
