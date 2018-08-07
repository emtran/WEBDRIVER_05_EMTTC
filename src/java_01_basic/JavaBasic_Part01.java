package java_01_basic;

import org.testng.annotations.Test;

public class JavaBasic_Part01 {
	@Test
	public void Exercise() {
		String chuoi = "Automation Testing Tutorials Online 123456";
		int aNumber = 0;
		int i;
		boolean isTesting = false;
		boolean isAutomation = false;
		boolean isOnline = false;
		int indexTurorials;
		String newChuoi;
		String numberString;
		int numberChar = 0;

		
		for (i = 0; i < chuoi.length(); i++) {
			char a = chuoi.charAt(i);
			if ((a == 'a') || (a == 'A')) {
				aNumber++;
			}
		}
		System.out.println("So luong ky tu 'a' co trong chuoi tren la: " + aNumber);

		
		String[] subString = chuoi.split(" ");
		for (i = 0; i < subString.length; i++) {
			String sub = subString[i];
			if (sub.equals("Testing")) {
				isTesting = true;
				break;
			}
			isTesting = false;
		}
		System.out.println("Kiem tra chuoi co chua tu 'Testing'? " + isTesting);

		
		for (i = 0; i < subString.length; i++) {
			if (subString[0].equals("Automation")) {
				isAutomation = true;
				break;
			}
			isAutomation = false;
		}
		System.out.println("Kiem tra chuoi bat dau la 'Automation'? " + isAutomation);
		
		int lengthSugString = subString.length;
		for (i = 0; i < subString.length; i++) {
			if (subString[lengthSugString -1].equals("Online")) {
				isOnline = true;
				break;
			}
			isOnline = false;
		}
		System.out.println("Kiem tra chuoi ket thuc la 'Online'? " + isOnline);
		
		
		indexTurorials = chuoi.indexOf("Tutorials");
		System.out.println("Vi tri 'Tutorials' trong chuoi? " + indexTurorials);
		
		newChuoi = chuoi.replace("Online", "Offline");
		System.out.println(newChuoi);
		
		
		numberString = subString[lengthSugString -1];
		for (i = 0; i < numberString.length(); i++) {
			numberChar ++;
		}
		System.out.println("So luong ky tu la so trong chuoi " + numberChar);
	}
}
