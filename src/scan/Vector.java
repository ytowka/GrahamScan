package scan;

public record Vector(double x, double y) {

    public Vector(Point p1, Point p2){
        this(p2.x() - p1.x(), p2.y() - p1.y());
    }
}
