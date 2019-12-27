package dim.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SquareDrawingCanvas extends View {
    private final GestureDetector gestureDetector;

    // Contains each square to draw.
    List<Square> squares = new ArrayList<>();

    // Random number generator, to obtain the color of the current line.
    Random rdm = new Random();

    // Stores the configuration for the line to draw.
    Paint paint = new Paint();

    private ScaleGestureDetector scaleDetector;

    Square currentSquare;

    private int initialGesturePointerId;


    public SquareDrawingCanvas(Context context) {
        super(context);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
        this.scaleDetector = new ScaleGestureDetector(getContext(), new SquareScaleLister(this));
    }

    public SquareDrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
        this.scaleDetector = new ScaleGestureDetector(getContext(), new SquareScaleLister(this));
    }

    public SquareDrawingCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
        this.scaleDetector = new ScaleGestureDetector(getContext(), new SquareScaleLister(this));
    }

    public void addSquare(Point centre) {
        Square square = new Square();

        square.Centre = centre;
        square.Radius = 75;
        square.Color = this.rdm.nextInt();

        this.squares.add(square);
    }

    private GestureDetector buildGestureDetector() {
        GestureDetector.OnGestureListener simpleOnGestureListener =
                new SquareGestureDetectorListener(this);

        return new GestureDetector(getContext(), simpleOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point touchPoint = new Point((int)event.getX(), (int) event.getY());
                this.currentSquare = this.selectSquare(touchPoint);

                // Track the pointer that started the event.
                this.initialGesturePointerId = event.getPointerId(event.getActionIndex());

                break;
            case MotionEvent.ACTION_MOVE:
                if(this.currentSquare == null) {
                    break;
                }

                // Use only the tracked pointer to move the square. Ignore the others.
                Point position = this.getCurrentPointerPosition(event, this.initialGesturePointerId);

                if (position != null) {
                    this.currentSquare.Centre = position;
                }

                break;
            case MotionEvent.ACTION_POINTER_UP:
                // If the initial pointer is removed, stop tracking the square.
                if (event.getPointerId(event.getActionIndex()) == this.initialGesturePointerId)
                {
                    this.initialGesturePointerId = -1;
                    this.currentSquare = null;
                }

                break;
        }

        // Refer the event to the other listeners.
        this.gestureDetector.onTouchEvent(event);
        this.scaleDetector.onTouchEvent(event);

        this.invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the stored squares.
        for (Square square : squares) {
            paint.setColor(square.Color);

            canvas.drawRect(
                    square.Centre.x - square.Radius,
                    square.Centre.y - square.Radius,
                    square.Centre.x + square.Radius,
                    square.Centre.y + square.Radius, paint);
        }
    }

    private static Paint ConfigurePaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        return paint;
    }

    private Square selectSquare(Point touchPoint) {

        for (Square s : this.squares) {
            if(s.isPointInSquareArea(touchPoint)) {
                return s;
            }
        }

        return null;
    }


    private Point getCurrentPointerPosition(MotionEvent event, int pointerId) {
        try {
            MotionEvent.PointerCoords pCoords = new MotionEvent.PointerCoords();

            // To obtain the pointer coordinates, we need the pointer's index in the event.
            int pointerIndex = event.findPointerIndex(pointerId);
            event.getPointerCoords(pointerIndex, pCoords);

            return new Point((int) pCoords.x, (int) pCoords.y);
        }
        catch (IllegalArgumentException e)
        {
            // The pointer does not exist anymore.
            return null;
        }
    }

    private class SquareScaleLister
            extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        private SquareDrawingCanvas squareDrawingCanvas;

        public SquareScaleLister(SquareDrawingCanvas squareDrawingCanvas) {
            this.squareDrawingCanvas = squareDrawingCanvas;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (this.squareDrawingCanvas.currentSquare != null) {
                this.squareDrawingCanvas.currentSquare.scaleSquare(detector.getScaleFactor());

                invalidate();
            }

            return true;
        }
    }
}
