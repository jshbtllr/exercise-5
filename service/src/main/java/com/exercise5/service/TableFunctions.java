/*
 * ManipulateTable
 *
 * Where all Table Manipulation Functions are done
 *
 */

package com.exercise5.service;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections;
import com.exercise5.model.Key;
import com.exercise5.model.Value;
import com.exercise5.utility.InputValidationUtil;

public class TableFunctions {
	public static ArrayList <LinkedHashMap <Key, Value>> editCell(ArrayList <LinkedHashMap <Key, Value>> myTable) {
		Map <Key, Value> oneRow = new LinkedHashMap <Key, Value>();
		int rowNumber = myTable.size();
		int columnNumber = myTable.get(0).size();
		int editRow = 0;
		int editColumn = 0;
		int flag = 0;
		String key = new String();
		String value = new String();
		String option = new String();

		do {
			System.out.println("\nSpecify which cell to update");
			System.out.print("Specify Row: ");
			editRow = InputValidationUtil.checkInteger();	

			System.out.print("Specify Column: ");
			editColumn = InputValidationUtil.checkInteger();	
							
			if((editRow > rowNumber) || (editColumn > columnNumber)) {
				System.out.print("\nSpecified Cell is out of bounds.\n");	//prommpt if out of bounds
			}

		} while ((editRow > rowNumber) || (editColumn > columnNumber));		//get input until the cell is in bounds of myTable

		oneRow = myTable.get(editRow-1);		//get the row instance
		Set<Map.Entry<Key, Value>> oneRowSet = oneRow.entrySet();			//get set view
		Iterator <Map.Entry<Key, Value>> oneRowIter = oneRowSet.iterator();	//create iterator
		Map.Entry<Key, Value> oneCell = null;
		
		for(int i = 0; i < editColumn; i++){
			oneCell = oneRowIter.next();
		}
		
		key = oneCell.getKey().getKey();
		value = oneCell.getValue().getValue();

		System.out.println("\nSelect which element to edit");
		System.out.println("Edit Key only**************1");
		System.out.println("Edit Value only************2");
		System.out.println("Edit Both element**********3");

		while (flag == 0) {
			System.out.print("\nInput option: ");
			Scanner menu = new Scanner(System.in);
			option = menu.nextLine();

			switch (option) {
				case "1":
					flag = 1;
					System.out.print("\nChange Key to: ");
					key = InputValidationUtil.userInput();
					break;				
				case "2":
					flag = 1;
					System.out.print("\nChange Value to: ");
					value = InputValidationUtil.userInput();
					break;
				case "3":
					flag = 1;
					System.out.print("\nChange Key to:");
					key = InputValidationUtil.userInput();
					System.out.print("\nChange Value to: ");
					value = InputValidationUtil.userInput();
					break;

				default:
					System.out.println("\n" + option + " is not a valid option.");

			}
		}

		oneCell.getKey().setKey(key);
		oneCell.getValue().setValue(value);
	
		return myTable;
	}

	public static void printTable(ArrayList <LinkedHashMap <Key, Value>> myTable) {
		try {		
			for (Map <Key, Value> oneRow : myTable) {
				Set<Map.Entry<Key, Value>> oneRowSet = oneRow.entrySet();
				Iterator<Map.Entry<Key, Value>> oneRowIter = oneRowSet.iterator();
				Map.Entry<Key, Value> oneCell = null;
				
				while(oneRowIter.hasNext()) {
					oneCell = oneRowIter.next();
					System.out.print(oneCell.getKey().getKey() + "," + oneCell.getValue().getValue() + " ");
				}
				System.out.println("");
			}
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("\nInvalid file content format. Kindly fix the file content before proceeding.");
		}
	}

