/**
 * Created by Eter on 04/12/2016.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

    }

    //Copy constructor
    public Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int distanceFrom(Point p){
            return Math.abs(x - p.x) + Math.abs(y - p.y);
    }

    public void translate(int direction, int distance)
    {
        switch (direction){
            case 0:
                y += distance;
                break;
            case 1:
                x += distance;
                break;
            case 2:
                y -= distance;
                break;
            case 3:
                x -= distance;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        return (x == p.x && y == p.y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x:" + x +
                ", y:" + y +
                '}';
    }
}
