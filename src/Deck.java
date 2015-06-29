/**
 * This class simulates the deck of cards available i.e. 
 *    a collection of cards,
 *    a collection of cards dealt,  
 * We can choose a card at random.      
 * 
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

import java.util.ArrayList;
import java.util.Random;



public class Deck {

	private ArrayList<Card> cards;
	private ArrayList<Card> dealtCards;
	private Random random;

	/**
	* Constructor for objects of class Deck. Instantiates all fields.
	* ArrayList of Cards is filled with 26 Cards, which are constructed
	* with the names declared in SharesAndCards enum and appropriate values
	* between 2 and 4 (both positive and negative) except bull and bear - 
	* which have only one value each - 4 and -4 respectively 
	*/
	public Deck() {
		random = new Random();
		cards = new ArrayList<Card>();
		dealtCards = new ArrayList<Card>();
		for (SharesAndCards name : SharesAndCards.values()) {
			for (int i = -4; i <= 4; i++) {
				if (name.toString().equals("BULL") && i < 4)
					continue;
				if (name.toString().equals("BEAR") && i > -4)
					continue;
				if (i > -2 && i < 2)
					continue;
				cards.add(new Card(name.toString(), i));
			}
		}
	}

	/**
	* @return a list of all 26 cards.
	*/
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * This method deals a random card object from the ArrayList of cards.
	 * The card gets added to the ArrayList of dealt cards and is removed
	 * from the ArrayList of cards.
	 * @return the card
	 */
	public Card dealCard() {
		Card card;
		int cardIndex = random.nextInt(cards.size());
		card = cards.get(cardIndex);
		dealtCards.add(card);
		cards.remove(card);
		return card;
	}
	
	/**
	 * This method adds all the dealt cards back into the end of the ArrayList of
	 * cards and saving the creates a new (empty) instance of dealtCards to fill 
	 * again, once cards are dealt.
	 */
	public void collectDealtCards() {
		cards.addAll(dealtCards);
		dealtCards = new ArrayList<Card>();
	}
	/** 
	 * @return ArrayList of the cards dealt
	 */
	public ArrayList<Card> getDealtCards() {
		return dealtCards;
	}

}
