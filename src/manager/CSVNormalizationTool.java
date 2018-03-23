package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import entity.CSVFile;
import entity.Row;

public class CSVNormalizationTool {

	private static final String SEPARATOR = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

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

			// Export CSV File
			FileWriter fWriter = null;

			try {
				fWriter = new FileWriter(args[1]);
				fWriter.append(csvFile.getHeaderLine());
				fWriter.append(NEW_LINE_SEPARATOR);

				for (Row row : csvFile.getRows()) {
					fWriter.append(String.valueOf(row.getTimestamp()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getAddress()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getZipCode()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getName()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getFooDuration()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getBarDuration()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getTotalDuration()));
					fWriter.append(SEPARATOR);
					fWriter.append(String.valueOf(row.getNotes()));
					fWriter.append(NEW_LINE_SEPARATOR);
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			} finally {
				try {
					fWriter.flush();
					fWriter.close();
				} catch (IOException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
}
