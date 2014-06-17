package quickutils.core.views.text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoBold extends TextView {

	public RobotoBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public RobotoBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RobotoBold(Context context) {
		super(context);
		init();
	}

	public void init() {

		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
		setTypeface(tf, 1);

	}
}