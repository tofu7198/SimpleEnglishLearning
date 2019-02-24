# SimpleEnglishLearning

Just a plain, simple Java application app. :p
Using Java for code, Java Swing for UI.

# About the structures

The words and meanings are storaged in a "directory".
A "directory" is a .dat file which is contented with formatted lines: Word#ItsMeaning#isTheWordLearnedOrNot?
The word has a illustration (optional). It has the same name as the word and is stored at Sources/image/ .

There is a special "directory" named "review" for reviewing function, whose format is different: Word#Meaning#isTheWordLearnedOrNot? - LearningDate(dd/mm/yyyy hh:mm:ss)

# About the files

Word, WordWithDate, Directory and DirectoriesControl.java contents the Word and Directory input method.

MenuGUI contents the main GUI of this application. 
Other .java files content the GUI of specific function (SomethingWin.java) and other small functions for that big one. (ex. EditSelection for editting the selected directory, LearnByNum for learning words at a limit)




