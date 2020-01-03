package dim.drawingapp.Figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * A {@link Figure} representing a circle.
 */
public class Circle extends Figure {
    private static final float MIN_CIRCLE_RADIUS = 25;

    private static final float MAX_CIRCLE_RADIUS = 200;

    /**
     * Represents the radius of the circle.
     */
    public float Radius;

    public Circle(Point centre) {
        this.Centre = centre;
        this.Radius = 75;
    }

    /**
     * {@inheritDoc}
     */
    public void scaleFigure(float scaleFactor) {
        float auxRadius = scaleFactor * Radius;
        auxRadius = Math.max(MIN_CIRCLE_RADIUS, auxRadius);
        auxRadius = Math.min(MAX_CIRCLE_RADIUS, auxRadius);

        this.Radius = auxRadius;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPointInFigureArea(Point point) {
        double distanceFromPointToCentre =
            Math.pow(point.x - this.Centre.x, 2) + Math.pow(point.y - this.Centre.y, 2);

        double errorMargin = 1.0;

        double squaredRadius = (Math.pow(this.Radius + errorMargin, 2));

        return distanceFromPointToCentre <= squaredRadius;
    }

    @Override
    public void drawFigure(Canvas canvas, Paint paint) {
        canvas.drawCircle(
            this.Centre.x,
            this.Centre.y,
            this.Radius,
            paint);
    }
}