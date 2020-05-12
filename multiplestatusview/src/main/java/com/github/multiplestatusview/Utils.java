package com.github.multiplestatusview;

import java.util.List;
import java.util.Set;

/**
 * @author: houtrry
 * @date: 2020/5/12 15:07
 * @version: $
 * @description:
 */
public class Utils {

    public static boolean isEmpty(Set list) {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isEmpty(T[] list) {
        return list == null || list.length == 0;
    }
}
