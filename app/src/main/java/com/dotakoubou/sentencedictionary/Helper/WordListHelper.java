package com.dotakoubou.sentencedictionary.Helper;

import java.util.ArrayList;

/**
 * Created by dotua on 31-Dec-16.
 */

public class WordListHelper {
    static ArrayList<Character> wordList = new ArrayList<>();

    public static void setWordList() {
//        wordList.clear();
        WordListHelper.wordList.add('明');
        WordListHelper.wordList.add('天');
        WordListHelper.wordList.add('更');
        WordListHelper.wordList.add('残');
        WordListHelper.wordList.add('酷');
    }

    public static Character getWordFromWordList(int position) {
        Character c;
        if (position >= 0 && position < wordList.size()) {
            c = wordList.get(position);
        } else {
            c = 'p';
        }
        return c;
    }

    public static int getWordListSize() {
        return wordList.size();
    }
}
