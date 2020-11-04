package project2;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2.ver04.Account;
import project2.ver04.AccountManager;
import project2.ver04.AutoSaverT;
import project2.ver04.Menu;
import project2.ver04.MenuChoice;
import project2.ver04.MenuSelectException;



public class BankingSystemVer04 implements Menu{

	public static void main(String[] args) {
		
		MenuChoice menu = new AccountManager();
		Account acc = new AccountManager();
		((AccountManager) acc).loadAccInfo();
		AutoSaverT dt = new AutoSaverT();
		Thread dth = new Thread(dt);
		
		while(true) {
			try {
				//메뉴출력을 위한 메소드 호출
				System.out.println();
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
					case Menu.저장옵션:
						if(dth.isAlive()==false) {
							dt = new AutoSaverT();
							dth = new Thread(dt);
						}
						((AccountManager) acc).showOutoSave(dth);
						break;
					case Menu.GAME:
						((AccountManager) acc).game();
						break;
					case Menu.EXIT:
						((AccountManager) acc).saveAccInfo();
						System.out.println("프로그램을 종료합니다.");
						return; 
					default :
						MenuSelectException ex = new MenuSelectException();
						throw ex;
							
				}
			}
			catch (InputMismatchException e) {
				System.out.println();
				System.err.println("정수를 입력해주세요.");
			} 
			catch (MenuSelectException e) {
				System.out.println();
				System.err.println("범위에 맞는 수를 입력해주세요.");
			}
		}
	}
}


