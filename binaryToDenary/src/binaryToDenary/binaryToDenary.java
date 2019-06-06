package binaryToDenary;

import java.util.Scanner;

public class binaryToDenary {

	public static int test(int ourNum) {
		int num3 = 1;
		while(ourNum>num3) {
			num3 = num3*2;
		}
		num3 = num3/2;
		return num3;
	}
	
	public static void main(String[] args) {
		String binary = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input:");
		int ourNum = scanner.nextInt();
		int num2 = test(ourNum);
				
		while(ourNum != 0) {
			if(ourNum == 0 || num2 == 0) {
				break;
			}
			if(ourNum >= num2) {
				ourNum = ourNum-num2;
				binary+="1";
				num2 = num2/2;
			}
			if(ourNum < num2) {
				binary+="0";
				num2=num2/2;
			}
		}
		while(num2 > 0 ) {
			binary+="0";
			num2 = num2/2;	
		}
		System.out.println("Binary: "+binary);
	}
}
