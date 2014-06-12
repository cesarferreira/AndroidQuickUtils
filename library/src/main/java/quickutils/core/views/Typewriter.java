package quickutils.core.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;


/**
 * Created by cesarferreira on 11/06/14.
 */
public class Typewriter extends RobotoTextView {

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 500; //Default 500ms delay


    // usage:
    // Typewriter textView = (Typewriter) rowView.findViewById(R.id.gridItemTextView);
    // textView.setCharacterDelay(60);
    // textView.animateText(mCountries.get(position).name);

    public Typewriter(Context context) {
        super(context);
    }

    public Typewriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }
}
