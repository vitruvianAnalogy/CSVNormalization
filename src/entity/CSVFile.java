package entity;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CSVFile extends File{
	
	private int rowCounter = 1;
	private String headerLine;
	private List<Row> rows = new ArrayList<Row>();
	
	public CSVFile(String fileName) {
		super(fileName);
	}
	
	public String getHeaderLine() {
		return headerLine;
	}

	public void setHeaderLine(String headerLine) {
		this.headerLine = headerLine;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List< Row> rows) {
		this.rows = rows;
	}
	
	public void load(String line) throws ParseException {
		if(rowCounter==1){
			rowCounter++;
			headerLine = line;
		} else {
			Row row = new Row();
			row.parseAndStoreLine(line);
			rows.add( row);
		}
	}

}
