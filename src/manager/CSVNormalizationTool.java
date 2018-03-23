package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

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
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					csvFile.load(line);
				}
			} catch (IOException | ParseException e) {
				System.err.println(e.getMessage());
				return;
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}

			// Export into CSV file
		}
	}
}
