package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    protected boolean isEndOfWord;
    private boolean isSaved;
    protected String meaning;
    protected Map<Character, TrieNode> children;
    public TrieNode() {
        isEndOfWord = false;
        children = new HashMap<>();
        meaning = null;
        isSaved = false;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public boolean getIsSaved() {
        return this.isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

}