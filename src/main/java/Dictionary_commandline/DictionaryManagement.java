package Dictionary_commandline;

import trie.*;

import java.io.*;
import java.util.List;

public class DictionaryManagement {
    private Trie dictionary;

    public DictionaryManagement() {
        dictionary = new Trie();
    }

    public Trie getDictionary() {
        return dictionary;
    }

    public void insertWordFromFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);
            //store first value of english word
            String word = buf.readLine();
            word = word.replace("|" , "");
            String line;
            while ((line = buf.readLine()) != null) {
                // initialize meaning
                String meaning = line + "\n";
                String temp = word;
                while ((line = buf.readLine()) != null) {
                    if (!line.startsWith("|")) meaning += line + "\n";
                    else {
                        word = line.replace("|" , "");
                        break;
                    }
                }
                dictionary.insertWord(temp.toLowerCase().trim(), meaning);
            }
            // close file
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void exportToFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter buf = new BufferedWriter(fileWriter);
            // write to file from current dictionary
            List<String> words = dictionary.allWords();
            for (String word : words) {
                TrieNode node = dictionary.getNode(word);
                buf.write("|" + word.toLowerCase() + "\n" + node.getMeaning().toLowerCase());
                buf.newLine();
            }
            buf.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void insertWordByUser(String word, String meaning) {
        dictionary.insertWord(word, meaning);
    }

    public void updateWord(String word, String meaning) {
        TrieNode node = dictionary.getNode(word);
        node.setMeaning(meaning);
    }

    public void deleteWord(String word) {
        dictionary.deleteWord(word);
    }

    public boolean searchWord(String word) {
        return dictionary.haveWord(word);
    }

    public void showAllWords() {
        dictionary.showAllWords();
    }

    public void showSuggest(String word) {
        List<String> suggestList = dictionary.suggestWord(word);
        for (String s : suggestList) {
            System.out.println(s);
        }
    }
    public List<String> getSuggest(String word) {
        return dictionary.suggestWord(word);
    }

    public List<String> getAllWords() {
        return dictionary.allWords();
    }
    public String getWordMeaning(String word) {
        TrieNode node = dictionary.getNode(word);
        if (node != null) {
            return node.getMeaning();
        }
        return "Word not found";
    }
    public void playGame() {
        System.out.println("Nothin here...wait to next update...");
    }
}


