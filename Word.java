package EnglishDict;

import java.io.File;

public class Word {
    /*
    O day 1 tu trong bo tu dien duoc nhap vao duoi dang String "name#description#learned" (ten + nghia + kiem tra tu da hoc hay chua)
     */

    protected String input = new String("UTF-8"); // nhap vao
    protected String name; // ten
    protected String description; // giai nghia
    protected boolean learned;
    Word() {
    	this.learned = false;
    }
    
    Word(String input) {
        this.input = input;
        this.learned = false;
    }

    Word(String name, String description) {
        this.name = name;
        this.description = description;
        this.learned = false;
    }

    public void setLearned(boolean l) {
    	learned = l;
    }
    
    public boolean isLearned() {
    	return this.learned;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getDescription() {
        return description;
    }

    public String getInput() {
        return input;
    }

    public String getName() {
        return name;
    }

    public void splitInput() { // tach string input thanh name va description
        String[] split = this.input.split("#");
        
        this.name = split[0];
        
        this.description = split[1];
        String tmp = split[2];
        if (tmp.equals("true")) 
        	this.learned = true;
        else this.learned = false;
    }

    public String display() { // hien thi vao string moi
        return this.name + "#" + this.description + "#" + this.learned;
    } // hien thi

    public void update() { //cap nhat chinh sua
        this.input = this.name + "#" + this.description + "#" + this.learned;
    }
}