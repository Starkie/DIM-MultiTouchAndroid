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
    // The points that compose this hand drawn line.
    public Path LinePath;

    public HandDrawnLine(Point startPoint) {
        this.LinePath = new Path();
        this.LinePath.setLastPoint(startPoint.x, startPoint.y);
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
        // TODO: Implement.
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFigure(Canvas canvas, Paint paint) {
        canvas.drawPath(LinePath, paint);
    }
}