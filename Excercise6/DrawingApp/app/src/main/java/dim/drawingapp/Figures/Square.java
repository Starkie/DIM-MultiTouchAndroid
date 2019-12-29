package dim.drawingapp.Figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * A representing a square.
 */
public class Square extends Figure {
    private static final float MIN_SQUARE_RADIUS = 25;

    private static final float MAX_SQUARE_RADIUS = 200;

    /**
     * Represents the radius of the square [half-the size of a side].
     */
    public float Radius;

    public Square(Point centre) {
        this.Centre = centre;
        this.Radius = 75;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scaleFigure(float scaleFactor) {
        float auxRadius = scaleFactor * Radius;
        auxRadius = Math.max(MIN_SQUARE_RADIUS, auxRadius);
        auxRadius = Math.min(MAX_SQUARE_RADIUS, auxRadius);

        this.Radius = auxRadius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPointInFigureArea(Point point) {
        return point.x <= (this.Centre.x + this.Radius) &&
                point.x >= (this.Centre.x - this.Radius) &&
                point.y <= (this.Centre.y + this.Radius) &&
                point.y >= (this.Centre.y - this.Radius);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFigure(Canvas canvas, Paint paint) {
        canvas.drawRect(
                this.Centre.x - this.Radius,
                this.Centre.y - this.Radius,
                this.Centre.x + this.Radius,
                this.Centre.y + this.Radius,
                paint);
    }
}