package scan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File dataFolder = new File("data/");
        File[] dataFile = dataFolder.listFiles();

        Arrays.sort(dataFile, (o1, o2) -> {
            int size1 = Integer.parseInt(o1.getName().replaceAll("[a-zA-z.]*", ""));
            int size2 = Integer.parseInt(o2.getName().replaceAll("[a-zA-z.]*", ""));
            return size1 - size2;
        });


        for(File file : dataFile){
            Data data = readPoints(file);
            long startTime = System.currentTimeMillis();
            Stack<Point> hull = Scan.scan(data.points());
            long delta = System.currentTimeMillis() - startTime;
            System.out.println(data.size()+"    "+delta);
        }
    }

    static Data readPoints(File file){
        try {
            Scanner in = new Scanner(file);
            int n = Integer.parseInt(in.nextLine());
            Point[] points = new Point[n];

            for (int i = 0; i < n; i++) {
                String[] coords = in.nextLine().split(":");
                points[i] = new Point(Float.parseFloat(coords[0]),Float.parseFloat(coords[1]));
            }

            return new Data(n , points);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}