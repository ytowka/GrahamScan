package scan.visual;

import scan.Point;
import scan.Scan;
import scan.Stack;
import scan.data.DataGenerator;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Visualizer {

    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(DataGenerator.MIN_COORD-1000, DataGenerator.MAX_COORD+1000);
        StdDraw.setYscale(DataGenerator.MIN_COORD-1000, DataGenerator.MAX_COORD+1000);
        StdDraw.setPenColor(Color.black);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        ArrayList<scan.Point> userPoints = new ArrayList<>();
        StdDraw.setOnMouseClick(((x, y) -> {
            userPoints.add(new scan.Point((float) x, (float) y));
            scan.Point[] userPointArray = userPoints.toArray(new scan.Point[0]);
            StdDraw.clear();
            drawPoints(userPointArray);
            if(userPoints.size() >= 3){
                long startTime = System.currentTimeMillis();
                Stack<scan.Point> hull = Scan.scan(userPointArray).points();
                long delta = System.currentTimeMillis() - startTime;
                draw(hull);
                StdDraw.text(100, 100, "time: "+delta+"ms");
            }
            StdDraw.show();
        }));
    }

    private static void drawPoints(scan.Point[] points){
        StdDraw.setPenColor(Color.BLACK);
        for (scan.Point p : points) {
            StdDraw.filledCircle(p.x(), p.y(), 50);
        }
    }

    private static void draw(Stack<scan.Point> hull){
        scan.Point first = hull.peak();
        scan.Point last = hull.peak();
        StdDraw.setPenColor(Color.RED);

        while (!hull.isEmpty()) {
            Point next = hull.pop();
            StdDraw.line(last.x(), last.y(), next.x(), next.y());
            last = next;
        }
        StdDraw.line(last.x(), last.y(), first.x(), first.y());
    }
}
