package EnglishDict;

import java.util.ArrayList;
import java.io.*;

public class Directory {
    /*
    Bo tu dien
    Duoc nhap vao duoi dang 1 file dat trong Folder Source

     */
	
    protected ArrayList<String> wordsInput = new ArrayList<>();
    protected ArrayList<Word> words = new ArrayList<>();
    protected String path;
    protected String name;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * Lay List WORD trong class
     */
    public ArrayList<Word> getWordsList() {
        return words;
    }

    /*
     * // nhap toan bo cac dong input trong file txt vao
     * > Doc file va nhap vao cac dong input, moi dong tuong ung 1 tu
     */
    public void setWordsInput(String path) throws IOException { 
        File fileDir = new File(path);
        wordsInput.clear();
        String currentLine;
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
        while ((currentLine = br.readLine()) != null) {
            this.wordsInput.add(currentLine);
        }
        br.close();
    }

    /*
     * // chuyen doi toan bo input trong bo tu dien thanh cac thanh phan word & description
     * > chuyen doi dong input thanh cac thanh phan cua tus
     */
    public void putInputIntoWords() { 
        words.clear();
        for (int i = 0; i < this.wordsInput.size(); i++) {
            Word newWord = new Word(this.wordsInput.get(i));
            this.words.add(newWord);
        }
        for (int i = 0; i < this.words.size(); i++) {
            words.get(i).splitInput();
        }
    }

    /*public void getWords() { // hien thi toan bo tu trong bo tu dien
        for (int i = 0; i < this.words.size(); i++) {
            words.get(i).splitInput();
            System.out.println(words.get(i).getName() + "\n" + words.get(i).getDescription());
        }
    }*/

    /*
     * // them tu moi vao bo tu dien
     */
    
    public void addNewWord(Word word) {
        words.add(word);
    } 
    /*public void deleteWord(String word) { // xoa tu co ten trong bo tu dien
        for(int i = 0; i < this.words.size(); i++) {
            if (words.get(i).getName().equals(word)) {
                words.remove(i);
                wordsInput.remove(i);
            }
        }
    }*/

    /*
     * // luu lai cac chinh sua
     */
    public void updateFile() throws IOException { 
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for (int i = 0; i < this.words.size(); i++) {
            writer.println(words.get(i).getName()+"#"+words.get(i).getDescription()+"#"+words.get(i).isLearned());
        }
        writer.close();
    }

}
