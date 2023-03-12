package scan;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Visualizer {

    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(DataGenerator.MIN_COORD-1000, DataGenerator.MAX_COORD+1000);
        StdDraw.setYscale(DataGenerator.MIN_COORD-1000, DataGenerator.MAX_COORD+1000);
        StdDraw.setPenColor(Color.black);
        StdDraw.enableDoubleBuffering();

        File data = new File("data/size100.txt");
        Scanner in = new Scanner(data);

        int n = Integer.parseInt(in.nextLine());
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            String[] coords = in.nextLine().split(":");
            points[i] = new Point(Float.parseFloat(coords[0]),Float.parseFloat(coords[1]));
        }

        ArrayList<Point> userPoints = new ArrayList<>();
        StdDraw.setOnMouseClick(((x, y) -> {
            userPoints.add(new Point((float) x, (float) y));
            Point[] userPointArray = userPoints.toArray(new Point[0]);
            StdDraw.clear();
            drawPoints(userPointArray);
            if(userPoints.size() >= 3){
                long startTime = System.currentTimeMillis();
                Stack<Point> hull = Scan.scan(userPointArray);
                long delta = System.currentTimeMillis() - startTime;
                draw(hull);
                StdDraw.text(100, 100, "time: "+delta+"ms");
            }
            StdDraw.show();
        }));

        Stack<Point> hull = Scan.scan(points);

        drawPoints(points);
        draw(hull);
        StdDraw.show();
    }

    private static void drawPoints(Point[] points){
        StdDraw.setPenColor(Color.BLACK);
        for (Point p : points) {
            StdDraw.filledCircle(p.x(), p.y(), 50);
        }
    }

    private static void draw(Stack<Point> hull){
        Point first = hull.peak();
        Point last = hull.peak();
        StdDraw.setPenColor(Color.RED);

        while (!hull.isEmpty()) {
            Point next = hull.pop();
            StdDraw.line(last.x(), last.y(), next.x(), next.y());
            last = next;
        }
        StdDraw.line(last.x(), last.y(), first.x(), first.y());
    }
}
