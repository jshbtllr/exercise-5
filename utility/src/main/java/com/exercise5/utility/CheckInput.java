/*
 * CheckInput
 *
 * Handles all the user input checkings
 *
 */

package com.exercise5.utility;
import java.util.Scanner;

public class CheckInput {												
	public static int checkInteger() {
		Scanner input = new Scanner(System.in);
		String inLine = new String();
		int inVal;

		while (true){
			inVal = 0;
			inLine = input.nextLine();
			
			try{
				inVal = Integer.parseInt(inLine);
				
				if (inVal < 1) {
					System.out.print("Input invalid. Input should be greater than 0: ");
				} else {
					break;
				}

			} catch (NumberFormatException ne) {
				System.out.print("Input not a number, please provide another: ");
			}
		}

		return inVal;
	}

	public static String userInput() {							//Checks if the user input is valid
		Scanner input = new Scanner(System.in);
		String lineInput = new String();

		while ((lineInput.length() < 1) || (lineInput.length() != 3)) {
			lineInput = input.nextLine();
			lineInput = lineInput.replaceAll("[^\\x20-\\x7E]","");
			
			if ((lineInput.length() < 1) || (lineInput.length() != 3)) {
				System.out.print("Input should only be 3 characters. Change input to:");
			}
		}

		return lineInput;
	}

	public static String searchInput() {							//Checks if the user input is valid
		Scanner input = new Scanner(System.in);
		String lineInput = new String();

		while ((lineInput.length() < 1) || (lineInput.length() > 6)) {
			lineInput = input.nextLine();
			lineInput = lineInput.replaceAll("[^\\x20-\\x7E]","");
			
			if ((lineInput.length() < 1) || (lineInput.length() > 6)) {
				System.out.print("Search character/s should only be 6 characters or less. Change input to:");
			}
		}

		return lineInput;
	}
}