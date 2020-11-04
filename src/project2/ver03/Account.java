package project2.ver03;

import java.util.Scanner;

public abstract class Account implements MenuChoice, CustomSpecialRate{
	
	String accNum,name;
	int money;
	
	public Account() {
	}
	
	public Account(String accNum, String name, int money) {
		super();
		this.accNum = accNum;
		this.name = name;
		this.money = money;
	}
	
	public void showAllData() {
		System.out.println("-------------");
		System.out.println("계좌번호>"+ accNum);
		System.out.println("고객이름>"+ name);
		System.out.println("잔고>"+ money);
	}
	
	@Override
	public void menuShow() {
	}
	
	public void rateResult() {
	}
	
}
