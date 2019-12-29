package dim.drawingapp.Figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * A class representing figures to draw.
 */
public abstract class Figure {
    /**
     * The color in which the figure will be drawn.
     */
    public int Color;

    /**
     * Represents the point at the centre of the figure.
     */
    public Point Centre;

    /**
     * Scales the figure according to the provided scale factor.
     * @param scaleFactor The factor in which to scale the figure.
     */
    public abstract void scaleFigure(float scaleFactor);

    /**
     * Determines if the given point is located inside in the figure area.
     * @param point The point.
     * @return A boolean value indicating whether the point is in the figure's area.
     */
    public abstract boolean isPointInFigureArea(Point point);

    /**
     * Draws the figure in the given canvas.
     * @param canvas The canvas where to draw the figure.
     * @param paint The paint to configure the figure drawn.
     */
    public abstract void drawFigure(Canvas canvas, Paint paint);
}