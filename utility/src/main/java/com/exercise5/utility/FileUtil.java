/* 
 * FileUtil
 * 
 * Handles all reads and writes from and to the specified File
 *
 */

package com.exercise5.utility;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import javax.swing.RowFilter.Entry;
import com.exercise5.model.Key;
import com.exercise5.model.Value;

public class FileUtil {
	
	public static ArrayList <LinkedHashMap <Key, Value>> readFile(String fileName, ArrayList <LinkedHashMap <Key, Value>> myTable) {
		FileReader inFile = null;
		BufferedReader fileInput = null;
		String lineOfFile = null;
		String blankLine = null;

		myTable.clear();		//Clear contents of myTable

		
		try {
			inFile = new FileReader(fileName); 
			fileInput = new BufferedReader(inFile);
			while ((lineOfFile = fileInput.readLine()) != null) {			//Read contents of File per line	
				blankLine = lineOfFile.replaceAll("[^\\x21-\\x7E]","");		//Removing non-printable chars and space
			
				if (blankLine.equals("")) {									//If line of file is blank continue to the next line
					continue;
				}			
			
				myTable.add(FileUtil.fillRow(lineOfFile));						//Add the processed line to myTable
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

		return myTable;
	}

	public static LinkedHashMap <Key,Value> fillRow(String lineInput) {									//parses the line received from the file
		LinkedHashMap <Key, Value> rowComponent = new LinkedHashMap <Key, Value>();
		String inputRow = lineInput;
		String key = new String();
		String value = new String();

		try {
			while (true) {														
				key = inputRow.substring(0, 3);
				value = inputRow.substring(4, 7);

				rowComponent.put(new Key(key), new Value(value));				//adds the parsed Cell to the row

				if (inputRow.length() < 15) {						//used to check if the current index is the last comma
					break;
				} else {
					inputRow = inputRow.substring(8, inputRow.length());
				}
			}

		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Invalid file content format. Kindly fix the file content before proceeding.");
		}

		return rowComponent;
	}
	
	public static void writeFile(String fileName, ArrayList <LinkedHashMap <Key, Value>> myTable) {			//Writes current myTable to specific File
		Map <Key, Value> oneRow = new LinkedHashMap <Key, Value>();
		String toFile = new String();
		int rows = myTable.size();
		int columns = myTable.get(0).size();

		try {
			FileWriter createdFile = new FileWriter(fileName);
			createdFile = new FileWriter(fileName,true);
			
			for (int i = 0; i < rows; i++) {
				oneRow = myTable.get(i);											//gets 1 row of LinkedHashMap
				Set<Map.Entry<Key, Value>> oneRowSet = oneRow.entrySet(); 				//returns set view of Entry
				Iterator <Map.Entry <Key, Value>> oneRowIter = oneRowSet.iterator();	//declare an iterator
				Map.Entry<Key,Value> oneCell = null;
				
				for (int j = 0; j < columns - 1; j++) {
					oneCell = oneRowIter.next();									//get one instance of key-value pair
					toFile = oneCell.getKey().getKey() + "," + oneCell.getValue().getValue() + " ";
					createdFile.write(toFile);
				}
				oneCell = oneRowIter.next();
				toFile = oneCell.getKey().getKey() + "," + oneCell.getValue().getValue()
						 + ((i == rows - 1) ? "" : "\n");
				
				createdFile.write(toFile);
			}

			createdFile.close();

		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
