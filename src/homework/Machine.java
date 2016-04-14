package homework;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
//a key class that connects other three classes.
//�Ž������� �ֳ�, ��ǰ�� �ֳ� �˻��ϴ� ��  machine class��

public class Machine {
	
	private List<COFFEE> products;
	private List<Change> changes;
	
	private int currentMoney; // ���� ��� ������ ��
	
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
			Change change = new Change(CHANGE_VALUE[i], 20); // ��ġ�� ����
			this.changes.add(change); // �Ž����� List�� �߰�
			
			COFFEE beverage = new COFFEE(NAME[i], PRICE[i]); // ���ݰ� �̸� �ʱ�ȭ
			this.products.add(beverage); // Ŀ�� List�� �߰�
		}
	}
	
	/**
	 * ���Ǳ⿡�� ���� �߰��ϱ� ���� coinButton�� ������ �� �۵�
	 * @param cmd
	 */
	public void addMoney(String cmd) {
		if (cmd.equals("1000")) { // ������ ��ư�� "1000" ��
			changes.get(0).setNumINC();
			currentMoney += 1000; // ���� ��밡���� ������ �߰�
			//break;
		}
		if (cmd.equals("500")) { // ������ ��ư�� "500" ��
			changes.get(1).setNumINC();
			currentMoney += 500;
			//break;
		}
		if (cmd.equals("100")) { // ������ ��ư�� "100" ��
			changes.get(2).setNumINC();
			currentMoney += 100;
			//break;
		}
		if (cmd.equals("50")) { // ������ ��ư�� "50" �̸�
			changes.get(3).setNumINC();
			currentMoney += 50;
			//break;
		}
		if (cmd.equals("10")) { // ������ ��ư�� "10" ��
			changes.get(4).setNumINC();
			currentMoney += 10;
			//break;
		}
	}
	
	/**
	 * Ŀ�� ��ư�� ������ �����Ѵ�.
	 * @param cmd
	 */
	public void operate(String cmd) {
		
		while (true) {
			if (currentMoney == 0) { // ���� ���� ���� ������ ���� ���
				error("���� ���� �־��ּ���!"); 
				break;
			}
			else {
				if (cmd.equals("AMERICANO")) { // ������ ��ư�� "AMERICANO" ��
					if (!isOutOfStock(0)) { // ǰ������ Ȯ���Ѵ�.
						if (currentMoney >= PRICE[0])	sellCoffee(0); // ǰ���� �ƴϰ�, ���絷�� �������� ������ �Ǵ�
						else	error("���� " + (PRICE[0]-currentMoney) + "�� �����մϴ�! �������ּ���!");
					}
					break;
				}
				if (cmd.equals("ESPRESSO")) { // ������ ��ư�� "ESPRESSO" ��
					if (!isOutOfStock(1)) {
						if (currentMoney >= PRICE[1])	sellCoffee(1); // ǰ���� �ƴϰ� ���絷�� �������� ������ �Ǵ�
						else	error("���� " + (PRICE[1]-currentMoney) + "�� �����մϴ�! �������ּ���!");
					}
					break;
				}
				if (cmd.equals("LATTE")) { // ������ ��ư�� "LATTE" ��
					if (!isOutOfStock(2)) {
						if (currentMoney >= PRICE[2])	sellCoffee(2); // ǰ���� �ƴϰ� ���絷�� �������� ������ �Ǵ�
						else	error("���� " + (PRICE[2]-currentMoney) + "�� �����մϴ�! �������ּ���!");
					}
					break;
				}
				if (cmd.equals("CAPPUCCINO")) { // ������ ��ư�� "CAPPUCCINO" �̸�
					if (!isOutOfStock(3)) {
						if (currentMoney >= PRICE[3])	sellCoffee(3); // ǰ���� �ƴϰ� ���絷�� �������� ������ �Ǵ�
						else	error("���� " + (PRICE[3]-currentMoney) + "�� �����մϴ�! �������ּ���!");
					}
					break;
				}
				if (cmd.equals("CAFEMOCHA")) { // ������ ��ư�� "CAFEMOCHA" ��
					if (!isOutOfStock(4)) {
						if (currentMoney >= PRICE[4])	sellCoffee(4); // ǰ���� �ƴϰ� ���絷�� �������� ������ �Ǵ�
						else	error("���� " + (PRICE[4]-currentMoney) + "�� �����մϴ�! �������ּ���!");
					}
					break;
				}
			}
		}
	}
	
	/**
	 * Ŀ�Ǹ� �� �� ����Ǵ� �޼ҵ�
	 * ��� �þ��.
	 * ���簡���� �پ���.
	 * @param index
	 */
	public void sellCoffee(int index) {

		ImageIcon coffeeIcon = new ImageIcon("images/coffee.jpg"); // Ŀ���� �̹���
		
		JOptionPane.showMessageDialog(null, NAME[index] + " �� ���Խ��ϴ�!"
				, "COFFEE Vending Machine", JOptionPane.INFORMATION_MESSAGE, coffeeIcon);
		
		products.get(index).setNumOfPurchased(); //���� �Ǹż� ++
		products.get(index).setNumOfStock(); //��� --
		currentMoney -= PRICE[index]; // ���� �ִ� ������ ������ ������� ���ݸ�ŭ ����
	}
	
	/**
	 * �ִ��� ���� ������ �ּ�ȭ�ϱ� ���� �Լ�
	 */
	public int[] returnChange() {
		int[] numOfChange = {0, 0, 0, 0, 0, 0}; // �Ž����� �� �Ž������� ����
		int i = 0; // CHANGE_VALUE�� index (0 ~ 4)
		
		if (totalChange() == 0) { // �Ž������� �����ؼ� ������ �Ž����� ���� �ִٸ� error
			error("�Ž��� �� ���� �����... ���� �� �־��ּ���.");
			numOfChange[0] = -1; // �Ž������� �����ϴٴ� ���� �˷���
			return numOfChange;
		}
		
		while (currentMoney != 0 && i < 5) { // �Ž������� �� ������� ��
			if (currentMoney >= CHANGE_VALUE[i]) { // 1000������ ũ�ų� ��������� ����
				
				numOfChange[i] = currentMoney / CHANGE_VALUE[i]; // �� = �Ž����� �Ž������� ����
				currentMoney %= CHANGE_VALUE[i]; // ������ = ���� �Ž������� ���� �Ž�����
				
				if (changes.get(i).getNum() < numOfChange[i])	{ // �Ž�������� ���� ������ ���ڶ� ��
					numOfChange[i] = changes.get(i).getNum(); // �� �� �ִ� �� �� �ְ�
					currentMoney -= numOfChange[i] * CHANGE_VALUE[i]; // �� �ذ� �ٽ� �Ž�������� ���� ���ļ� �ѱ��
				}
			}
			i ++;
			if (currentMoney != 0 && i == 4) { // �Ž������� �����ؼ� ������ �Ž����� ���� �ִٸ� error
				error("�Ž��� �� ���� �����... ���� �� �־��ּ���.");
				numOfChange[0] = -1; // �Ž������� �����ϴٴ� ���� �˷���
				break;
			}
		}
		
		if (numOfChange[0] != -1) { // �Ž������� �������� �ʰ� �����۵� �ߴٸ� �Ž��������� �Ž�������� ��ŭ ����.
			for (int j = 0; j < 5; j++)
				changes.get(j).setNumDEC(numOfChange[j]);
		}
		
		return numOfChange;
	}

	/**
	 * ǰ������ �˻��Ѵ�.
	 * @param index
	 * @return ǰ���̸�  true �ƴϸ�  false
	 */
	public boolean isOutOfStock(int index) {
		if (products.get(index).isEmpty()) // ǰ���̸�
		{
			error("��� �����ϴ�! �ٸ� Ŀ�Ǹ� �������ּ���.");
			return true;
		}
		else	return false;
	}

	/**
	 * ���� ���� ���� �����Ѵ�.
	 * @return currentMoney
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}
	
	/**
	 * ���â�� ����ϴ� �޼ҵ�
	 */
	public void error(String msg) { 
		JOptionPane.showMessageDialog(null, msg, "Ŀ�� ���Ǳ� ���", 
				JOptionPane.INFORMATION_MESSAGE);
		return;
	}
	
	/**
	 * �Ž����� �Ѿ��� ����ؼ� �����Ѵ�.
	 * @return sum of change
	 */
	public int totalChange() {
		return changes.get(0).sum() + changes.get(1).sum() + changes.get(2).sum() + changes.get(3).sum() + changes.get(4).sum();
	}
}

