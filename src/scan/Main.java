package scan;

import scan.data.Data;
import scan.data.DataGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File dataFolder = new File(DataGenerator.DATA);
        File[] dataFile = dataFolder.listFiles();

        Arrays.sort(dataFile, (o1, o2) -> {
            int size1 = Integer.parseInt(o1.getName().replaceAll("[a-zA-z.]*", ""));
            int size2 = Integer.parseInt(o2.getName().replaceAll("[a-zA-z.]*", ""));
            return size1 - size2;
        });

        File results = new File("results/results_size_"+dataFile.length+".txt");
        if(!results.exists()){
            try {
                results.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        PrintWriter out;
        try {
            out = new PrintWriter(results);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        for(File file : dataFile){
            Data data = readPoints(file);
            final int[] iters = {0};
            long time = measureTime(
                    () -> iters[0] += Scan.scan(data.points()).iterations()
            );
            out.println(data.size()+"    "+time+"   "+iters[0]);
            System.out.println(data.size()+":"+time+":"+iters[0]);
        }
        out.close();
    }


    static long measureTime(Runnable code){
        long startTime = System.nanoTime();
        code.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
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