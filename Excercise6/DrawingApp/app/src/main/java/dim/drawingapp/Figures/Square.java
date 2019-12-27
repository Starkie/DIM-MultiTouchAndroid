package dim.drawingapp.Figures;

import android.graphics.Point;

/**
 * A representing a square.
 */
public class Square extends Figure {
    private static final float MIN_SQUARE_RADIUS = 25;

    private static final float MAX_SQUARE_RADIUS = 200;

    /**
     * Represents the point at the centre of the square.
     */
    public Point Centre;

    /**
     * Represents the radius of the square [half-the size of a side].
     */
    public float Radius;

    /**
     * {@inheritDoc}
     */
    public void scaleFigure(float scaleFactor) {
        float auxRadius = scaleFactor * Radius;
        auxRadius = Math.max(MIN_SQUARE_RADIUS, auxRadius);
        auxRadius = Math.min(MAX_SQUARE_RADIUS, auxRadius);

        this.Radius = auxRadius;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPointInFigureArea(Point point) {
        return point.x <= (this.Centre.x + this.Radius) &&
                point.x >= (this.Centre.x - this.Radius) &&
                point.y <= (this.Centre.y + this.Radius) &&
                point.y >= (this.Centre.y - this.Radius);
    }
}