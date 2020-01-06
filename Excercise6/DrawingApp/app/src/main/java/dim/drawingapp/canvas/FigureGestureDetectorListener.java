package dim.drawingapp.canvas;

import android.graphics.Point;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import dim.drawingapp.Figures.*;

public class FigureGestureDetectorListener extends SimpleOnGestureListener {

    private DrawingCanvasView drawingCanvas;

    public FigureGestureDetectorListener(DrawingCanvasView drawingCanvas)
    {
        this.drawingCanvas = drawingCanvas;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Point touchCentre = new Point((int)e.getX(), (int)e.getY());

        Figure figure = null;

        switch (this.drawingCanvas.currentDrawingFigureMode) {
            case Square:
                figure = new Square(touchCentre);

                break;
            case Circle:
                figure = new Circle(touchCentre);

                break;

        }

        if (figure != null) {
            this.drawingCanvas.addFigure(figure);
        }

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Point touchCentre = new Point((int)e.getX(), (int)e.getY());

        switch (this.drawingCanvas.currentDrawingFigureMode) {
            case Delete:
                Figure selectedFigure = this.drawingCanvas.selectFigure(touchCentre);
                this.drawingCanvas.figures.remove(selectedFigure);

                break;
        }

    }
}