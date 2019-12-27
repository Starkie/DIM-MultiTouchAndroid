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
        this.parentView.addSquare(touchCentre);

        return true;
    }
}