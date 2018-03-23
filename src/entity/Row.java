package entity;

import java.util.ArrayList;
import java.util.List;

public class Row {
	private String timestamp;
	private String zipCode;
	private String name;
	private String address;
	private String fooDuration;
	private String barDuration;
	private String totalDuration;
	private String notes;
	
	private static final char QUOTE = '"';
	private static final char SEPARATOR = ',';
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		//TODO
	}
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		//TODO
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		//TODO
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		//TODO
	}
	
	public String getFooDuration() {
		return fooDuration;
	}
	public void setFooDuration(String fooDuration) {
		//TODO
	}
	
	public String getBarDuration() {
		return barDuration;
	}
	public void setBarDuration(String barDuration) {
		//TODO
	}
	
	public String getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(String totalDuration) {
		//TODO
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		//TODO
	}
	public void parseAndStoreLine(String line) {
		
		if (line == null || line.isEmpty()){
			System.err.println("Line is empty. Skipping to next line");
			return;
		}
		
		List<String> columnSet = parseLine(line);
		setTimestamp(columnSet.get(0));
		setAddress(columnSet.get(1));
		setZipCode(columnSet.get(2));
		setName(columnSet.get(3));
		setFooDuration(columnSet.get(4));
		setBarDuration(columnSet.get(5));
		//setTotalDuration(columnSet.get(6));
		setNotes(columnSet.get(7));
		
	}
	private List<String> parseLine(String line) {
		
		List<String> result = new ArrayList<String>();
		boolean insideQuotes = false;
		StringBuffer current = new StringBuffer();

		for(char ch : line.toCharArray()){
			if(insideQuotes){
				if(ch==QUOTE){
					insideQuotes = false;
					current.append(ch);
				} else {
					current.append(ch);
				}
			} else {
				if(ch==QUOTE){
					insideQuotes = true;					
				} else if(ch==SEPARATOR){
					result.add(current.toString());
					current = new StringBuffer();
				}else {
					current.append(ch);
				}
			}
		}
		result.add(current.toString());
		
		return result;
	}
}
