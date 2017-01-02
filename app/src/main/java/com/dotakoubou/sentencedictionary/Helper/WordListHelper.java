package com.dotakoubou.sentencedictionary.Helper;

import android.content.Context;
import android.content.Intent;

import com.dotakoubou.sentencedictionary.R;

import java.util.ArrayList;

/**
 * Created by dotua on 31-Dec-16.
 */

public class WordListHelper {
    static ArrayList<String> wordList = new ArrayList<>();

    public static void setWordList() {
//        wordList.clear();
        WordListHelper.wordList.add("明");
        WordListHelper.wordList.add("天");
        WordListHelper.wordList.add("更");
        WordListHelper.wordList.add("残");
        WordListHelper.wordList.add("酷");
    }

    public static String getWordFromWordList(int position) {
        String c;
        if (position >= 0 && position < wordList.size()) {
            c = wordList.get(position);
        } else {
            c = " ";
        }
        return c;
    }

    public static int getWordListSize() {
        return wordList.size();
    }

    public static void sendWordIntent(Context context, int[] indices, int sendIntentID) {
        int colorDictIntentID = context.getResources().getInteger(R.integer.COLOR_DICT_INTENT_ID);
        int sendTextIntentID = context.getResources().getInteger(R.integer.SEND_TEXT_INTENT_ID);
        String sendString = "";
        for (int i: indices){
            sendString += getWordFromWordList(i);
        }
        if (!sendString.equals("")) {//only send if the string is not empty
            if (sendIntentID == colorDictIntentID) {
                Intent intent = new Intent("colordict.intent.action.SEARCH");
                intent.putExtra("EXTRA_QUERY", sendString); //Search Query
                context.startActivity(intent);
            } else if (sendIntentID == sendTextIntentID) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sendString);
                sendIntent.setType("text/plain");
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                context.startActivity(sendIntent);
            }
        }
    }
}
