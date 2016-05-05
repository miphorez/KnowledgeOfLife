package com.cezia.knowledgeoflife;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

enum EBookPart {
    PART1_001(1, 1, "22.06.2012"),
    PART1_002(2, 1, "23.06.2012"),
    PART1_003(3, 1, "25.06.2012"),
    PART1_004(4, 1, "26.06.2012"),
    PART1_005(5, 1, "27.06.2012"),
    PART1_006(6, 1, "28.06.2012"),
    PART1_007(7, 1, "29.06.2012"),

    PART2_008(8, 2, "12.07.2012"),
    PART2_009(9, 2, "13.07.2012"),
    PART2_010(10, 2, "14.07.2012"),
    PART2_011(11, 2, "15.07.2012"),
    PART2_012(12, 2, "16.07.2012"),
    PART2_013(13, 2, "20.07.2012"),
    PART2_014(14, 2, "21.07.2012"),
    PART2_015(15, 2, "22.07.2012"),
    PART2_016(16, 2, "23.07.2012"),
    PART2_017(17, 2, "24.07.2012"),
    PART2_018(18, 2, "25.07.2012"),
    PART2_019(19, 2, "26.07.2012"),
    PART2_020(20, 2, "27.07.2012"),
    PART2_021(21, 2, "28.07.2012"),
    PART2_022(22, 2, "29.07.2012"),
    PART2_023(23, 2, "30.07.2012"),
    PART2_024(24, 2, "31.07.2012"),

    PART3_025(25, 3, "01.08.2012"),
    PART3_026(26, 3, "02.08.2012"),

    PART4_027(27, 4, "11.09.2012"),
    PART4_028(28, 4, "12.09.2012"),
    PART4_029(29, 4, "13.09.2012"),
    PART4_030(30, 4, "14.09.2012"),
    PART4_031(31, 4, "15.09.2012"),
    PART4_032(32, 4, "16.09.2012"),
    PART4_033(33, 4, "17.09.2012"),
    PART4_034(34, 4, "19.09.2012"),
    PART4_035(35, 4, "21.09.2012"),
    PART4_036(36, 4, "22.09.2012"),
    PART4_037(37, 4, "23.09.2012"),
    PART4_038(38, 4, "24.09.2012"),
    PART4_039(39, 4, "25.09.2012"),
    PART4_040(40, 4, "28.09.2012"),
    PART4_041(41, 4, "30.09.2012"),

    PART5_042(42, 5, "01.10.2012"),
    PART5_043(43, 5, "02.10.2012"),
    PART5_044(44, 5, "03.10.2012"),
    PART5_045(45, 5, "06.10.2012"),
    PART5_046(46, 5, "07.10.2012"),
    PART5_047(47, 5, "08.10.2012"),
    PART5_048(48, 5, "11.10.2012"),
    PART5_049(49, 5, "14.10.2012"),
    PART5_050(50, 5, "15.10.2012"),
    PART5_051(51, 5, "16.10.2012"),
    PART5_052(52, 5, "20.10.2012"),
    PART5_053(53, 5, "21.10.2012"),

    ;

    int bookPart;
    BookMark bookMark;

    EBookPart(int bookPart, int numPart, String strFragment) {
        this.bookPart = bookPart;
        bookMark = new BookMark();
        bookMark.setNumPart(numPart);
        bookMark.setStrFragment(strFragment);
    }

    static String getTextBookPart(Context context, BookMark bookMark, boolean htmlMode) {
        return getStringFromAssetFile(context, getFileName(bookMark), htmlMode);
    }

    private static String getFileName(BookMark bookMark) {
        return "part" + Integer.toString(bookMark.getNumPart()) + "/" +
                bookMark.getStrFragment() + ".txt";
    }

    private static String getStringFromAssetFile(Context context, String fileName, boolean htmlMode) {
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = convertStreamToString(is, htmlMode);
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static String convertStreamToString(InputStream is, boolean htmlMode) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (htmlMode)
                sb.append(line).append("<br>"); else
                sb.append(line).append("\n");
            }
            is.close();
        } catch (OutOfMemoryError | Exception om) {
            om.printStackTrace();
        }
        return sb.toString();
    }

    public static BookMark getNextBookMark(BookMark bookMark) {
        BookMark newBookMark = null;
        int lastPart = getNumBookPart(bookMark);
        if (lastPart == (EBookPart.values().length - 1)) return bookMark;
        lastPart++;
        for (EBookPart eBookPart : values()) {
            if (eBookPart.bookPart == lastPart) {
                newBookMark = new BookMark(eBookPart.bookMark);
                break;
            }
        }
        return newBookMark;
    }

    public static BookMark getPrevBookMark(BookMark bookMark) {
        BookMark newBookMark = null;
        int lastPart = getNumBookPart(bookMark);
        if (lastPart == 0) return bookMark;
        lastPart--;
        for (EBookPart eBookPart : values()) {
            if (eBookPart.bookPart == lastPart) {
                newBookMark = new BookMark(eBookPart.bookMark);
                break;
            }
        }
        return newBookMark;
    }

    private static int getNumBookPart(BookMark bookMark) {
        int lastPart = 0;
        for (EBookPart eBookPart : values()) {
            if (eBookPart.bookMark.getNumPart() == bookMark.getNumPart() &&
                    eBookPart.bookMark.equalsStrFragment(bookMark)) {
                lastPart = eBookPart.bookPart;
                break;
            }
        }
        return lastPart;
    }

    public static boolean isLastBookPart(BookMark bookMark) {
        int lastPart = getNumBookPart(bookMark);
        return lastPart == (EBookPart.values().length - 1);
    }

    public static BookMark getFirstBookPart() {
        return new BookMark(1, BookPartPosition.PREF_LAST_FRAGMENT_STR);
    }

    public static String getFirstFragment(int numPart) {
        String strFragment = "";
        for (EBookPart eBookPart : values()) {
            if (eBookPart.bookMark.getNumPart() == numPart ) {
                strFragment = eBookPart.bookMark.getStrFragment();
                break;
            }
        }
        return strFragment;
    }

    public static boolean isFirstBookPart(BookMark bookMark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return (bookMark.getNumPart() == 1 && Objects.equals(bookMark.getStrFragment(), BookPartPosition.PREF_LAST_FRAGMENT_STR));
        }else{
            return (bookMark.getNumPart() == 1 && bookMark.getStrFragment() == BookPartPosition.PREF_LAST_FRAGMENT_STR);
        }
    }
}
