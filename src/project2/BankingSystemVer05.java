package project2;

import java.util.Scanner;

import project2.ver05.Account;
import project2.ver05.Menu;
import project2.ver05.MenuChoice;

public class BankingSystemVer05 implements Menu{

	public static void main(String[] args) {
		
		MenuChoice menu = new Account();
		Account acc = new Account();
		
		
		while(true) {
			//메뉴출력을 위한 메소드 호출
			menu.menuShow();

			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();

			switch(choice) {			
				case Menu.MAKE:
					acc.makeAccount();
					break;
				case Menu.DEPOSIT:
					acc.depositMoney();
					break;
				case Menu.WITHDRAW:
					acc.withdrawMoney();
					break;
				case Menu.INQUIRE:
					acc.showAccInfo();
					break;
				case Menu.EXIT:
					System.out.println("프로그램을 종료합니다.");
					//main함수의 종료는 프로그램의 종료와 동일하다. 
					return; 
			}
		}
	}
}
