package project2.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class AccountManager extends Account {
	
	//컬렉션
	HashSet<Account> accSet = new HashSet<Account>();
	
	public AccountManager() {
	}
	

	// 메뉴출력
	@Override
	public void menuShow() {
		System.out.println("--------------------------------Menu---------------------------------");
		System.out.println("1.계좌계설   2.입   금   3.출   금   4.전체계좌정보출력   5.저장옵션   6.3by3game  7.프로그램종료");
		System.out.println("---------------------------------------------------------------------");
//		System.out.println("1.계좌계설");
//		System.out.println("2.입   금");
//		System.out.println("3.출   금");
//		System.out.println("4.전체계좌정보출력");
//		System.out.println("5.저장옵션");
//		System.out.println("6.3by3game");
//		System.out.println("7.프로그램종료");
		System.out.print("선택:");
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		try {
			//변수
			String iaccNum,iname,irank;
			int imoney,inorRate,ihighRate;
			
			//계좌는 50개까지만 허용
			if(accSet.size()>50) {
				System.err.println("개설된 계좌가 너무 많습니다.");
				return;
			}
			
			System.out.println("***신규계좌개설***");
			System.out.println("-----계좌선택------");
			
			//계좌의 타입 선택
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			System.out.print("선택:");
			Scanner scan = new Scanner(System.in);
			int choice2 = scan.nextInt();
			scan.nextLine(); //버퍼 제거
			
			//계좌 정보 입력
			System.out.print("계좌번호:");
			iaccNum = scan.nextLine();
			System.out.print("고객이름:");
			iname = scan.nextLine();
			System.out.print("잔고:");
			imoney = scan.nextInt();
			
			//입력받은 금액이 음수가 아닌지 확인 (try1)
			if(imoney<0) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
			
			//1.   보통계좌라면(choice==1)
			if(choice2==1) {	
				System.out.print("기본이자%(정수형태로 입력):"); inorRate = scan.nextInt();
				
				//NormalAccount객체 생성
				NormalAccount normal =
						new NormalAccount(iaccNum, iname, imoney, inorRate);
				
				//생성된 객체가 HashSet안에 들어가는지 확인(계좌가 중복인지 확인)
				//1. 중복이 아니라면
				if(accSet.add(normal)==true) {
					accSet.add(normal);
				}
				//2. 중복이라면
				else {
					System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n):");
					scan.nextLine();
					String oLap = scan.nextLine();
					//y,n만 입력되도록 if문
					try {
						if(oLap.length()==1) {
							if(oLap.charAt(0) =='y' || oLap.charAt(0) =='n') {	
								if (oLap.charAt(0) == 'y') {
									//Iterator로 컬렉션을 풀어서 같은계좌번호를 찾음
									Iterator itr = accSet.iterator();
									while(itr.hasNext()) {
										Object object = itr.next();
										//같은계좌가 있으면 지우고 다시입력
										if(iaccNum.equals(((Account)object).accNum)) {
											accSet.remove((Account)object);
											accSet.add(normal);
										}
									}
								}
							}
							//y,n이 아닐경우
							else {
								System.err.println("y 혹은 n을 입력해주세요.");
								return;
							}
						}
						//y,n으로 시작하는 긴문자를 입력할경우
						else {
							System.err.println("y 혹은 n을 입력해주세요.");
							return;
						}
					}
					//컬렉션 수정할때 발생
					catch (ConcurrentModificationException e) {
					}
				}
			}
			//2.  신용신뢰계좌라면(choice==2)
			else if(choice2==2) {
				System.out.print("기본이자%(정수형태로 입력):");
				ihighRate = scan.nextInt();
				scan.nextLine();
				System.out.print("신용등급(A,B,C등급):");
				
				//A,B,C만 입력되도록
				while (true) {
					irank = scan.nextLine();
					if(irank.length() == 1) {
						if(irank.charAt(0) != 'A') {
							if(irank.charAt(0) == 'B' || irank.charAt(0) == 'C') {break;}
						}
						if(irank.charAt(0) == 'A') {break;}
						else {System.err.println("A,B,C중 입력해주세요:");}
					}
					else {System.err.println("A,B,C중 입력해주세요:");}
				}
				
				//HighCreditAccount 객체 생성
				HighCreditAccount high =
						new HighCreditAccount(iaccNum, iname, imoney, ihighRate, irank);
				
				//생성된 객체가 HashSet안에 들어가는지 확인(계좌가 중복인지 확인)
				//1. 중복이 아니라면
				if(accSet.add(high)==true) {
					accSet.add(high);
				}
				//2. 중복이라면
				else {
					System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n):");
					String oLap = scan.nextLine();
					//y,n만 입력되도록 if문
					try {
						if(oLap.length()==1) {
							if(oLap.charAt(0) =='y' || oLap.charAt(0) =='n') {	
								if (oLap.charAt(0) == 'y') {
									//Iterator로 컬렉션을 풀어서 같은계좌번호를 찾음
									Iterator itr = accSet.iterator();
									while(itr.hasNext()) {
										Object object = itr.next();
										//같은계좌가 있으면 지우고 다시입력
										if(iaccNum.equals(((Account)object).accNum)) {
											accSet.remove((Account)object);
											accSet.add(high);
										}
									}
								}
							}
							//y,n이 아닌 다른 글자를 입력시
							else {
								System.err.println("y 혹은 n을 입력해주세요.");
								return;
							}
						}
						//y,n으로 시작하는 긴문자를 입력시
						else {
							System.err.println("y 혹은 n을 입력해주세요.");
							return;
						}
					}
					//컬렉션 수정할때 발생
					catch (ConcurrentModificationException e) {
					}
				}
			}
			//3.  1,2 둘다 아니라면(choice!=1  and choice!=2)
			else {
				System.out.println("1.보통계좌 와 2.신용신뢰계좌 중에서 골라주세요");
				return;
			}
		}
		// try1 catch
		catch (MenuSelectException e) {
			System.err.println("음수 입력 불가능");
			return;
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
				System.err.println("음수를 입금 할 수 없습니다.");
				return;
			}
			else if (depMon%500!=0) {
				System.err.println("입금액은 500원단위로 가능합니다.");
				return;
			}
			
			Iterator itr = accSet.iterator();
			while(itr.hasNext()) {
				Object object = itr.next();
				if(searchAcc.equals(((Account)object).accNum)) {
					((Account)object).rateResult();
					((Account)object).money += depMon;
					System.out.println("입금이 완료되었습니다.");
				}
			}
		}
		catch (InputMismatchException e) {
			System.err.println("금액 입력시 문자를 입력할 수 없습니다.");
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
				System.err.println("음수를 출금 할 수 없습니다.");
				return;
			}
			else if (wdMon%1000!=0) {
				System.err.println("출금액은 1000원단위로 가능합니다.");
				return;
			}
			
			Iterator itr = accSet.iterator();
			while(itr.hasNext()) {
				Object object = itr.next();
				if(searchAcc.equals(((Account)object).accNum)) {
					if (wdMon>((Account)object).money) {
						System.err.println("잔고가 부족합니다. 금액전체를 출금할까요?(YES or NO):");
						String wdMoney = scan.nextLine();
						if(wdMoney.compareTo("YES")==0) {
							wdMon = ((Account)object).money;
						}
						else {
							System.out.println("출금을 취소합니다.");
							return;
						}
					}
					((Account)object).money -= wdMon;
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
		Iterator itr = accSet.iterator();
		while(itr.hasNext()) {
			Object object = itr.next();
			
			if(object instanceof Account) {
				((Account)object).showAllData();
			}
 		}
		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	//저장
	public void saveAccInfo() {
		try {
			ObjectOutputStream out =
				new ObjectOutputStream(
					new FileOutputStream("src/project2/AccountInfo.obj")
				);
			
			Iterator itr = accSet.iterator();
			while(itr.hasNext()) {
				Object object = itr.next();
				out.writeObject(object);
			}
		}
		catch (Exception e) {
		}
	}
	//불러오기
	public void loadAccInfo() {
		try {
			ObjectInputStream in = 
				new ObjectInputStream(
						new FileInputStream("src/project2/AccountInfo.obj")
				);
			
			while(true) {
				Object object = (Object)in.readObject();
				accSet.add((Account) object);
				if(object==null) break;
			}	
		}
		catch (Exception e) {
		}
	}
	//자동저장
	public void showOutoSave(Thread dth) {
		int outo = 0;
		System.out.print("1.자동저장On, 2.자동저장Off :");
		Scanner scan = new Scanner(System.in);
		outo = scan.nextInt();	
		try {
			switch (outo) {
			case 1:
				dth.start();
				System.out.println("자동저장합니다.");
				return;
			case 2:
				System.out.println("자동저장을 종료합니다.");
				dth.interrupt();
				return;
			default:
				System.out.println("1,2중에 입력해주세요");
				return;
			}
		}
		catch(IllegalThreadStateException e) {
			System.err.println("이미 자동저장이 실행중입니다.");
			return;
		}
	}
	//게임 화면
	public static void show(int[][] arr) {
		System.out.println("=======");
		System.out.println(" "+(char)arr[0][0]+" "+(char)arr[0][1]+" "+(char)arr[0][2]);
		System.out.println(" "+(char)arr[1][0]+" "+(char)arr[1][1]+" "+(char)arr[1][2]);
		System.out.println(" "+(char)arr[2][0]+" "+(char)arr[2][1]+" "+(char)arr[2][2]);
		System.out.println("=======");
	}
	
	//게임 섞기
	public static void suffle(int[][] arr) {
		Random random = new Random();
		//랜덤하게 섞이는 부분(x의 반복 횟수 지정 가능)
		for (int x=0 ; x<4 ; x++) {
			boolean flag = true;
			for (int i=0 ; i<3 ; i++) {
				for (int j=0 ; j<3 ; j++) {
					if (arr[i][j] == 88) {
						//1~4 사이의 랜덤한 숫자 입력
						int ranNum = random.nextInt(10000)%4+1;
						int[][] imp= new int[1][1];
						try {
							switch (ranNum) {
								case 1: 
									imp[0][0]=arr[i][j];
									arr[i][j]=arr[i-1][j];
									arr[i-1][j]=imp[0][0];
									break;
								case 2:
									imp[0][0]=arr[i][j];
									arr[i][j]=arr[i][j-1];
									arr[i][j-1]=imp[0][0];
									break;
								case 3:
									imp[0][0]=arr[i][j];
									arr[i][j]=arr[i+1][j];
									arr[i+1][j]=imp[0][0];
									break;
								case 4:
									imp[0][0]=arr[i][j];
									arr[i][j]=arr[i][j+1];
									arr[i][j+1]=imp[0][0];
									break;
								default : 
									break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {
						}
						flag = false;
						break;
					}
				}
				if (flag == false) {
					break;
				}
			}
		}
	}
	
	//3by3게임
	public static void game() {
		
		//처음 값 입력
		int[][] arr = new int[3][3];
		int num = 49;
		for (int i=0 ; i<3 ; i++) {
			for (int j=0 ; j<3 ; j++) {
				arr[i][j] = num;
				num++;
			}
		}
		arr[2][2] = 88;
		
		
		suffle(arr);
		
		while (true) {
			boolean flag = true;
			show(arr);
			
			for (int i=0 ; i<3 ; i++) {
				for (int j=0 ; j<3 ; j++) {
					if (arr[i][j] == 88) {
						Scanner sc = new Scanner(System.in);
						int[][] imp= new int[1][1];
						System.out.println("[ 이동 ] a:Left d:Right w:Up s:Down");
						System.out.println("[ 종료 ] x:Exit");
						System.out.print("키를 입력해주세요 :");
						String ipChar = sc.nextLine();
						try {
							if (ipChar.length()==1) {
								char inPut = ipChar.charAt(0);
								switch (inPut) {
									case 'w': 
										imp[0][0]=arr[i][j];
										arr[i][j]=arr[i-1][j];
										arr[i-1][j]=imp[0][0];
										break;
									case 'a':
										imp[0][0]=arr[i][j];
										arr[i][j]=arr[i][j-1];
										arr[i][j-1]=imp[0][0];
										break;
									case 's':
										imp[0][0]=arr[i][j];
										arr[i][j]=arr[i+1][j];
										arr[i+1][j]=imp[0][0];
										break;
									case 'd':
										imp[0][0]=arr[i][j];
										arr[i][j]=arr[i][j+1];
										arr[i][j+1]=imp[0][0];
										break;
									case 'x' :
										return;
									default : 
										System.err.println("잘못입력했습니다.");
										break;
								}
							}
							else {
								System.err.println("한글자만 입력해주세요");
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {
							System.err.println("이동할수 없습니다.");
						}
						flag = false;
						break;
					}
				}
				if (flag == false) {
					break;
				}
			}
			
			int result = 0;
			int eqlnum = 49;
			for (int i=0 ; i<3 ; i++) {
				for (int j=0 ; j<3 ; j++) {
					if (arr[i][j]== eqlnum) {
						result++;
					}
					eqlnum++;
				}
			}
			if (result == 8) {
				show(arr);
				System.out.println("==^^정답입니다^^==");
				System.out.println("재시작하겠습니까?(y를 누르면 재시작, 나머지는 종료)");
				Scanner sc = new Scanner(System.in);
				String ipChar = sc.nextLine();
				char inPut = ipChar.charAt(0);
				if(inPut!='y') {
					return;
				}
				suffle(arr);
			}
		}
	}
}


