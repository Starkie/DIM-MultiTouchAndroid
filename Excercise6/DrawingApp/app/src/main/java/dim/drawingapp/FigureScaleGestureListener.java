package dim.drawingapp;

import android.view.ScaleGestureDetector;

public class FigureScaleGestureListener
        extends ScaleGestureDetector.SimpleOnScaleGestureListener
{
    private DrawingCanvasView drawingCanvasView;

    public FigureScaleGestureListener(DrawingCanvasView drawingCanvasView) {
        this.drawingCanvasView = drawingCanvasView;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (this.drawingCanvasView.currentFigure != null) {
            this.drawingCanvasView.currentFigure.scaleFigure(detector.getScaleFactor());

            this.drawingCanvasView.invalidate();
        }

        return true;
    }
}