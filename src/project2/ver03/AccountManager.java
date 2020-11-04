package project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AccountManager extends Account {
	
	private Account[] accClient;
	private int numOfAccount;
	
	public AccountManager() {
	}
	
	public AccountManager(int num) {
		accClient = new Account[num];
		numOfAccount = 0;
	}
	

	// 메뉴출력
	@Override
	public void menuShow() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌계설");
		System.out.println("2.입   금");
		System.out.println("3.출   금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print("선택:");
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		String iaccNum,iname,irank;
		int imoney,inorRate,ihighRate;
		
		System.out.println("***신규계좌개설***");
		
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:");
		
		Scanner scan = new Scanner(System.in);
		int choice2 = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호:");iaccNum = scan.nextLine();
		System.out.print("고객이름:");iname = scan.nextLine();
		System.out.print("잔고:");imoney = scan.nextInt();
		
		if(choice2==1) {	
			System.out.print("기본이자%(정수형태로 입력):"); inorRate = scan.nextInt();
						
			NormalAccount normal =
					new NormalAccount(iaccNum, iname, imoney, inorRate);
			accClient[numOfAccount++] = normal;
		}
		else if(choice2==2) {
			System.out.print("기본이자%(정수형태로 입력):");
			ihighRate = scan.nextInt();
			scan.nextLine();
			System.out.print("신용등급(A,B,C등급):");
			irank = scan.nextLine();
			
			HighCreditAccount high =
					new HighCreditAccount(iaccNum, iname, imoney, ihighRate, irank);
			accClient[numOfAccount++] = high;
		}
		System.out.println("계좌계설이 완료되었습니다.");
		System.out.println();
		
		
	}
	
	 // 입    금
	public void depositMoney() {
		try {
			System.out.println("***입   금***");
			Scanner scan = new Scanner(System.in);
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호:");
			String searchAcc = scan.nextLine();
			System.out.print("입금액:");
			int depMon = scan.nextInt();
			
			if (depMon<0) {
				System.out.println("음수를 입금 할 수 없습니다.");
				return;
			}
			else if (depMon%500!=0) {
				System.out.println("입금액은 500원단위로 가능합니다.");
				return;
			}
				
			for(int i=0 ; i<numOfAccount ; i++) {			
				if(searchAcc.compareTo(accClient[i].accNum)==0) {
					accClient[i].rateResult();
					accClient[i].money += depMon;
					System.out.println("입금이 완료되었습니다.");
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("금액 입력시 문자를 입력할 수 없습니다.");
		}
	}
	
	// 출    금
	public void withdrawMoney() {
		try {
			System.out.println("***출   금***");
			Scanner scan = new Scanner(System.in);
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
			System.out.print("계좌번호:");
			String searchAcc = scan.nextLine();
			System.out.print("출금액:");
			int wdMon = scan.nextInt();
			scan.nextLine();
			
			if (wdMon<0) {
				System.out.println("음수를 출금 할 수 없습니다.");
				return;
			}
			else if (wdMon%1000!=0) {
				System.out.println("출금액은 1000원단위로 가능합니다.");
				return;
			}
			
	
			for(int i=0 ; i<numOfAccount ; i++) {			
				if(searchAcc.compareTo(accClient[i].accNum)==0) {
					if (wdMon>accClient[i].money) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?(YES or NO):");
						String wdMoney = scan.nextLine();
						if(wdMoney.compareTo("YES")==0) {
							wdMon = accClient[i].money;
						}
						else {
							System.out.println("출금을 취소합니다.");
							return;
						}
					}
					accClient[i].money -= wdMon;
					System.out.println("출금이 완료되었습니다.");
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("금액 입력시 문자를 입력할 수 없습니다.");
		}
	}
	
	// 전체계좌정보출력
	public void showAccInfo(){
		System.out.println("***계좌정보출력***");
		for(int i=0 ; i<numOfAccount ; i++) {
			accClient[i].showAllData();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	

}
