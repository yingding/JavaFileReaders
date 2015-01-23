import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * this class can parse the content of a file into an int array.
 * This only works with the input file, which has only an integer each line. 
 * @author Yingding Wang
 */
public class IntFileParser {
	public static void printState(int[] array) {
		System.out.println("Number of Content Parsed is: " + array.length);
	}
	
	public static void toString(int[] array) {
		for (int t : array) {
			System.out.println(t);
		}
	}
	
	public static int[] parseInput(File file, int limit) {		
		LinkedList<Integer> tmpList = new LinkedList<Integer>();
		if (limit != 0) {
			int breakCount = 0;
			try (BufferedReader bR = new BufferedReader(new FileReader(file))){
				for (String line; (line = bR.readLine()) != null;) {
					breakCount++;
					if (breakCount > limit ) break;
					tmpList.add(Integer.parseInt(line));				
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			try (BufferedReader bR = new BufferedReader(new FileReader(file))){
				for (String line; (line = bR.readLine()) != null;) {
					tmpList.add(Integer.parseInt(line));				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*converting LinkedList to int array*/
		int[] intArray = new int[tmpList.size()];
		int i = 0;
		for (Integer intObj : tmpList) {
			intArray[i] = intObj.intValue();
			i++;
		}
		// return the converted primitive int array
		return intArray;		
	}
}
