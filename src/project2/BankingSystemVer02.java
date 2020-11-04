package project2;

import java.util.Scanner;

import project2.ver02.Account;
import project2.ver02.AccountManager;
import project2.ver02.MenuChoice;
import project2.ver02.Menu;


public class BankingSystemVer02 implements Menu{

	public static void main(String[] args) {
		
		MenuChoice menu = new AccountManager();
		Account acc = new AccountManager(50);
		
		
		while(true) {
			//메뉴출력을 위한 메소드 호출
			menu.menuShow();
			
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();

			switch(choice) {			
				case Menu.MAKE:
					((AccountManager) acc).makeAccount();
					break;
				case Menu.DEPOSIT:
					((AccountManager) acc).depositMoney();
					break;
				case Menu.WITHDRAW:
					((AccountManager) acc).withdrawMoney();
					break;
				case Menu.INQUIRE:
					((AccountManager) acc).showAccInfo();
					break;
				case Menu.EXIT:
					System.out.println("프로그램을 종료합니다.");
					//main함수의 종료는 프로그램의 종료와 동일하다. 
					return; 
			}
		}
	}
}
