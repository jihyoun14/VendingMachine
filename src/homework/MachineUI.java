/**
 * @author ������
 * @brief Software Engineering HW 1 :: VendingMachine
 * @date 2016-04-11
 */

package homework;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MachineUI extends JFrame {
	
	Machine machine;
	
	public MachineUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		machine = new Machine();
		
		getContentPane().add(new panel());
		setSize(650,650);
		setResizable(false); // ����ڰ� �������� ũ�� ������ �� ������ ����
		setVisible(true);
	}
	
	/**
	 * Title "COFFEE Vending Machine"�� ����ϱ� ����
	 */
	class panel extends JPanel {
		public panel(){
			
			TitleMessage();
			ImagePanel();
			ButtonPanel();
			ChangePanel();
			manageMoney();
			showBalance();
			ReturnChangePanel();
		}	

		/**
		 * ������ �����Ѵ�.
		 */
		public void TitleMessage() {
			getContentPane().setLayout(null);
			JTextField title;
			title = new JTextField();
			title.setBounds(0, 0, 650, 80);
			title.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 18));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setText("COFFEE Vending Machine");
			getContentPane().add(title);
			title.setColumns(10);
			title.setEditable(false);
		}		
		/**
		 *  ������� �����ϴ� ��ư�� �ޱ� ����
		 */
		public void ButtonPanel(){
			int width = 29;
			JTextField [] BeveragPrices = new JTextField[5];
			JButton [] BeverageButtons = new JButton[5]; 	// coffee name	
			for (int i=0; i<BeverageButtons.length; i++) { // ��ư �迭�� ����(4)��ŭ ����
				BeverageButtons[i] = new JButton(machine.NAME[i]);//+ " / " + machine.PRICE[i]); // ��ư ������Ʈ ����	
				BeverageButtons[i].setBounds(width, 110, 110, 40);
				getContentPane().add(BeverageButtons[i]); // ��ư �ޱ�
				
				BeveragPrices[i] = new JTextField();
				BeveragPrices[i].setText(machine.PRICE[i]+" won");
				BeveragPrices[i].setBounds(width+5, 160, 100, 40);
				BeveragPrices[i].setHorizontalAlignment(SwingConstants.CENTER);
				BeveragPrices[i].setEditable(false);
				getContentPane().add(BeveragPrices[i]);
				
				// ��� ��ư�� Action ������ ���
				BeverageButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { // 
						// e.getActionCommand()�� Ŭ���� ��ư�� ���ڿ� ���� ����
						machine.operate(e.getActionCommand());	
						
						// balance update
						showBalance() ;			
					}
				});
				width += 120;
			}
		}

		
		/**
		 * �Ž������� �̹����� �����Ѵ�.
		 */
		public void ImagePanel() {

			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("images/1000.png"));
			lblNewLabel_1.setBounds(29, 235, 130, 65);
			getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon("images//500.png"));
			lblNewLabel_2.setBounds(60, 303, 70, 65);
			getContentPane().add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon("images/100.png"));
			lblNewLabel_3.setBounds(60, 368, 70, 65);
			getContentPane().add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon("images/50.png"));
			lblNewLabel_4.setBounds(60, 493, 71, 65);
			getContentPane().add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setIcon(new ImageIcon("images/10.png"));
			lblNewLabel_5.setBounds(60, 431, 71, 65);
			getContentPane().add(lblNewLabel_5);
			
		}
			
		/**
		 * �Ž����� ��ư�� �����Ѵ�.
		 */
		public void ChangePanel() {
			int height = 265;
			JButton [] ChangeButtons = new JButton[5]; 
			String [] names = {"1000", "500", "100", "50", "10"}; //��ư ���ڿ� �迭
	
			for(int i=0; i<ChangeButtons.length; i++) { // ��ư �迭�� ����(4)��ŭ ����
				ChangeButtons[i] = new JButton(names[i]); // ��ư ������Ʈ ����	
				ChangeButtons[i].setBounds(180, height, 70, 30);
				getContentPane().add(ChangeButtons[i]); // ��ư �ޱ�
					
				// ��� ��ư�� Action ������ ���
				ChangeButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { // 
					// e.getActionCommand()�� Ŭ���� ��ư�� ���ڿ� ���� ����
						machine.addMoney(e.getActionCommand());	
						// balance update
						showBalance() ;	
					}
				});
				height += 62;
			}
		}
		
		public void showBalance() {

			JTextField balance;
			balance = new JTextField();
			balance.setBackground(Color.WHITE);
			balance.setBounds(490, 320, 100, 40);
			balance.setText(machine.getCurrentMoney()+" won");
			getContentPane().add(balance);
			balance.setColumns(10);
			balance.setEditable(false);		
		}
		
		/**
		 * ���� �ܾ� ĭ�� ��ȯ ��ư�� �����Ѵ�.
		 */
		public void manageMoney() {
			
			// ���� �ܾ� �����ֱ�
			getContentPane().setLayout(null);
			JTextField balanceField;
			balanceField = new JTextField();
			balanceField.setBounds(420, 320, 60, 40);
			balanceField.setText("Balance :");
			getContentPane().add(balanceField);
			balanceField.setEditable(false);

			getContentPane().setLayout(null);
			JTextField money;
			money = new JTextField(machine.getCurrentMoney());
			money.setBounds(412, 300, 100, 60);
			money.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			money.setHorizontalAlignment(SwingConstants.CENTER);
			money = new JTextField(machine.getCurrentMoney());
			getContentPane().add(money);
			money.setColumns(10);
			
			// ��ȯ ��ư
			JButton returnButton = new JButton("\uBC18\uD658 \uBC84\uD2BC");
			returnButton.setBounds(442, 400, 100, 90);
			getContentPane().add(returnButton);
			// ��ư�� Action ������ ���
			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // 
				// e.getActionCommand()�� Ŭ���� ��ư�� ���ڿ� ���� ����	
					ReturnChangePanel();
					// balance update
					showBalance() ;			
				}
			});
		}

		/**
		 * ��ȯ��ư�� ������ �Ž������� ������ ���� ���̺��� ����Ѵ�.
		 */
		public void ReturnChangePanel() {
			int[] numOfChange =	machine.returnChange();	
			
			if (numOfChange[0] == -1) // �Ž������� ���������� exit
				return;
			
			JTextField panel = new JTextField();
			JTextField number_0 = new JTextField();
			JTextField number_1 = new JTextField();
			JTextField number_2 = new JTextField();
			JTextField number_3 = new JTextField();
			JTextField number_4 = new JTextField();

			// �Ž����� ���� ���
			panel.setText("��HERE IS YOUR CHANGE��");
			panel.setBounds(260, 225, 160, 30);
			panel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.setEditable(false);
			getContentPane().add(panel);

			number_0.setText("x " + numOfChange[0]);
			number_0.setBounds(300, 265, 70, 30);
			number_0.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_0.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_0); // ��ư �ޱ�

			number_1.setText("x " + numOfChange[1]);
			number_1.setBounds(300, 327, 70, 30);
			number_1.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_1.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_1); // ��ư �ޱ�

			number_2.setText("x " + numOfChange[2]);
			number_2.setBounds(300, 389, 70, 30);
			number_2.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_2.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_2); // ��ư �ޱ�

			number_3.setText("x " + numOfChange[3]);
			number_3.setBounds(300, 451, 70, 30);
			number_3.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_3.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_3); // ��ư �ޱ�

			number_4.setText("x " + numOfChange[4]);
			number_4.setBounds(300, 513, 70, 30);
			number_4.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_4.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_4); // ��ư �ޱ�
		}
	}

	static public void main(String [] args) {
		new MachineUI();	
	}
}


