package quickutils.core.categories;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by cesarferreira on 28/06/14.
 */
public class collection {

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> newArrayList(int startingSize) {
        return new ArrayList<E>(startingSize);
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<E>();
    }

    public static <K, V> Hashtable<K, V> newHashTable() {
        return new Hashtable<K, V>();
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> keyType) {
        return new EnumMap<K, V>(keyType);
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Class<E> keyType) {
        return EnumSet.noneOf(keyType);
    }

    public java.util.Collection removeDuplicates(java.util.Collection c) {
        java.util.Collection result = new ArrayList();

        for (Object o : c) {
            if (!result.contains(o)) {
                result.add(o);
            }
        }

        return result;
    }

    public static <T> boolean isACollection(T list) {
        if (list == null) return false;

        boolean bool = false;

        try {
            T t = (T) list.getClass();
            T s = (T) ((List<?>) list).get(0);
            bool = true;
        } catch (ClassCastException e) {
        }

        return bool || list.getClass().isArray();

    }
}
