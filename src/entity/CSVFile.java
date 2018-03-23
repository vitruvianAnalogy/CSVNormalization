package entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CSVFile extends File{
	
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

}
