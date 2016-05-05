package com.cezia.knowledgeoflife;

import android.os.Build;

import java.util.Objects;

class BookMark {
    private int numPart;
    private int numFragment;
    private String strFragment;
    private int positionScrollY;
    private int positionScrollX;

    BookMark() {
        clearBookMark();
    }

    BookMark(BookMark bookMark) {
        numPart = bookMark.getNumPart();
        numFragment = bookMark.getNumFragment();
        strFragment = bookMark.getStrFragment();
        positionScrollY = bookMark.getPositionScrollY();
        positionScrollX = bookMark.getPositionScrollX();
    }

    BookMark(int numPart, int numFragment) {
        this.numPart = numPart;
        this.numFragment = numFragment;
        this.strFragment = "";
    }

    BookMark(int numPart, String strFragment) {
        this.numPart = numPart;
        this.numFragment = 0;
        this.strFragment = strFragment;
    }

    private void clearBookMark() {
        numPart = 0;
        numFragment = 0;
        strFragment = "";
        positionScrollX = 0;
        positionScrollY = 0;
    }

    boolean equalsStrFragment(BookMark bookMark){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.equals(this.strFragment, bookMark.getStrFragment());
        }else {
            return this.strFragment == bookMark.getStrFragment();
        }
    }

    String getStrFragment() {
        return strFragment;
    }

    void setStrFragment(String strFragment) {
        this.strFragment = strFragment;
    }

    int getNumPart() {
        return numPart;
    }

    void setNumPart(int numPart) {
        this.numPart = numPart;
    }

    int getNumFragment() {
        return numFragment;
    }

    void setNumFragment(int numFragment) {
        this.numFragment = numFragment;
    }

    private int getPositionScrollY() {
        return positionScrollY;
    }

    public void setPositionScrollY(int positionScrollY) {
        this.positionScrollY = positionScrollY;
    }

    private int getPositionScrollX() {
        return positionScrollX;
    }

    public void setPositionScrollX(int positionScrollX) {
        this.positionScrollX = positionScrollX;
    }
}
