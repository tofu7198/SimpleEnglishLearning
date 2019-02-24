package EnglishDict;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class testingBug {
	public static int count;
	
	public static void main (String[] args) {
		ReviewDirectory rd = new ReviewDirectory();
		try {
			rd.setListFromInput();
		}
		catch(Exception e) {
			
		}
		
		count = 0;
		rd.getDatesList().get(count).setDate(new Date());
		try {
		rd.updateFile(); }
		catch(Exception e) {
			
		}
	}
}