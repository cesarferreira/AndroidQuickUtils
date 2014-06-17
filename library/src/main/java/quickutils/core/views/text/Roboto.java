package quickutils.core.views.text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class Roboto extends TextView {

	public Roboto(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Roboto(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Roboto(Context context) {
		super(context);
		init();
	}

	public void init() {

		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto.ttf");
		setTypeface(tf, 1);

	}
}