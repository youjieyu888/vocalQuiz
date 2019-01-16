package edu.gatech.seclass.sdpvocabquiz;

public class Practice {
    private boolean allUsed;
    private Word randomWord;
    private String choices;
    private boolean correct;
    private String percentage;

    public Practice(boolean allUsed, Word randomWord, String choices, boolean correct) {
        this.allUsed = allUsed;
        this.randomWord = randomWord;
        this.choices = choices;
        this.correct = correct;
    }

    public void setAllUsed(boolean allUsed) {
        this.allUsed = allUsed;
    }

    public void setRandomWord(Word randomWord) {
        this.randomWord = randomWord;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public boolean isAllUsed() {
        return allUsed;
    }

    public Word getRandomWord() {
        return randomWord;
    }

    public String getChoices() {
        return choices;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getPercentage() {
        return percentage;
    }
}
