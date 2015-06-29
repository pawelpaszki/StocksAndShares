/**
 * This class holds details relating to the cards i.e. 
 *    the card name, 
 *    the value, 
 * 
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

public class Card {
	private String name;
	private int value;
	
	/**
	* Constructor for objects of class Card
	*/
	protected Card(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	* @return the card name.
	*/
	public String getName() {
		return name;
	}

	/**
	* @return the card value.
	*/
	public int getValue() {
		return value;
	}
	
	/**
	* @return the string version of the card object. 
	*/
	public String toString() {
		return "<html>" + name + "<br>" + "<center>" +  value + "</center>" + "</html>";
	}
	
}
