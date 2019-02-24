package EnglishDict;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DirectoriesControl {
    /*
    Bo dieu khien Directory (bo tu dien)
     */
    protected ArrayList<Directory> directories = new ArrayList<>();

    protected Directory selectedDirectory = new Directory();
    
    public void setSelected(Directory set) {
    	this.selectedDirectory = set;
    }
    
    public Directory getSelected() {
    	return this.selectedDirectory;
    }
    
    public ArrayList<Directory> getDirectories() {
        return directories;
    }

    public void addNew(Directory directory) { // them bo tu moi
        this.directories.add(directory);
    }

    public void delete(Directory directory) { // xoa bo tu
        if (directories.contains(directory)) directories.remove(directory);
    }
    
    /*
     * MERGE 2 BO TU A, B THANH BO TU A
     * > doc vao 2 collection xong ghep vao 1 file moi tao
     */
    public void mergeTwo(Directory directory1, Directory directory2, String newFileName) throws IOException{ 
        Directory merge = new Directory();
        ArrayList<Word> dir1 = directory1.getWordsList(), dir2 = directory2.getWordsList();
        File merge_2 = new File("Sources/" + newFileName + ".dat");
        
        merge.setPath(merge_2.getPath());
        
        PrintWriter writer = new PrintWriter(merge_2.getPath(), "UTF-8");

        for (int i = 0; i < dir1.size(); i++) {
            writer.println(dir1.get(i).getInput());
        }

        for (int i = 0; i < dir2.size(); i++) {
            writer.println(dir2.get(i).getInput());
        }

        writer.close();
        addNew(merge);
    }
    /*
     * // load toan bo cac file txt vao thanh cac bo tu + load lai toan bo cac bo tu
     */
    public void loadDirectories(File[] listOfFiles) throws IOException { 
        this.directories.clear();
        for (final File directory: listOfFiles){
            if (directory.isFile()) {
                Directory newDir = new Directory();
                newDir.setName(directory.getName().replace(".dat", ""));
                newDir.setPath(directory.getPath());
                addNew(newDir);
            }
        }

        for (int i = 0; i < directories.size(); i++){
            directories.get(i).setWordsInput(directories.get(i).getPath());
            directories.get(i).putInputIntoWords();
        }
    }

    
    /*public void loadDirectory(File directory) throws IOException {
    	if (directory.isFile()) {
    		Directory newDir = new Directory();
    		newDir.setName(directory.getName().replace(".dat", ""));
    		newDir.setPath(directory.getPath());
    		addNew(newDir);
    		directories.get(directories.size() - 1).setWordsInput(newDir.getPath());
    		directories.get(directories.size() - 1).putInputIntoWords();
    	}
    }*/
    
    
    /*
     * Lay INDEX cua Mang WORD
     */
    protected int allWordsIndex = -1;
    
    public int getAllWordsIndex() {
    	return this.allWordsIndex;
    }
    
    /*
     * Lay toan bo cac WORD co san trong cac COLLECTION
     */
    
    public ArrayList<Word> getAllWords() {
    	ArrayList<Word> temp = new ArrayList<>();
    	
    	for (int i = 0; i < this.directories.size(); i++) {
    		temp.addAll(this.directories.get(i).getWordsList());
    	}
    	
    	return temp;
    }
}
