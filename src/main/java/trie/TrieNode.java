package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    protected boolean isEndOfWord;
    protected String meaning;
    protected Map<Character, TrieNode> children;
    public TrieNode() {
        isEndOfWord = false;
        children = new HashMap<>();
        meaning = null;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}