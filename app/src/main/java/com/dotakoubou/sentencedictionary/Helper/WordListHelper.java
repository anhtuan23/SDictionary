package com.dotakoubou.sentencedictionary.Helper;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import com.dotakoubou.sentencedictionary.R;

import java.util.ArrayList;

/**
 * Created by dotua on 31-Dec-16.
 */

public class WordListHelper {
    static ArrayList<String> wordList = new ArrayList<>();

    public static void buildWordListFromRawText(String text) {
        wordList.clear();
        text = text.replaceAll("\\s+","");//remove all whitespace
        for (char c : text.toCharArray()) {
            wordList.add(String.valueOf(c));
        }
    }

    public static void generateWordList(Context context, Intent intent) {
        String action = intent.getAction();
        String sharedText = "";
        switch (action) {
            case Intent.ACTION_SEND:
                //receive intent share text
                if (intent.hasExtra(Intent.EXTRA_TEXT)) {
                    sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                    buildWordListFromRawText(sharedText);
                }
                break;
            case Intent.ACTION_PROCESS_TEXT:
                //receive intent share text through global long press context menu
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { //only works on Android 6.0 +
                    if (intent.hasExtra(Intent.EXTRA_PROCESS_TEXT)) {
                        sharedText = intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT);
                        buildWordListFromRawText(sharedText);
                    } else if (intent.hasExtra(Intent.EXTRA_PROCESS_TEXT_READONLY)) {
                        sharedText = intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT_READONLY);
                        buildWordListFromRawText(sharedText);
                    }
                }
                break;
            case Intent.ACTION_SEARCH:
                //receive intent from app's search view
                if (intent.hasExtra(SearchManager.QUERY)){
                    sharedText = intent.getStringExtra(SearchManager.QUERY);
                    buildWordListFromRawText(sharedText);
                }
        }
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
        for (int i : indices) {
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
