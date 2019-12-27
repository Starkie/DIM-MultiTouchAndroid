package dim.drawingapp.Figures;

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
}