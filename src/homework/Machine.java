package homework;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
//a key class that connects other three classes.
//거스름돈이 있나, 상품이 있나 검사하는 건  machine class에

public class Machine {
	
	private List<COFFEE> products;
	private List<Change> changes;
	
	private int currentMoney; // 현재 사용 가능한 돈
	
	public static final String[] NAME = {
			"AMERICANO", "ESPRESSO", "LATTE", "CAPPUCCINO", "CAFFMOCHA"
			};
	public static final int[] PRICE = { 
			1000, 800, 1200, 1500, 1500
			};
	public static final int[] CHANGE_VALUE = {
			1000, 500, 100, 50, 10
			};
	
	public Machine() {
			products = new ArrayList<COFFEE>();
			changes = new ArrayList<Change>();
			ProductInitialize();
	}  
	
	/**
	 * initialize beverage name and price
	 */
	public void ProductInitialize() {
		currentMoney = 0;
		for (int i = 0; i < NAME.length; i++) {
			Change change = new Change(CHANGE_VALUE[i], 20); // 가치와 개수
			this.changes.add(change); // 거스름돈 List에 추가
			
			COFFEE beverage = new COFFEE(NAME[i], PRICE[i]); // 가격과 이름 초기화
			this.products.add(beverage); // 커피 List에 추가
		}
	}
	
	/**
	 * 자판기에서 돈을 추가하기 위해 coinButton을 눌렀을 때 작동
	 * @param cmd
	 */
	public void addMoney(String cmd) {
		if (cmd.equals("1000")) { // 눌러진 버튼이 "1000" 면
			changes.get(0).setNumINC();
			currentMoney += 1000; // 현재 사용가능한 돈으로 추가
			//break;
		}
		if (cmd.equals("500")) { // 눌러진 버튼이 "500" 면
			changes.get(1).setNumINC();
			currentMoney += 500;
			//break;
		}
		if (cmd.equals("100")) { // 눌러진 버튼이 "100" 면
			changes.get(2).setNumINC();
			currentMoney += 100;
			//break;
		}
		if (cmd.equals("50")) { // 눌러진 버튼이 "50" 이면
			changes.get(3).setNumINC();
			currentMoney += 50;
			//break;
		}
		if (cmd.equals("10")) { // 눌러진 버튼이 "10" 면
			changes.get(4).setNumINC();
			currentMoney += 10;
			//break;
		}
	}
	
	/**
	 * 커피 버튼을 누르면 동작한다.
	 * @param cmd
	 */
	public void operate(String cmd) {
		
		while (true) {
			if (currentMoney == 0) { // 현재 넣은 돈이 없으면 에러 출력
				error("돈을 먼저 넣어주세요!"); 
				break;
			}
			else {
				if (cmd.equals("AMERICANO")) { // 눌러진 버튼이 "AMERICANO" 면
					if (!isOutOfStock(0)) { // 품절인지 확인한다.
						if (currentMoney >= PRICE[0])	sellCoffee(0); // 품절이 아니고, 현재돈이 부족하지 않으면 판다
						else	error("돈이 " + (PRICE[0]-currentMoney) + "원 부족합니다! 충전해주세요!");
					}
					break;
				}
				if (cmd.equals("ESPRESSO")) { // 눌러진 버튼이 "ESPRESSO" 면
					if (!isOutOfStock(1)) {
						if (currentMoney >= PRICE[1])	sellCoffee(1); // 품절이 아니고 현재돈이 부족하지 않으면 판다
						else	error("돈이 " + (PRICE[1]-currentMoney) + "원 부족합니다! 충전해주세요!");
					}
					break;
				}
				if (cmd.equals("LATTE")) { // 눌러진 버튼이 "LATTE" 면
					if (!isOutOfStock(2)) {
						if (currentMoney >= PRICE[2])	sellCoffee(2); // 품절이 아니고 현재돈이 부족하지 않으면 판다
						else	error("돈이 " + (PRICE[2]-currentMoney) + "원 부족합니다! 충전해주세요!");
					}
					break;
				}
				if (cmd.equals("CAPPUCCINO")) { // 눌러진 버튼이 "CAPPUCCINO" 이면
					if (!isOutOfStock(3)) {
						if (currentMoney >= PRICE[3])	sellCoffee(3); // 품절이 아니고 현재돈이 부족하지 않으면 판다
						else	error("돈이 " + (PRICE[3]-currentMoney) + "원 부족합니다! 충전해주세요!");
					}
					break;
				}
				if (cmd.equals("CAFEMOCHA")) { // 눌러진 버튼이 "CAFEMOCHA" 면
					if (!isOutOfStock(4)) {
						if (currentMoney >= PRICE[4])	sellCoffee(4); // 품절이 아니고 현재돈이 부족하지 않으면 판다
						else	error("돈이 " + (PRICE[4]-currentMoney) + "원 부족합니다! 충전해주세요!");
					}
					break;
				}
			}
		}
	}
	
