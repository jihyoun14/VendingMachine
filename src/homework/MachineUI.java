/**
 * @author 이지현
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
		setResizable(false); // 사용자가 프레임의 크기 조절할 수 없도록 설정
		setVisible(true);
	}
	
	/**
	 * Title "COFFEE Vending Machine"을 출력하기 위함
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
		 * 제목을 설정한다.
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
		 *  음료수를 선택하는 버튼을 달기 위함
		 */
		public void ButtonPanel(){
			int width = 29;
			JTextField [] BeveragPrices = new JTextField[5];
			JButton [] BeverageButtons = new JButton[5]; 	// coffee name	
			for (int i=0; i<BeverageButtons.length; i++) { // 버튼 배열의 개수(4)만큼 루프
				BeverageButtons[i] = new JButton(machine.NAME[i]);//+ " / " + machine.PRICE[i]); // 버튼 컴포넌트 생성	
				BeverageButtons[i].setBounds(width, 110, 110, 40);
				getContentPane().add(BeverageButtons[i]); // 버튼 달기
				
				BeveragPrices[i] = new JTextField();
				BeveragPrices[i].setText(machine.PRICE[i]+" won");
				BeveragPrices[i].setBounds(width+5, 160, 100, 40);
				BeveragPrices[i].setHorizontalAlignment(SwingConstants.CENTER);
				BeveragPrices[i].setEditable(false);
				getContentPane().add(BeveragPrices[i]);
				
				// 모든 버튼에 Action 리스너 등록
				BeverageButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { // 
						// e.getActionCommand()는 클릭된 버튼의 문자열 정보 리턴
						machine.operate(e.getActionCommand());	
						
						// balance update
						showBalance() ;			
					}
				});
				width += 120;
			}
		}

		
		/**
		 * 거스름돈의 이미지를 설정한다.
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
		 * 거스름돈 버튼을 설정한다.
		 */
		public void ChangePanel() {
			int height = 265;
			JButton [] ChangeButtons = new JButton[5]; 
			String [] names = {"1000", "500", "100", "50", "10"}; //버튼 문자열 배열
	
			for(int i=0; i<ChangeButtons.length; i++) { // 버튼 배열의 개수(4)만큼 루프
				ChangeButtons[i] = new JButton(names[i]); // 버튼 컴포넌트 생성	
				ChangeButtons[i].setBounds(180, height, 70, 30);
				getContentPane().add(ChangeButtons[i]); // 버튼 달기
					
				// 모든 버튼에 Action 리스너 등록
				ChangeButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { // 
					// e.getActionCommand()는 클릭된 버튼의 문자열 정보 리턴
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
		 * 현재 잔액 칸과 반환 버튼을 설정한다.
		 */
		public void manageMoney() {
			
			// 현재 잔액 보여주기
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
			
			// 반환 버튼
			JButton returnButton = new JButton("\uBC18\uD658 \uBC84\uD2BC");
			returnButton.setBounds(442, 400, 100, 90);
			getContentPane().add(returnButton);
			// 버튼에 Action 리스너 등록
			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // 
				// e.getActionCommand()는 클릭된 버튼의 문자열 정보 리턴	
					ReturnChangePanel();
					// balance update
					showBalance() ;			
				}
			});
		}

		/**
		 * 반환버튼을 누르면 거스름돈의 개수가 적인 레이블을 출력한다.
		 */
		public void ReturnChangePanel() {
			int[] numOfChange =	machine.returnChange();	
			
			if (numOfChange[0] == -1) // 거스름돈이 부족했으면 exit
				return;
			
			JTextField panel = new JTextField();
			JTextField number_0 = new JTextField();
			JTextField number_1 = new JTextField();
			JTextField number_2 = new JTextField();
			JTextField number_3 = new JTextField();
			JTextField number_4 = new JTextField();

			// 거스름돈 개수 출력
			panel.setText("↓HERE IS YOUR CHANGE↓");
			panel.setBounds(260, 225, 160, 30);
			panel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.setEditable(false);
			getContentPane().add(panel);

			number_0.setText("x " + numOfChange[0]);
			number_0.setBounds(300, 265, 70, 30);
			number_0.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_0.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_0); // 버튼 달기

			number_1.setText("x " + numOfChange[1]);
			number_1.setBounds(300, 327, 70, 30);
			number_1.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_1.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_1); // 버튼 달기

			number_2.setText("x " + numOfChange[2]);
			number_2.setBounds(300, 389, 70, 30);
			number_2.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_2.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_2); // 버튼 달기

			number_3.setText("x " + numOfChange[3]);
			number_3.setBounds(300, 451, 70, 30);
			number_3.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_3.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_3); // 버튼 달기

			number_4.setText("x " + numOfChange[4]);
			number_4.setBounds(300, 513, 70, 30);
			number_4.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 10));
			number_4.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(number_4); // 버튼 달기
		}
	}

	static public void main(String [] args) {
		new MachineUI();	
	}
}


