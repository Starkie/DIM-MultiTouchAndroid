package dim.drawingapp.Figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Line extends Figure {

    public Point LineStart;

    public Point LineEnd;

    @Override
    public void scaleFigure(float scaleFactor) {
        // TODO: Implement.
    }

    @Override
    public boolean isPointInFigureArea(Point point) {
        // this.
        return false;
    }

    @Override
    public void drawFigure(Canvas canvas, Paint paint) {
        canvas.drawLine(this.LineStart.x, this.LineStart.y, this.LineEnd.x, this.LineEnd.y, paint);
    }
}