	/**
	 * 커피를 팔 때 실행되는 메소드
	 * 재고가 늘어난다.
	 * 현재가격이 줄어든다.
	 * @param index
	 */
	public void sellCoffee(int index) {

		ImageIcon coffeeIcon = new ImageIcon("images/coffee.jpg"); // 커피잔 이미지
		
		JOptionPane.showMessageDialog(null, NAME[index] + " 가 나왔습니다!"
				, "COFFEE Vending Machine", JOptionPane.INFORMATION_MESSAGE, coffeeIcon);
		
		products.get(index).setNumOfPurchased(); //누적 판매수 ++
		products.get(index).setNumOfStock(); //재고 --
		currentMoney -= PRICE[index]; // 현재 있는 돈에서 구매한 음료수의 가격만큼 빼기
	}
	
	/**
	 * 최대한 동전 개수를 최소화하기 위한 함수
	 */
	public int[] returnChange() {
		int[] numOfChange = {0, 0, 0, 0, 0, 0}; // 거슬러줄 각 거스름돈의 개수
		int i = 0; // CHANGE_VALUE의 index (0 ~ 4)
		
		if (totalChange() == 0) { // 거스름돈이 부족해서 아직도 거슬러줄 돈이 있다면 error
			error("거슬러 줄 돈이 없어요... 돈을 더 넣어주세요.");
			numOfChange[0] = -1; // 거스름돈이 부족하다는 것을 알려줌
			return numOfChange;
		}
		
		while (currentMoney != 0 && i < 5) { // 거스름돈을 다 계산했을 때
			if (currentMoney >= CHANGE_VALUE[i]) { // 1000원보다 크거나 같으면부터 시작
				
				numOfChange[i] = currentMoney / CHANGE_VALUE[i]; // 몫 = 거슬러줄 거스름돈의 개수
				currentMoney %= CHANGE_VALUE[i]; // 나머지 = 아직 거슬러주지 못한 거스름돈
				
				if (changes.get(i).getNum() < numOfChange[i])	{ // 거슬러줘야할 돈의 개수가 모자랄 때
					numOfChange[i] = changes.get(i).getNum(); // 줄 수 있는 건 다 주고
					currentMoney -= numOfChange[i] * CHANGE_VALUE[i]; // 못 준건 다시 거슬러줘야할 돈에 합쳐서 넘긴다
				}
			}
			i ++;
			if (currentMoney != 0 && i == 4) { // 거스름돈이 부족해서 아직도 거슬러줄 돈이 있다면 error
				error("거슬러 줄 돈이 없어요... 돈을 더 넣어주세요.");
				numOfChange[0] = -1; // 거스름돈이 부족하다는 것을 알려줌
				break;
			}
		}
		
		if (numOfChange[0] != -1) { // 거스름돈이 부족하지 않고 정상작동 했다면 거스름돈에서 거슬러줘야할 만큼 뺀다.
			for (int j = 0; j < 5; j++)
				changes.get(j).setNumDEC(numOfChange[j]);
		}
		
		return numOfChange;
	}

	/**
	 * 품절인지 검사한다.
	 * @param index
	 * @return 품절이면  true 아니면  false
	 */
	public boolean isOutOfStock(int index) {
		if (products.get(index).isEmpty()) // 품절이면
		{
			error("재고가 없습니다! 다른 커피를 선택해주세요.");
			return true;
		}
		else	return false;
	}

	/**
	 * 현재 넣은 돈을 리턴한다.
	 * @return currentMoney
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}
	
	/**
	 * 경고창을 출력하는 메소드
	 */
	public void error(String msg) { 
		JOptionPane.showMessageDialog(null, msg, "커피 자판기 경고", 
				JOptionPane.INFORMATION_MESSAGE);
		return;
	}
	
	/**
	 * 거스름돈 총액을 계산해서 리턴한다.
	 * @return sum of change
	 */
	public int totalChange() {
		return changes.get(0).sum() + changes.get(1).sum() + changes.get(2).sum() + changes.get(3).sum() + changes.get(4).sum();
	}
}

