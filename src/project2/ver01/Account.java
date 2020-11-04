package project2.ver01;

import java.util.Scanner;



public class Account implements MenuChoice{
	
	private Account[] accClient;
	private int numOfAccount;
	String accNum,name;
	int money;
	
	
	
	public Account() {
	}

	public Account(int num) {
		accClient = new Account[num];
		numOfAccount = 0;
	}
	
	public Account(String accNum, String name, int money) {
		super();
		this.accNum = accNum;
		this.name = name;
		this.money = money;
	}
	
	public void showAllData() {
		System.out.println("-------------");
		System.out.println("계좌번호:"+ accNum);
		System.out.println("고객이름:"+ name);
		System.out.println("잔고:"+ money);
		System.out.println("-------------");
	}

	// 메뉴출력
	@Override
	public void menuShow() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌계설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print("선택:");
		
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호:");accNum = scan.nextLine();
		System.out.print("고객이름:");name = scan.nextLine();
		System.out.print("잔고:");money = scan.nextInt();
		System.out.println("계좌계설이 완료되었습니다.");
		
		Account account = new Account(accNum, name, money);
		accClient[numOfAccount++] = account;
		
	}
	
	 // 입    금
	public void depositMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String searchAcc = scan.nextLine();
		System.out.print("입금액:");
		int depMon = scan.nextInt();

		for(int i=0 ; i<numOfAccount ; i++) {			
			if(searchAcc.compareTo(accClient[i].name)==0) {
				System.out.println(accClient[i].accNum);
				accClient[i].money += depMon;
				System.out.println("입금이 완료되었습니다.");
				isFind = true;
			}
			
		}
		if(isFind==false)
			System.out.println("***찾는 정보가 없습니다.***");
	}
	
	// 출    금
	public void withdrawMoney() {
		boolean isFind = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String searchAcc = scan.nextLine();
		System.out.print("출금액:");
		int depMon = scan.nextInt();

		for(int i=0 ; i<numOfAccount ; i++) {			
			if(searchAcc.compareTo(accClient[i].name)==0) {
				System.out.println(accClient[i].accNum);
				accClient[i].money -= depMon;
				System.out.println("출금이 완료되었습니다.");
				isFind = true;
			}
		}
		if(isFind==false)
			System.out.println("***찾는 정보가 없습니다.***");
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
