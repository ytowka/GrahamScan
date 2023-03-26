package scan;

import java.util.Comparator;

public class Sort {
    public static <T> int msort(T[] a, Comparator<T> comparator){
        return msort(a, 0, a.length, comparator);
    }

    public static <T> int msort(T[] a, int l, int r, Comparator<T> comparator){
        if(l + 1>= r) return 0;
        int m = (l+r)/2;

        int iters = 0;
        iters += msort(a, l, m, comparator);
        iters += msort(a, m, r, comparator);
        iters += mergeParts(a, l, m, r, comparator);
        return iters;
    }


    private static <T> int mergeParts(T[] a, int l, int m, int r, Comparator<T> comparator){
        int iters = 0;
        T[] c = (T[]) new Object[r-l];

        int i = l;
        int j = m;
        int k = 0;

        while(i < m && j < r){
            if(comparator.compare(a[i], a[j]) <= 0){
                c[k] = a[i];
                i++;
            }else{
                c[k] = a[j];
                j++;
            }
            k++;
        }

        while (i < m){
            c[k] = a[i];
            i++;
            k++;
        }
        while (j < r){
            c[k] = a[j];
            j++;
            k++;
        }
        iters += k;

        for (i = 0; i < c.length; i++){
            a[l+i] = c[i];
            iters++;
        }
        return iters;
    }
}
