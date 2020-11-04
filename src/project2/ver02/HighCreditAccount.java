package project2.ver02;

public class HighCreditAccount extends Account {
	/*
	 신용신뢰계좌 > 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
	NormalAccount 와 마찬가지로 생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다.
	고객의 신용등급을 A, B, C로 나누고 계좌개설시 이 정보를 등록한다.
	A,B,C 등급별로 각각 기본이율에 7%, 4%, 2%의 이율을 추가로 제공한다.
	 */
	int highRate;
	String rank;

	public HighCreditAccount(String accNum, String name, int money, int highRate, String rank) {
		super(accNum, name, money);
		this.highRate = highRate;
		this.rank = rank;
	}
	
	@Override
	public void showAllData() {
		super.showAllData();
		System.out.println("기본이자>"+ highRate + "%");
		System.out.println("신용등급>"+ rank);
		System.out.println("-------------");
	}
	
	@Override
	public void rateResult() {
		if (rank.charAt(0) == 'A') {
			money = (int)((int)money*(1+((double)highRate/100)+A));
		}
		else if (rank.charAt(0) == 'B') {
			money = (int)((int)money*(1+((double)highRate/100)+B));
		}
		else if (rank.charAt(0) == 'C') {
			money = (int)((int)money*(1+((double)highRate/100)+C));
		}
	}
	
}	
