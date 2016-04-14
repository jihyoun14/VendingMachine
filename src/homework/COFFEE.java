package homework;
//name, price, numOfPurchased, numOfStock

public class COFFEE {
	
	protected String Name; //이름
	protected int Price; //가격
	protected int numOfPurchased = 0; //팔린 개수
	protected int numOfStock = 10; //재고 개수
	
	public COFFEE(String name, int price) {
		this.Name = name; // = {"AMERICANO", "ESPRESSO", "LATTE", "CAPPUCCINO", "CAFFMOCHA"};
		this.Price = price; // = {1000, 800, 1200, 1500, 1500};
	}
	
	public void setNumOfPurchased() {
		numOfPurchased++;
	}

	public int getNumOfPurchased() {
		return numOfPurchased;
	}
	
	public void setNumOfStock() {
		numOfStock--;
	}

	public int getNumOfStock() {
		return numOfStock;
	}
	
	public boolean isEmpty() {
		if (this.getNumOfStock() == 0) return true;
		else	return false;
	}
}
