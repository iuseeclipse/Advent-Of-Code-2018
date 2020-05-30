import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day_5 {
		
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File("Day-5/input.txt"));
		String polymer1 = fileIn.next();
		StringBuilder polymer = new StringBuilder(polymer1);
		boolean cont = true;
		while(cont) {
			cont = false;
			for(int i = 0; i < polymer.length() - 1 && !cont; i ++) {
				if(Character.toLowerCase(polymer.charAt(i)) == Character.toLowerCase(polymer.charAt(i + 1)) 
						&& ((Character.isUpperCase(polymer.charAt(i)) && Character.isLowerCase(polymer.charAt(i + 1)))
					|| (Character.isLowerCase(polymer.charAt(i)) && Character.isUpperCase(polymer.charAt(i + 1))))) {
					polymer.deleteCharAt(i);
					polymer.deleteCharAt(i);
					cont = true;
				}
			}

		}	
		System.out.println(polymer.length());
	}
	
}
