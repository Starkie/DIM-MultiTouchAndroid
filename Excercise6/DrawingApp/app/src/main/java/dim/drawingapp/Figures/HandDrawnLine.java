package dim.drawingapp.Figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Figure} representing a hand drawn line.
 */
public class HandDrawnLine extends Figure {
    // The max acceptable rounding error.
    public static final double EPSILON = 7.5;

    // The points that compose this hand drawn line.
    public Path LinePath;

    private List<Point> linePoints;

    public HandDrawnLine(Point startPoint) {
        this.LinePath = new Path();
        this.LinePath.setLastPoint(startPoint.x, startPoint.y);

        this.linePoints = new ArrayList<>();
        linePoints.add(startPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scaleFigure(float scaleFactor) {
        // Intentionally left blank.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPointInFigureArea(Point point) {
        Point startPoint, endPoint;

        for (int i = 1; i < this.linePoints.size(); i++) {
            startPoint = linePoints.get(i - 1);
            endPoint = linePoints.get(i);

            if (isPointInsideLine(startPoint, endPoint, point)) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFigure(Canvas canvas, Paint paint) {
        canvas.drawPath(LinePath, paint);
    }

    public void lineTo(Point point) {
        this.linePoints.add(point);
        this.LinePath.lineTo(point.x, point.y);
    }


    private static boolean isPointInsideLine(Point a, Point b, Point c) {
        return (distanceBetweenPoints(a, c) + distanceBetweenPoints(c, b)) - distanceBetweenPoints(a, b) <= EPSILON;
    }

    private static double distanceBetweenPoints(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}