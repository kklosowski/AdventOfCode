import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private int id;
    private Point topLeft;
    private Point bottomRight;

    public Rectangle(int id, Point topLeft, Point bottomRight) {
        this.id = id;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }


    public Point getBottomRight() {
        return bottomRight;
    }


    public boolean overlaps(Rectangle rect){
        if (topLeft.getX() > rect.bottomRight.getX() || rect.getTopLeft().getX() > bottomRight.getX()){
            return false;
        }
        if (topLeft.getY() > rect.bottomRight.getY() || rect.getTopLeft().getY() > bottomRight.getY()){
            return false;
        }

        return true;
    }

    public Rectangle overlappingArea(Rectangle rect) {
        Rectangle overlap = new Rectangle(new Point(-1, -1), new Point(-1, -1));
        if (!this.overlaps(rect)) return null;

        if (topLeft.getX() <= rect.getTopLeft().getX() && rect.getTopLeft().getX() <= bottomRight.getX()) {
            overlap.getTopLeft().setX(rect.getTopLeft().getX());
        } else {
            overlap.getTopLeft().setX(topLeft.getX());
        }

        if (topLeft.getX() <= rect.getBottomRight().getX() && rect.getBottomRight().getX() <= bottomRight.getX()){
            overlap.getBottomRight().setX(rect.getBottomRight().getX());
        } else {
            overlap.getBottomRight().setX(bottomRight.getX());
        }

        if (topLeft.getY() <= rect.getTopLeft().getY() && rect.getTopLeft().getY() <= bottomRight.getY()) {
            overlap.getTopLeft().setY(rect.getTopLeft().getY());
        } else {
            overlap.getTopLeft().setY(topLeft.getY());
        }

        if (topLeft.getY() <= rect.getBottomRight().getY() && rect.getBottomRight().getY() <= bottomRight.getY()){
            overlap.getBottomRight().setY(rect.getBottomRight().getY());
        } else {
            overlap.getBottomRight().setY(bottomRight.getY());
        }

        return overlap;
    }

    public List<Point> toPointList(){
        List<Point> points = new ArrayList<>();

        for (int x = topLeft.getX(); x <= bottomRight.getX(); x++) {
            for (int y = topLeft.getY(); y <= bottomRight.getY(); y++) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }


    @Override
    public String toString() {
        return String.format("[%s - %s]", topLeft, bottomRight);
    }
}
