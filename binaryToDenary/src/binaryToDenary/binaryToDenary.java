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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Denary2Binary & Binary2Denary Converter");
		System.out.println(" ");
		System.out.println("1. Denary to Binary");
		System.out.println("2. Binary to Denary");
		System.out.println("");
		System.out.print("Select an option: ");
		int option = scanner.nextInt();
		
		switch(option) {
		
		case 1:
			System.out.print("Input:");
			int ourNum = scanner.nextInt();
			int num2 = test(ourNum);
			String binary = "";
			
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
		
		case 2:
			System.out.print("Input:");
			long ourBinary = scanner.nextLong();
	    	long reverse = 0;


		      
		      
		      
		      			
			
		}	
	}		
}
