package project2.ver04;

public class NormalAccount extends Account {
	/*
	 보통예금계좌 > 최소한의 이자를 지급하는 자율 입출금식 계좌
	보통예금계좌를 의미한다.
	생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
	 */
	int norRate;
	
	public NormalAccount(String accNum, String name, int money, int norRate) {
		super(accNum, name, money);
		this.norRate = norRate;
	}
	
	@Override
	public void showAllData() {
		super.showAllData();
		System.out.println("기본이자>"+ norRate + "%");
		System.out.println("-------------");
	}
	
	@Override
	public void rateResult() {
		money = (int)((int)money*(1+((double)norRate/100)));
	}
	
	
}
