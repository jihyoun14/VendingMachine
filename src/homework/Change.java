package homework;
// tenWon (10), fiftyWon (50), 
// oneHundredWon (100), fiveHundredWon (500), 
// oneThousandWon (1000)

public class Change {
	
	//protected int tenWon;
	//protected int fiftyWon;
	//protected int oneHundredWon;
	//protected int fiveHundredWon;
	//protected int oneThousandWon;
	
	protected int value;
	protected int number;
	
	//public static final int[] CHANGE = {
	//		1000, 500, 100, 50, 10
	//		};
	
	public Change(int value, int number) {
		this.value = value;
		this.number = number;
		//tenWon = 20;
		//fiftyWon = 20;
		//oneHundredWon = 20;
		//fiveHundredWon = 20;
		//oneThousandWon = 20;
	}

	public void setNumINC() {
		number++;
	}
	
	public void setNumDEC() {
		number--;
	}

	public void setNumDEC(int i) {
		number = number - i;
	}

	public int getNum() {
		return number;
	}
	
	public int sum() {
		return value * number;
	}
	
	public Boolean isEmpty(){
		if (sum() == 0) return true;
		else	return false;
	}
}
