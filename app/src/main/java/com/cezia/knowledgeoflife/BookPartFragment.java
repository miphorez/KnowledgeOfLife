package com.cezia.knowledgeoflife;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class BookPartFragment extends Fragment {
    private boolean flRestoreView;
    private boolean flRestoreMode = true;

    private int lastBookPosition;

    private BookMark bookMark;
    BookPartFont fontBookPart;
    BookPartPosition positionBookPart;

    interface ChangeBookPartListener {
        void changeBookPart(BookMark bookMark);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        flRestoreView = false;
        if (savedInstanceState != null) {
            bookMark = new BookMark();
            bookMark.setNumPart(savedInstanceState.getInt("numPart"));
            bookMark.setStrFragment(savedInstanceState.getString("strFragment"));
            flRestoreView = true;
        }
        return inflater.inflate(R.layout.fragment_book_part, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("numPart", bookMark.getNumPart());
        outState.putString("strFragment", bookMark.getStrFragment());
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView textView = getTextViewBookPart();
        if (textView != null) {
            String iStr = "<html> <b><i><sup>"+bookMark.getStrFragment()+"</sup></i></b><br>";
            iStr += EBookPart.getTextBookPart(getContext(), bookMark, true)+"</html>";
            textView.setText(Html.fromHtml(iStr));
            fontBookPart = new BookPartFont(getContext(), getTextViewBookPart());
            positionBookPart = new BookPartPosition(getContext(), getScrollViewBookPart());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        changeBookPartForListener(bookMark);
        //восстановить размер текста
        TextView textView = getTextViewBookPart();
        if (textView != null) {
            fontBookPart.setTextSize();
        }
        //восстановить позицию в тексте
        if (flRestoreMode) {
            lastBookPosition = positionBookPart.loadPrefLastBookPosition();
            final ScrollView scrollView = getScrollViewBookPart();
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, lastBookPosition);
                }
            });
        }
        flRestoreMode = true;
    }

    private void changeBookPartForListener(BookMark bookMark) {
        positionBookPart.savePrefLastPart(bookMark);
        Context context = getContext();
        ChangeBookPartListener listener = (ChangeBookPartListener) context;
        View view = getView();
        if (view != null) {
            if (listener != null) {
                listener.changeBookPart(bookMark);
            }
        }
    }

    void setBookPart(BookMark bookMark, boolean modeRestore) {
        this.bookMark = new BookMark(bookMark);
        this.flRestoreMode = modeRestore;
    }

    BookMark getBookMark() {
        return bookMark;
    }

    boolean isFlRestoreView() {
        return flRestoreView;
    }

    void setFlRestoreView(boolean flRestoreView) {
        this.flRestoreView = flRestoreView;
    }

    private TextView getTextViewBookPart() {
        TextView textView = null;
        View view = getView();
        if (view != null) {
            textView = (TextView) view.findViewById(R.id.book_part_view);
        }
        return textView;
    }

    private ScrollView getScrollViewBookPart() {
        ScrollView scrollView = null;
        View view = getView();
        if (view != null) {
            scrollView = (ScrollView) view.findViewById(R.id.scroll_part_view);
        }
        return scrollView;
    }

    String getBookPartText() {
        return getTextViewBookPart().getText().toString();
    }
}
