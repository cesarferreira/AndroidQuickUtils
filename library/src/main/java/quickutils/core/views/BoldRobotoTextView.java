package quickutils.core.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class BoldRobotoTextView extends TextView {

	public BoldRobotoTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BoldRobotoTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BoldRobotoTextView(Context context) {
		super(context);
		init();
	}

	public void init() {

		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
		setTypeface(tf, 1);

	}
}