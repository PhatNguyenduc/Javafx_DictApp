package Dictionary_commandline;

public class Word {
    private String word_target;
    private String word_explain;

    public Word() {}
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    public String getWord() {
        return word_target;
    }
    public void setWord(String word_target) {
        this.word_target = word_target;
    }

    public String getExplain() {
        return word_explain;
    }

    public void setExplain(String word_explain) {
        this.word_explain = word_explain;
    }

}
