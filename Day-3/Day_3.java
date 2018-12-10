import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day_3 {
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("input.txt");
		Scanner fileIn = new Scanner(input);
		int[][] fabric = new int[1001][1001];
		int overlaps = 0;
		while(fileIn.hasNext()) {
			String line = fileIn.nextLine();
			int beginWidth = Integer.parseInt(line.substring(line.indexOf("@") + 2, line.indexOf(",")));
			int endWidth = beginWidth + Integer.parseInt(line.substring(line.indexOf(":") + 2, line.indexOf("x")));
			int beginHeight = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(":")));
			int endHeight = beginHeight + Integer.parseInt(line.substring(line.indexOf("x") + 1));
			for(int i = beginWidth; i < endWidth; i ++) {
				for(int j = beginHeight; j < endHeight; j ++) {
					if(fabric[i][j] < 2) {
						fabric[i][j]++;
						if(fabric[i][j] == 2) {
							overlaps++;
						}
					}
				}
			}
		}
		System.out.println(overlaps);
	}

}
