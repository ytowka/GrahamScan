package scan.data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataGenerator {
    public static final String DATA = "dataMedium/";
    public static final String SEPARATOR = ":";

    public static final double MIN_COORD = 0;
    public static final double MAX_COORD = 10_000;


    public static final DataProfile dataSize = DataProfile.MEDIUM;

    public static void main(String[] args) {
        generateData();
    }

    public static void generateData(){
        File folder = new File(DATA);
        if(!folder.exists()){
            folder.mkdir();
        }
        int size = dataSize.MIN_SIZE;
        int delta = (dataSize.MAX_SIZE-dataSize.MIN_SIZE) / (dataSize.FILE_COUNT);
        for (int i = 0; i < dataSize.FILE_COUNT+1; i++) {
            generateFile(size);
            size += delta;
            System.out.println(i+" / "+dataSize.FILE_COUNT);
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
