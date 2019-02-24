package EnglishDict;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ReviewDirectory extends Directory{
	/*
	 * Bo tu dien nay su dung nham dieu khien cac tu da duoc hoc 
	 * > Su dung 1 file chung review.dat
	 * > Mot tu se duoc quan ly duoi dang "ten#nghia#kt da hoc - ngay(dd/MM/yyyy HH:mm")
	 */
	
	/*
	 * File path tro den thang file review.dat
	 */
	protected File path; 
	
	protected static ArrayList<WordWithDate> dates;
	
	ReviewDirectory() {
		path = new File("Sources/data/review.dat");
		words = new ArrayList<>();
		dates = new ArrayList<>();
	}
	/*
	 * Tra ve danh sach tu trong class
	 */
	public ArrayList<WordWithDate> getDatesList() {
		return ReviewDirectory.dates;
	}
	
	/*
	 * Ham sort cac tu theo thoi gian
	 */
	public void sort() {

        Collections.sort(dates, new Comparator<WordWithDate>() {
			@Override
			public int compare(WordWithDate o1, WordWithDate o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
       
	}
	
	/*
	 * Ham cap nhat file
	 * > Doc file vao bang printwriter sau do ghi lai
	 * > formatter de dinh dang kieu ghi ngay
	 * > lineSeparator = \n (xuong dong)
	 */
	@Override
	public void updateFile() throws IOException {				
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			PrintWriter bw = new PrintWriter(path, "UTF-8");
			for (int i = 0; i < dates.size(); i++) {
				bw.write(this.dates.get(i).display() + " - " + formatter.format(this.dates.get(i).getDate()) + System.lineSeparator());
			}
			bw.close();
		}
		catch (Exception exc) {
			System.out.println("cant write");
		}
	}
	
	/*
	 * Loai bo tu trung lap (bug)
	 * > Bat dau tu 1 tu va kiem tra xem trong cac tu con lai co tu trung khong
	 */
	public void removeDuplicate() {
		for (int i = 0; i < dates.size() - 1; i++) {
			for (int j = i + 1; j < dates.size(); j++) {
				if (dates.get(i).getName().equals(dates.get(j).getName())) {					
					dates.remove(j);
				}
			}
		}
	}
	
	/*
	 * Lay cac dong trong file va cai dat cac thuoc tinh cua cac tu trong class
	 * > Doc 1 dong, sau do split ra thanh 2 phan tu va ngay
	 */
	public void setListFromInput() throws IOException {
        String currentLine;
        
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        
        while ((currentLine = br.readLine()) != null) {
        	WordWithDate tempWord = new WordWithDate();
    		
        	String[] split = currentLine.split(" - ");
        	
        	if (!Character.isLetter(split[0].charAt(0))) {
        		split[0] = split[0].substring(1);
        	}
        	
            tempWord.setInput(split[0]);
            try {
            	tempWord.setDate(formatter.parse(split[1]));
            }
            catch(Exception e) {
            	System.out.println("Can't parse to string!");
            }
            tempWord.splitInput();
            
            dates.add(tempWord);
       }
       sort();
       removeDuplicate();
       updateFile();       
       br.close();
	}
}
