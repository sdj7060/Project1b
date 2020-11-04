package project2.ver05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Account implements MenuChoice{
	
	Connection con;
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;
	
	
	public Account() {
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
		login();
		try {
			if (con!=null) {
				//System.out.println("DB 연결");
				
				String queay = " INSERT INTO banking_tb VALUES (?, ?, ?) ";
				
				psmt = con.prepareStatement(queay);
				
				System.out.println("***신규계좌개설***");
				Scanner scan = new Scanner(System.in);
				System.out.print("계좌번호:");
				String acc = scan.nextLine();
				System.out.print("이름:");
				String name = scan.nextLine();
				System.out.print("금액:");
				int money = scan.nextInt();
				if (money<0) {
					JDBCException ex = new JDBCException();
					throw ex;
				}
				
				psmt.setString(1, acc);
				psmt.setString(2, name);
				psmt.setInt(3, money);
				
				int affected = psmt.executeUpdate();
				System.out.println("계좌계설이 완료되었습니다.");
				
				psmt.close();
				con.close();
			}
			else {
				System.out.println("DB연결실패");
			}
		}
		catch(InputMismatchException e) {
			System.out.println("금액은 정수로 입력해야합니다.");
		}
		catch (JDBCException e) {
			System.out.println("음수를 입력하면 안됩니다.");
		}
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("중복계좌는 허용 안됩니다.");
		}
		catch (SQLException e) {
		}
	}
		
	
	 // 입    금
	public void depositMoney() {
		login();
		String sql = " UPDATE banking_tb SET money =(?+money) WHERE account =? ";
		try {
			psmt = con.prepareStatement(sql);
			
			Scanner scan = new Scanner(System.in);
			System.out.print("계좌번호:");
			String acc = scan.nextLine();
			System.out.print("금액:");
			int money = scan.nextInt();
			if (money<0) {
				JDBCException ex = new JDBCException();
				throw ex;
			}
			
			psmt.setString(2, acc);
			psmt.setInt(1, money);
			
			int affected = psmt.executeUpdate();
			System.out.println("입금이 완료되었습니다.");
			psmt.close();
			con.close();
		}
		catch (InputMismatchException e) {
			System.out.println("금액은 정수로 입력해야합니다.");
		}
		catch (JDBCException e) {
			System.out.println("음수를 입력하면 안됩니다.");
		}
		catch (SQLException e) {
		}
	}
		
		
	// 출    금
	public void withdrawMoney() {
		login();
		String sql = " UPDATE banking_tb SET money =(money-?) WHERE account =? ";
		try {
			psmt = con.prepareStatement(sql);
			
			Scanner scan = new Scanner(System.in);
			System.out.print("계좌번호:");
			String acc = scan.nextLine();
			System.out.print("금액:");
			int money = scan.nextInt();
			if (money<0) {
				JDBCException ex = new JDBCException();
				throw ex;
			}
			
			stmt = con.createStatement();
			String query = " SELECT money FROM banking_tb ";
			rs = stmt.executeQuery(query);
			while(rs.next()) { 
				int _money = rs.getInt(1);
				if(_money<money) {
					JDBCException ex = new JDBCException();
					throw ex;
				}
			}
			
			psmt.setString(2, acc);
			psmt.setInt(1, money);
			
			int affected = psmt.executeUpdate();
			System.out.println("출금이 완료되었습니다.");
			psmt.close();
			con.close();
		}
		catch (InputMismatchException e) {
			System.out.println("금액은 정수로 입력해야합니다.");
		}
		catch (JDBCException e) {
			System.out.println("잔액이 부족하거나 음수를 입력했습니다.");
		}
		catch (SQLException e) {
		}
	}
	
	// 전체계좌정보출력
	public void showAccInfo(){
		login();
		try {
			stmt = con.createStatement();
			
			String query = " SELECT account, name, money FROM banking_tb ";
			
			rs = stmt.executeQuery(query);
			System.out.println("***계좌정보출력***");
			while(rs.next()) {
				String account = rs.getString(1);
				String name = rs.getString(2);
				int money = rs.getInt(3); 
				
				System.out.println("-------------");
				System.out.println("계좌번호:"+ account);
				System.out.println("고객이름:"+ name);
				System.out.println("잔고:"+ money);
				System.out.println("-------------");
				}
			System.out.println("전체계좌정보 출력이 완료되었습니다.");
		}
		catch (SQLException e) {
		}
	}
	public void login () {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userid = "Kosmo";
			String userpw = "1234";
			con = DriverManager.getConnection(url, userid, userpw);
		}
		catch(ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
		}
	}
	
	
	
}
