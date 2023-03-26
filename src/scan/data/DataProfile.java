package scan.data;

public enum DataProfile {

    SAMLL(100, 10_000, 100-1),
    MEDIUM(1_000, 100_100, 500-5),
    LARGE(1_000, 100_000, 1000-10);
    final int MIN_SIZE;
    final int MAX_SIZE;
    final int FILE_COUNT;

    DataProfile(int MIN_SIZE, int MAX_SIZE, int FILE_COUNT) {
        this.MIN_SIZE = MIN_SIZE;
        this.MAX_SIZE = MAX_SIZE;
        this.FILE_COUNT = FILE_COUNT;
    }
}
