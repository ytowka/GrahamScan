package scan;

import java.util.Arrays;
import java.util.Comparator;

public class Scan {

    // меньше та точка, которая находится правее этой
    public static Comparator<Point> angleComparator(Point pivot){
        return (p1, p2) -> {
            Vector a = new Vector(pivot, p1);
            Vector b = new Vector(p1, p2);

            double zProduct = a.x()*b.y() - a.y()*b.x();

            if(zProduct < 0){
                return 1;
            }else if(zProduct > 0){
                return -1;
            }
            return 0;
        };
    }


    // находим стартовую точку, точно лежащуюю в оболочке
    public static int findStart(Point[] points){

        //самая нижняя точка, точно лежит в оболочке
        double minY = points[0].y();
        int index = 0;
        for(int i = 0; i < points.length; i++){
            if (points[i].y() < minY){
                minY = points[i].y();
                index = i;
            }
        }
        return index;
    }

    public static Stack<Point> scan(Point[] points) {
        int startIndex = findStart(points);

        Point start = points[startIndex];
        points[startIndex] = points[0];
        points[0] = start;

        Arrays.sort(points, 1, points.length, angleComparator(start));

        Stack<Point> hull = new Stack<>();
        hull.push(points[0]);
        hull.push(points[1]);


        for (int i = 2; i < points.length; i++) {
            while (angleComparator(hull.peak2()).compare(hull.peak(), points[i]) >= 0){
                hull.pop();
            }
            hull.push(points[i]);
        }

        return hull;
    }
}
