package quickutils.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by cesarferreira on 28/06/14.
 */
public class collection {

    public Collection removeDuplicates(Collection c) {
        Collection result = new ArrayList();

        for (Object o : c) {
            if (!result.contains(o)) {
                result.add(o);
            }
        }

        return result;
    }
}
