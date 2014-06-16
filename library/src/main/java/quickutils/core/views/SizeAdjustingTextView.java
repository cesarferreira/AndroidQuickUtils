package quickutils.core.views;

import android.content.Context;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 *
 * SizeAdjustingTextView is a TextView that adjusts text size
 *  depending if or if not it will fit in the view bounds.
 * Copyright (C) 2014 Elliott Chenger
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @author Elliott Chenger
 * @since Jan 18, 2014
 * @author Chase Colburn
 * @since Apr 4, 2011
 *
 */
public class SizeAdjustingTextView extends TextView{

    // Minimum text size for this text view
    public static final float MIN_TEXT_SIZE = 20;

    // Our ellipse string
    private static final String mEllipsis = "...";

    // Flag for text and/or size changes to force a resize
    private boolean mNeedsResize = false;

    // Text size that is set from code. This acts as a starting point for resizing
    private float mTextSize;

    // Temporary upper bounds on the starting text size
    private float mMaxTextSize = 0;

    // Lower bounds for text size
    private float mMinTextSize = MIN_TEXT_SIZE;

    // Text view line spacing multiplier
    private float mSpacingMult = 1.0f;

    // Text view additional line spacing
    private float mSpacingAdd = 0.0f;

    // Add ellipsis to text that overflows at the smallest text size
    private boolean mAddEllipsis = true;

    // Default constructor override
    public SizeAdjustingTextView(Context context) {
        this(context, null);
    }

    // Default constructor when inflating from XML file
    public SizeAdjustingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // Default constructor override
    public SizeAdjustingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTextSize = getTextSize();
    }

    @Override
    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
        mNeedsResize = true;
        // Since this view may be reused, it is good to reset the text size
        resetTextSize();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        resizeText();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            mNeedsResize = true;
        }
    }


    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        mTextSize = getTextSize();
    }

    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        mTextSize = getTextSize();
    }

    @Override
    public void setLineSpacing(float add, float mult) {
        super.setLineSpacing(add, mult);
        mSpacingMult = mult;
        mSpacingAdd = add;
    }

    public void setMaxTextSize(float maxTextSize) {
        mMaxTextSize = maxTextSize;
        requestLayout();
        invalidate();
    }

    public float getMaxTextSize() {
        return mMaxTextSize;
    }

    public void setMinTextSize(float minTextSize) {
        mMinTextSize = minTextSize;
        requestLayout();
        invalidate();
    }

    public float getMinTextSize() {
        return mMinTextSize;
    }

    public void setAddEllipsis(boolean addEllipsis) {
        mAddEllipsis = addEllipsis;
    }

    public boolean getAddEllipsis() {
        return mAddEllipsis;
    }

    public void resetTextSize() {
        if(mTextSize > 0) {
            super.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            mMaxTextSize = mTextSize;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(changed || mNeedsResize) {
            int widthLimit = (right - left) - getCompoundPaddingLeft() - getCompoundPaddingRight();
            int heightLimit = (bottom - top) - getCompoundPaddingBottom() - getCompoundPaddingTop();
            resizeText(widthLimit, heightLimit);
        }
        super.onLayout(changed, left, top, right, bottom);
    }


    public void resizeText() {
        int heightLimit = getHeight() - getPaddingBottom() - getPaddingTop();
        int widthLimit = getWidth() - getPaddingLeft() - getPaddingRight();
        resizeText(widthLimit, heightLimit);
    }

    public void resizeText(int width, int height) {
        CharSequence text = getText();
        if(text == null || text.length() == 0 || height <= 0 || width <= 0 || mTextSize == 0) {
            return;
        }
        float newTextSize = findNewTextSize(width, height, text);
        changeTextSize(newTextSize);
        mNeedsResize = false;
    }

    private void changeTextSize(float newTextSize) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX,newTextSize);
        setLineSpacing(mSpacingAdd, mSpacingMult);
    }

    private float findNewTextSize(int width, int height, CharSequence text) {
        TextPaint textPaint = new TextPaint(getPaint());

        float targetTextSize = textPaint.getTextSize();

        int textHeight = getTextHeight(text, textPaint, width, targetTextSize);
        while(textHeight > height && targetTextSize > mMinTextSize) {
            targetTextSize = Math.max(targetTextSize - 1, mMinTextSize);
            textHeight = getTextHeight(text, textPaint, width, targetTextSize);
        }
        return targetTextSize;
    }

    private int getTextHeight(CharSequence source, TextPaint paint, int width, float textSize) {
        paint.setTextSize(textSize);
        StaticLayout layout = new StaticLayout(source, paint, width, Alignment.ALIGN_NORMAL, mSpacingMult, mSpacingAdd, true);
        return layout.getHeight();
    }

}