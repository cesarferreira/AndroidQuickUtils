package quickutils.core.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Simple Utility class that runs fading animations on specified views. Fade animations are run
 * and the views are added, removed, shown, or hidden accordingly.
 */
public class Fade {

    private boolean durationSet = false;
    private long duration = 300;

    /**
     * All future calls to Fade operations will use this new duration value.
     *
     * @param duration The length of time, in milliseconds, that fade animations will
     *                 run. This value cannot be less than zero.
     */
    public void setDuration(long duration) {
        if (duration >= 0) {
            durationSet = true;
            this.duration = duration;
        }
    }

    /**
     * Fades out the given view and then removes in from its parent.
     * If the view is not currently parented, the method simply returns without doing anything.
     *
     * @param view The view that will be faded and removed.
     */
    public void remove(final View view) {
        if (view.getParent() instanceof ViewGroup) {
            final ViewGroup parent = (ViewGroup) view.getParent();
            if (durationSet) {
                view.animate().setDuration(duration);
            }
            view.animate().alpha(0f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    parent.removeView(view);
                    view.setAlpha(1);
                    view.animate().setListener(null);
                }
            });
        }
    }

    /**
     * Adds a view to the specified parent (adding it to the end of the child list in that
     * parent, equivalent to calling addView(view)) and then fades in the view. The view must
     * not currently be parented - if its parent is not null, the method returns without
     * doing anything.
     *
     * @param view   The view to be added
     * @param parent The parent to which the view is added
     */
    public void add(final View view, final ViewGroup parent) {
        add(view, parent, parent.getChildCount());
    }

    /**
     * Adds a view to the specified parent at the specified index in the parent's
     * child list and then fades in the view. The view must
     * not currently be parented - if its parent is not null, the method returns without
     * doing anything.
     *
     * @param view   The view to be added
     * @param parent The parent to which the view is added
     * @param index  The index at which the view is added in the parent's child list
     */
    public void add(final View view, final ViewGroup parent, int index) {
        if (view.getParent() == null) {
            view.setAlpha(0);
            parent.addView(view, index);
            if (durationSet) {
                view.animate().setDuration(duration);
            }
            view.animate().alpha(1);
        }
    }

    /**
     * Fades out the specified view and then sets its visibility to the specified
     * value (either View.INVISIBLE or View.GONE). If the view is not currently visibile, the
     * method will return without doing anything.
     *
     * @param view       The view to be hidden
     * @param visibility The value to which the view's visibility will be set after it fades out.
     *                   Must be either View.VISIBLE or View.INVISIBLE.
     */
    public void hide(final View view, final int visibility) {
        if (view.getVisibility() == View.VISIBLE && (visibility == View.INVISIBLE || visibility == View.GONE)) {
            hideNaughtily(view, visibility);
        }
    }

    /**
     * Fades out the specified view and then sets its visibility to the specified
     * value (either View.INVISIBLE or View.GONE). This is a naughty method as it allows you
     * to animate from INVISIBLE - GONE or vice versa (which makes sense in some parallel universe).
     *
     * @param view       The view to be hidden
     * @param visibility The value to which the view's visibility will be set after it fades out.
     */
    public void hideNaughtily(final View view, final int visibility) {
        if (durationSet) {
            view.animate().setDuration(duration);
        }
        view.animate().alpha(0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setAlpha(1);
                view.setVisibility(visibility);
                view.animate().setListener(null);
            }
        });
    }

    /**
     * Sets the visibility of the specified view to View.VISIBLE and then fades it in. If the
     * view is already visible, the method will return without doing anything.
     *
     * @param view The view to be faded in
     */
    public void show(final View view) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setAlpha(0);
            view.setVisibility(View.VISIBLE);
            if (durationSet) {
                view.animate().setDuration(duration);
            }
            view.animate().alpha(1);
        }
    }
}
