package project2.ver04;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Scanner;

public class AutoSaverT extends AccountManager implements Runnable{
	
	@Override
	public void run() {
		while(true) {
			try {
				save();
				Thread.sleep(5000);
				System.out.println("저장확인");
			}
			catch (InterruptedException e) {
				break;
			}
		}
	}
	public void save() {
		try {
			ObjectOutputStream out =
				new ObjectOutputStream(
					new FileOutputStream("src/project2/AutoSaveAccount.txt")
				);
			
			Iterator itr = accSet.iterator();
			while(itr.hasNext()) {
				Object object = itr.next();
				out.writeObject(object);
			}
		}
		catch (Exception e) {
			System.out.println("파일저장시 예외발생");
		}
	}
}

