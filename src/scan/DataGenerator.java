package scan;

import java.io.*;

public class DataGenerator {
    public static final String DATA = "data/";
    public static final String SEPARATOR = ":";

    public static final int FILE_COUNT = 1000-10;

    public static final double MIN_COORD = 0;
    public static final double MAX_COORD = 10_000;


    public static final int MIN_SIZE = 1_000;
    public static final int MAX_SIZE = 100_000;

    public static void main(String[] args) {
        generateData();
    }

    public static void generateData(){
        int size = MIN_SIZE;
        int delta = (MAX_SIZE-MIN_SIZE) / (FILE_COUNT);
        for (int i = 0; i < FILE_COUNT+1; i++) {
            generateFile(size);
            size += delta;
            System.out.println(i+" / "+FILE_COUNT);
        }
    }

    public static void generateFile(int n){
        File file = new File(DATA+"size"+n+".txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            out.write((n+"\n").getBytes());
            for (int i = 0; i < n; i++) {
                double x = Math.random()*MAX_COORD + MIN_COORD;
                double y = Math.random()*MAX_COORD + MIN_COORD;
                String coordinates = x+SEPARATOR+y+"\n";
                bout.write(coordinates.getBytes());
            }
            bout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
