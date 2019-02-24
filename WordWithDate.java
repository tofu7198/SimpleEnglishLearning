package EnglishDict;

import java.util.Date;

public class WordWithDate extends Word {
	/*
	 * Thua ke Word va co them ngay 
	 * Input co dang "input(ten#nghia#kt hoc) - ngay(dd/MM/yyyy HH:mm)"
	 */
	protected Date date;
	
	public void setDate(Date dt) {
		this.date = dt;
	}
	
	public Date getDate() {
		return this.date;
	}
}
