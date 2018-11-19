package ru.atom;

/**
 * In this assignment you need to implement the following util methods.
 * Note:
 * throw new UnsupportedOperationException(); - is just a stub
 */
public class Util {


    /**
     * Returns the greatest of {@code int} values.
     *
     * @param values an argument. Assume values.length > 0.
     * @return the largest of values.
     */
    public static int max(int[] values) {
        int _max=values[0];
        for (int _i:values) {
            if (_max<_i) _max=_i;
        }
        return _max;
//        throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of all {@code int} values.
     *
     * @param values an argument. Assume values.length > 0.
     * @return the sum of all values.
     */
    public static long sum(int[] values) {
        long _sum=0;
        for (int _s:values) {
            _sum=_sum+_s;
        }
        return _sum;
//        throw new UnsupportedOperationException();
    }


}
