package dim.drawingapp;

import android.graphics.Point;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import dim.drawingapp.Figures.*;

public class FigureGestureDetectorListener extends SimpleOnGestureListener {

    private DrawingCanvasView parentView;

    public FigureGestureDetectorListener(DrawingCanvasView parentView)
    {
        this.parentView = parentView;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Point touchCentre = new Point((int)e.getX(), (int)e.getY());

        Figure figure = null;

        switch (this.parentView.currentDrawingFigureMode) {
            case Square:
                figure = new Square(touchCentre);

                break;
            case Circle:
                figure = new Circle(touchCentre);

                break;
        }

        if (figure != null) {
            this.parentView.addFigure(figure);
        }

        return true;
    }
}