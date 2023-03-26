package scan.tests;

import org.junit.Assert;
import org.junit.Test;
import scan.Sort;

public class SortTest {

/*    public static void main(String[] args) {
        int n = 32;
        for (int i = n; i >= 1; i--) {
            System.out.print(i +", ");
        }
        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.print(i +", ");
        }
    }*/

    @Test
    public void testReverseSize8(){
        Integer[] a = new Integer[]{8, 7, 6, 5, 4, 3, 2, 1};
        int iters = Sort.msort(a, 0, a.length, Integer::compareTo);

        Integer[] expected = new Integer[]{1, 2, 3 ,4, 5, 6, 7, 8};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSize16(){
        Integer[] a = new Integer[]{16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int iters = Sort.msort(a, 0, a.length, Integer::compareTo);
        System.out.println(iters);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSize32(){
        Integer[] a = new Integer[]{32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, };
        int iters = Sort.msort(a, 0, a.length, Integer::compareTo);
        System.out.println(iters);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, };
        Assert.assertArrayEquals(expected, a);
    }


    @Test
    public void testReverseSize10(){
        Integer[] a = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Sort.msort(a, 0, a.length, Integer::compareTo);

        Integer[] expected = new Integer[]{1, 2, 3 ,4, 5, 6, 7, 8, 9, 10};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSize11(){
        Integer[] a = new Integer[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Sort.msort(a,0, a.length, Integer::compareTo);

        Integer[] expected = new Integer[]{1, 2, 3 ,4, 5, 6, 7, 8, 9, 10, 11};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSize13(){
        Integer[] a = new Integer[]{13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Sort.msort(a,0, a.length, Integer::compareTo);

        Integer[] expected = new Integer[]{1, 2, 3 ,4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Assert.assertArrayEquals(expected, a);
    }
}
