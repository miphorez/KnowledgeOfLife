package com.cezia.knowledgeoflife;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ScrollView;

class BookPartPosition {

     private static final String APP_PREFERENCES_LAST_PART = "bookmark_part";
     private static final String APP_PREFERENCES_LAST_FRAGMENT_STR = "bookmark_fragment_str";
     private static final int PREF_LAST_PART = 1;
     static final String PREF_LAST_FRAGMENT_STR = "22.06.2012";

     private static final String APP_PREFERENCES_LAST_PART_POSITION = "lastpartposition";
     private static final int PREF_LAST_PART_POSITION = 0;

    private final ScrollView scrollView;
    private final Context context;

    BookPartPosition(Context context, ScrollView scrollView) {
        this.scrollView = scrollView;
        this.context = context;
    }

     int loadPrefLastBookPosition(){
         int pos = 0;
         SharedPreferences prefSettings = context.getSharedPreferences(BookPartFont.APP_PREFERENCES, Context.MODE_PRIVATE);
         if (prefSettings.contains(APP_PREFERENCES_LAST_PART_POSITION)) {
             pos = prefSettings.getInt(APP_PREFERENCES_LAST_PART_POSITION, PREF_LAST_PART_POSITION);
         }
         return pos;
     }

     void savePrefLastPartPosition() {
         SharedPreferences prefSettings = context.getSharedPreferences(BookPartFont.APP_PREFERENCES, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = prefSettings.edit();
         editor.putInt(APP_PREFERENCES_LAST_PART_POSITION, scrollView.getScrollY());
         editor.commit();
     }

     static BookMark loadPrefLastPart(Context context) {
         int numPart = PREF_LAST_PART;
         String strFragment = PREF_LAST_FRAGMENT_STR;
         SharedPreferences prefSettings = context.getSharedPreferences(BookPartFont.APP_PREFERENCES, Context.MODE_PRIVATE);
         if (prefSettings.contains(APP_PREFERENCES_LAST_PART)) {
             numPart = prefSettings.getInt(APP_PREFERENCES_LAST_PART, PREF_LAST_PART);
             strFragment = prefSettings.getString(APP_PREFERENCES_LAST_FRAGMENT_STR, PREF_LAST_FRAGMENT_STR);
         }
         return new BookMark(numPart, strFragment);
     }

     void savePrefLastPart(BookMark bookMark) {
         SharedPreferences prefSettings = context.getSharedPreferences(BookPartFont.APP_PREFERENCES, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = prefSettings.edit();
         editor.putInt(APP_PREFERENCES_LAST_PART, bookMark.getNumPart());
         editor.putString(APP_PREFERENCES_LAST_FRAGMENT_STR, bookMark.getStrFragment());
         editor.commit();
     }
}
