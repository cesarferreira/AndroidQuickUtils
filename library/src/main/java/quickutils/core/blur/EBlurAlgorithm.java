package quickutils.core.blur;

import java.util.ArrayList;
import java.util.List;

import quickutils.core.R;


/**
 * Enum of all supported algorithms
 *
 * @author pfavre
 */
public enum EBlurAlgorithm {
    RS_GAUSS_FAST(R.color.graph_bg_green), RS_BOX_5x5(R.color.graph_blue),
	RS_GAUSS_5x5(R.color.graph_bg_white), RS_STACKBLUR(R.color.graph_bg_violet),STACKBLUR(R.color.graph_bg_yellow),
	GAUSS_FAST(R.color.graph_bg_red), BOX_BLUR(R.color.graph_bg_turquoise), NONE(R.color.graph_bg_black);

	private final int colorResId;

	EBlurAlgorithm(int colorResId) {
		this.colorResId = colorResId;
	}

	public int getColorResId() {
		return colorResId;
	}

	public static List<EBlurAlgorithm> getAllAlgorithms() {
        List<EBlurAlgorithm> algorithms = new ArrayList<EBlurAlgorithm>();
        for (EBlurAlgorithm algorithm : values()) {
            if(!algorithm.equals(NONE)) {
                algorithms.add(algorithm);
            }
        }
        return algorithms;
    }
}
