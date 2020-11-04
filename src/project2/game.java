package project2;

import java.util.Random;
import java.util.Scanner;

public class game {
	
	public static void show(int[][] arr) {
		System.out.println("=======");
		System.out.println(" "+(char)arr[0][0]+" "+(char)arr[0][1]+" "+(char)arr[0][2]);
		System.out.println(" "+(char)arr[1][0]+" "+(char)arr[1][1]+" "+(char)arr[1][2]);
		System.out.println(" "+(char)arr[2][0]+" "+(char)arr[2][1]+" "+(char)arr[2][2]);
		System.out.println("=======");
	}
	
	public static void suffle(int[][] arr) {
		Random random = new Random();
		//랜덤하게 섞이는 부분(x의 반복 횟수 지정 가능)
		for (int x=0 ; x<3 ; x++) {
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
	
	public static void main(String[] args) {
		
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
						char inPut = ipChar.charAt(0);
						try {
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
									System.out.println("잘못입력했습니다.");
									break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("=======");
							System.out.println("이동할수 없습니다.");
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
			System.out.println(result);
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
