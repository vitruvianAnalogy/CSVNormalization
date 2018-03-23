package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import entity.CSVFile;

public class CSVNormalizationTool {

	public static void main(String[] args) {

		if (args[0].isEmpty()) {
			System.err.println("No filename specified");
			return;
		}

		CSVFile csvFile = new CSVFile(args[0]);
		if (csvFile.isDirectory() || !csvFile.exists()) {
			System.err.println("Invalid file");
		} else {

			// Populate into CSV data structure and transform

			// Export into CSV file
		}
	}
}
