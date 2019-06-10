package binaryToDenary;

import java.util.Scanner;

public class binaryToDenary {

	public static int test(int ourNum) {
		int num3 = 1;
		while (ourNum > num3) {
			num3 = num3 * 2;
		}
		num3 = num3 / 2;
		return num3;
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Denary2Binary & Binary2Denary Converter");
		System.out.println(" ");
		System.out.println("1. Denary to Binary");
		System.out.println("2. Binary to Denary");
		System.out.println("");
		System.out.print("Select an option: ");
		int option = scanner.nextInt();

		if(option == 1) {
			System.out.print("Enter a DENARY number to convert: ");
			int ourNum = scanner.nextInt();
			int num2 = test(ourNum);
			String binary = "";

			while (ourNum != 0) {
				if (ourNum == 0 || num2 == 0) {
					break;
				}
				if (ourNum >= num2) {
					ourNum = ourNum - num2;
					binary += "1";
					num2 = num2 / 2;
				}
				if (ourNum < num2) {
					binary += "0";
					num2 = num2 / 2;
				}
			}
			while (num2 > 0) {
				binary += "0";
				num2 = num2 / 2;
			}
			System.out.println("Binary: " + binary);
		}
		else if(option == 2) {
			int convertingNum = 1;
			int newNum = 0;
			System.out.print("Enter a BINARY number to convert: ");
			String binaryNum = scanner.next();

			int count = binaryNum.length();
			int newCount = binaryNum.length();
			String tempString = "";

			while (count != 0) {
				char tempChar = binaryNum.charAt(0);
				tempString = tempChar + tempString;
				binaryNum = binaryNum.substring(1);
				count--;
			}

			while (newCount != 0) {
				if (tempString.charAt(0) == '1') {
					newNum = newNum + convertingNum;
					newCount--;
					tempString = tempString.substring(1);
					convertingNum = convertingNum * 2;
				} else if (tempString.charAt(0) == '0') {
					convertingNum = convertingNum * 2;
					newCount--;
					tempString = tempString.substring(1);

				}
			}
			System.out.println("Denary: " + newNum);
		}
	}
}
