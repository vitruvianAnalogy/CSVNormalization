package entity;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
	public void setTimestamp(String timestamp) throws ParseException {
		SimpleDateFormat pstFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss a");
		pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		Date date = pstFormat.parse(timestamp);
		
		SimpleDateFormat estFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		estFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		this.timestamp = estFormat.format(date);
	}
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		if (zipCode.length()<5){
			zipCode = String.format("%5s", Integer.parseInt(zipCode)).replace(" ", "0"); 
		}
		this.zipCode = zipCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.toUpperCase();
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		//Check if invalid UTF-8
		if(isValidUnicode(address)){
			this.address = address;
		} else {
			this.address = fixInvalidString(address);
		}
	}
	
	public String getFooDuration() {
		return fooDuration;
	}
	public void setFooDuration(String fooDuration) {
		this.fooDuration = convertTimeToSeconds(fooDuration);
	}
	
	public String getBarDuration() {
		return barDuration;
	}
	public void setBarDuration(String barDuration) {
		this.barDuration = convertTimeToSeconds(barDuration);
	}
	
	public String getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(String fooDuration, String barDuration) {
		this.totalDuration = new BigDecimal(getFooDuration()).add(new BigDecimal(getBarDuration())).toString();
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		//Check if invalid UTF-8
		if(isValidUnicode(notes)){
			this.notes = notes;
		} else {
			this.notes = fixInvalidString(notes);
		}
	}
	public void parseAndStoreLine(String line) throws ParseException {
		
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
		setTotalDuration(columnSet.get(4), columnSet.get(5));
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
	
	public String convertTimeToSeconds(String time){
		int startIndex = 0;
		int colonIndex = time.indexOf(":");
		int hour = Integer.parseInt(time.substring(startIndex, colonIndex ));
		
		startIndex = colonIndex;
		colonIndex = time.indexOf(":", colonIndex+1);
		int minutes = Integer.parseInt(time.substring(startIndex+1, colonIndex));
		
		int dotIndex = time.indexOf(".");
		int seconds = Integer.parseInt(time.substring(colonIndex+1, dotIndex));
		
		int milliseconds = Integer.parseInt(time.substring(dotIndex+1));
		
		BigDecimal floatTime = new BigDecimal(hour*60*60);
		floatTime = floatTime.add(new BigDecimal(minutes*60));
		floatTime = floatTime.add(new BigDecimal(seconds));
		floatTime = floatTime.add(new BigDecimal(milliseconds/100));
		return floatTime.toString();
	}
	
	public boolean isValidUnicode(String line){
		//TODO
		return true;
	}
	
	public String fixInvalidString(String line){
		//TODO
		return line;
	}
}