	public static void searchCell(ArrayList <LinkedHashMap <Key, Value>> myTable) {
		int searchCounter = 0;
		int row = 0;
		int found = 0;
		int index = 0;
		String searchChar = new String();
		String oneCell = new String();

		System.out.print("Input Search Character: ");
		searchChar = InputValidationUtil.searchInput();

		for (Map <Key,Value> oneRow : myTable) {
			Set <Map.Entry<Key, Value>> oneRowSet = oneRow.entrySet();			//gets one Set view
			Iterator <Map.Entry<Key, Value>> oneRowIter = oneRowSet.iterator();	//iterator
			Map.Entry<Key, Value> element = null;
			row++;
			
			for (int j = 0; j < myTable.get(0).size(); j++) {
				index = 0;
				searchCounter = 0;
				element = oneRowIter.next();
				oneCell =  element.getKey().getKey() + element.getValue().getValue();

				searchCounter = StringUtils.countMatches(oneCell,searchChar);	//returns the number of searchChar found in oneCell

				if (searchCounter > 0) {
					found++;
					
					if (found == 1) {
						System.out.println("\nSearch Character \"" + searchChar + "\" found");
					}

					System.out.println(((searchCounter > 1) ? (searchCounter + " times") : "Once") + " at Row: " + (row + 1) 
								       + " Column: " + (j + 1));
				}
			}
		}

		if (found == 0) {
			System.out.println("Search Character: \"" + searchChar + "\" not found");
		}
	}

	public static ArrayList <LinkedHashMap <Key, Value>> addRow(ArrayList <LinkedHashMap <Key, Value>> myTable) {
		Map <Key, Value> addedRow = new LinkedHashMap <Key, Value>();
		int maxRow = myTable.size() + 1;
		int maxColumn = myTable.get(0).size();
		String key = new String();
		String value = new String();

		for (int i = 0; i < maxColumn; i++) { 
			System.out.println("For Column " + (i+1));
			System.out.print("Provide Key: ");
			key = InputValidationUtil.userInput();
			
			System.out.print("Provide Value: ");
			value = InputValidationUtil.userInput();
			
			addedRow.put(new Key(key), new Value(value));
		}
		
		myTable.add(maxRow-1,(LinkedHashMap <Key, Value>)addedRow);

		return myTable;
	}

	public static ArrayList <LinkedHashMap <Key, Value>> sortRow(ArrayList <LinkedHashMap <Key, Value>> myTable) {
		Map <Key, Value> oneRow = new LinkedHashMap <Key, Value>();
		
		String oneCell = new String();
		ArrayList <String> unsortedCells = new ArrayList <String>();
		LinkedHashMap <Key, Value> sorted = new LinkedHashMap <Key, Value>();
		int editRow = 0;
		
		do {
			System.out.print("Which row to sort? ");
			editRow = InputValidationUtil.checkInteger();
		} while (editRow > myTable.size());

		oneRow = myTable.get(editRow - 1);
		Set <Map.Entry<Key, Value>> oneRowSet = oneRow.entrySet();
		Iterator <Map.Entry<Key, Value>> oneRowIter = oneRowSet.iterator();
		Map.Entry<Key, Value> element = null;
		oneRowIter = oneRowSet.iterator();
		
		while (oneRowIter.hasNext()) {
			element = oneRowIter.next();
			oneCell = element.getKey().getKey() + element.getValue().getValue();
			unsortedCells.add(oneCell);
		}

		Collections.sort(unsortedCells);

		for (int j = 0; j < unsortedCells.size(); j++) {
			oneRowIter = oneRowSet.iterator();
			while (oneRowIter.hasNext()) { 
				element = oneRowIter.next();
				oneCell = element.getKey().getKey() + element.getValue().getValue();
				
				if (oneCell.equals(unsortedCells.get(j)) == true) {
					sorted.put(element.getKey(), element.getValue());
					oneRowIter.remove();
				}
			}
		}
		

		myTable.set(editRow-1, sorted);
		
		return myTable;
	}

	public static ArrayList <LinkedHashMap <Key, Value>> resetTable() {
		ArrayList <LinkedHashMap <Key, Value>> myTable = new ArrayList <LinkedHashMap <Key, Value>>();
		Map <Key, Value> oneRow = null;
		
		int row = 0;
		int column = 0;
		
		System.out.print("Input Number of Rows: ");
		row = InputValidationUtil.checkInteger();
		
		System.out.print("Input Number of Columns: ");
		column = InputValidationUtil.checkInteger();

		for (int i = 0; i < row; i++) {
			oneRow = new LinkedHashMap <Key, Value>();

			for (int j = 0; j < column; j++) {
				oneRow.put(new Key(RandomStringUtils.randomAscii(3)), new Value(RandomStringUtils.randomAscii(3)));		//randomAscii(n) generates random ascii string with length of n
			}
			
			myTable.add((LinkedHashMap <Key, Value>) oneRow);
		}

		return myTable;
	}
}