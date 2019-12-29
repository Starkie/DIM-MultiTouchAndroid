package dim.drawingapp;

import android.graphics.Point;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class FigureGestureDetectorListener extends SimpleOnGestureListener {

    private DrawingCanvasView parentView;

    public FigureGestureDetectorListener(DrawingCanvasView parentView)
    {
        this.parentView = parentView;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Point touchCentre = new Point((int)e.getX(), (int)e.getY());

        switch (this.parentView.currentDrawingFigureMode) {
            case Square:
                this.parentView.addSquare(touchCentre);

                break;
            case Circle:
                this.parentView.addCircle(touchCentre);

                break;
        }

        return true;
    }
}