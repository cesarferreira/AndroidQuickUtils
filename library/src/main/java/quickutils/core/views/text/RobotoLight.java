package quickutils.core.views.text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoLight extends TextView {

	public RobotoLight(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public RobotoLight(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RobotoLight(Context context) {
		super(context);
		init();
	}

	public void init() {

		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
		setTypeface(tf, 1);

	}
}