/*
 * CellTableFunction
 *
 * Main File
 *
 */

package com.exercise5.app;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.io.File;
import com.exercise5.model.Key;
import com.exercise5.model.Value;
import com.exercise5.service.TableFunctions;
import com.exercise5.utility.FileUtil;


public class MainFunction {
	public static void main (String [] args) {
		int exit = 0;
		String fileName = new String();
		ArrayList <LinkedHashMap <Key, Value>> myTable = new ArrayList <LinkedHashMap <Key, Value>>();
		Scanner input = new Scanner(System.in);

		System.out.print("Input a valid Filename: ");
		fileName = input.nextLine();

		while (true) {
			File fileCheck = new File(System.getProperty("user.dir") + "/" + fileName );
			if(fileCheck.exists()) {
				break;
			} else {
				System.out.print("Specified file does not exist, provide another: ");
				fileName = input.nextLine();
			}
		}		
		
		myTable = FileUtil.readFile(fileName, myTable);							//reads the file stores to myTable
		TableFunctions.printTable(myTable);

		while (exit == 0) {
			System.out.println("");
			System.out.println("Menu");
			System.out.println("Search***********1");
			System.out.println("Edit*************2");
			System.out.println("Print************3");
			System.out.println("Reset************4");
			System.out.println("Add Row**********5");
			System.out.println("Sort Row*********6");
			System.out.println("Exit*************7");
			System.out.println("");
			
			System.out.print("Choose corresponding number to choose a Function: ");
			Scanner menu = new Scanner(System.in);
			String function = menu.nextLine();	
			myTable = FileUtil.readFile(fileName, myTable);								//Constantly reads the file after a function was chosen
			
			switch (function) {
				case "1":												//Search Function	
					TableFunctions.searchCell(myTable);
					break;
				case "2":												//Edit Function
					myTable = TableFunctions.editCell(myTable);
					FileUtil.writeFile(fileName,myTable);
					break;
				case "3":												//Print Function
					TableFunctions.printTable(myTable);
					break;
				case "4":												//Reset Function
					myTable = TableFunctions.resetTable();
					FileUtil.writeFile(fileName,myTable);
					break;
				case "5":												//Add Row Function
					myTable = TableFunctions.addRow(myTable);
					FileUtil.writeFile(fileName,myTable);
					break;
				case "6":												//Sort Row Function
					myTable = TableFunctions.sortRow(myTable);
					FileUtil.writeFile(fileName,myTable);
					break;
				case "7":												//Exit
					exit = 1;
					break;
				
				default:										
					System.out.println(function + " is not a valid Function. Choose another function.");
					break;
			}
		}
	}
}
