package edu.ncku.kidly;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CountChart {

	public CountChart() {
	}
	
	/*
	 *Read the inputFile,and change its format.
	 *Then put it into the string buffer and return.
	 *
	 *2012-10-24 DottoPing
	*/
	public String readFile(String fileName, String fmt) {

		StringBuffer inputFileBuffer = new StringBuffer();

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fileInputStream, fmt));
			String line = "";

			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					int c = line.charAt(0);
					if (c == 65279) {
						line = line.substring(1, line.length());
					}
				}
				inputFileBuffer.append(line + "\r\n");
			}
			fileInputStream.close();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return inputFileBuffer.toString();

	}

	/*
	 *Split the inputFile string into a 2-dimension string array.
	 *
	 *2012-10-24 Mr.Lucas
	*/
	public String[][] cutLine(String inputFileString) {

		String[][] result;
		String[] temp;
		
		temp = inputFileString.split("\n");
		result = new String[temp.length][];
		
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i].split(",");
		}
		return result;

	}
	
	/*
	 *Print out the result.
	 *
	 *2012-10-24 DottoPing
	*/
	private void printResult(String[][] result) {

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.print("\n");
		}

	}

	public static void main(String[] args) {

		CountChart countChart = new CountChart();
		String inputFileString = countChart.readFile("test1.txt", "Big5");
		String[][] result = countChart.cutLine(inputFileString);
		countChart.printResult(result);

	}
}