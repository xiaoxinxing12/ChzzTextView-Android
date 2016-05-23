package org.chzz.textview.animatetext;
import android.graphics.Canvas;
import android.util.AttributeSet;

import org.chzz.textview.CHZZTextView;

/**
 * interface used in HTextView
 * Created by hanks on 15-12-14.
 */
public interface IHText {
    void init(CHZZTextView CHZZTextView, AttributeSet attrs, int defStyle);
    void animateText(CharSequence text);
    void onDraw(Canvas canvas);
    void reset(CharSequence text);
}
