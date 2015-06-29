/**
 *This class holds details relating to the shares i.e. 
 *    the share name, 
 *    the share quantity,
 *    the share price,
 *     
 * @author  Steven Delaney, Peter Ironside, Rachel Kavanagh, Pawel Paszki
 * @version 1.0
 * @since   12/03/2015 
 */

public class Share {

	private String name;
	private int quantity;
	private int price;
	
	/**
	* Constructor for objects of class Share
	* @param name, 
	* @param quantity and
	* @param price are passed to the constructor to create an instance of Share
	*/
	public Share(String name, int quantity, int price){
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	/**
	* @return name of the share
	*/
	public String getName() {
		return name;
	}
	
	/**
	* @return quantity of share available 
	*/
	public int getQuantity() {
		return quantity;
	}
	
	/**
	* @param change is taken and quantity of particular share is adjusted
	*/
	public void setQuantity(int change) {
		this.quantity += change;
	}
	
	/**
	* @return price of the share
	*/
	public int getPrice() {
		return price;
	}
	
	/**
	* @param change is taken and share's price is adjusted
	* cannot go over 20 and below 0
	*/
	public void setPrice(int change) {
		if (this.price + change > 20) {
			this.price = 20;
		} else if (this.price + change < 0) {
			this.price = 0;
		} else {
			this.price += change;
		}
	}
	
	/**
	* @return true, if share is available, false otherwise
	*/
	public boolean isAvailable() {
		return (quantity > 0);
	}
	
	/**
	* @return String representation of the object of this class
	*/
	public String toString() {
		return name + ": " + quantity + " shares";
	}
	
}
