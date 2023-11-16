package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insertWord(String word, String meaning) {
        if (word == null) {
            throw new IllegalArgumentException("Null diagnoses entries are not valid.");
        }
        // start at root of the tree
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
            // mark this node as a complete word
        node.isEndOfWord = true;
        node.meaning = meaning;
    }

    public void deleteWord(String word) {
        TrieNode node = this.getNode(word);
        if (node == null) {
            return;
        }
        node.isEndOfWord = false;
        node.meaning = null;
    }

    public void showAllWords(){
        List<String> words = this.allWords();
        for (String word: words) {
            TrieNode node = this.getNode(word);
            String meaning = node.getMeaning();
            System.out.printf("%-20s %30s", word, meaning);
            System.out.println();
        }
    }

    public List<String> suggestWord(String word) {
        TrieNode node = this.getNode(word);
        List<String> listSuggest = new ArrayList<>();
        if (node == null) {
            return listSuggest;
        }
        dfs(node, word, listSuggest);
        return listSuggest;
    }

    public void dfs(TrieNode node, String cur, List<String> res) {
        if (node.isEndOfWord) {
            res.add(cur);
        }
        for (Map.Entry<Character, TrieNode> map : node.children.entrySet()) {
            cur += map.getKey();
            dfs(map.getValue(), cur, res);
            cur = cur.substring(0, cur.length() - 1);
        }
    }

    public List<String> allWords() {
        List<String> res = new ArrayList<>();
        dfs(root,"", res);
        return res;
    }

    public boolean haveWord(String word) {
        TrieNode node = this.getNode(word);
        if (node == null) {
            return false;
        }
        return node.isEndOfWord;
    }

    public TrieNode getNode(String word) {
        // start at root of the tree
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return null;
            }
            node = node.children.get(c);
        }
        return node;
    }
}
