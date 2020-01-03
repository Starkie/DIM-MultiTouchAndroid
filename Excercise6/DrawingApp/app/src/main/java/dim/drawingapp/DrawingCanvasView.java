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

import dim.drawingapp.Figures.*;
import dim.drawingapp.utils.TouchPointerUtils;

/**
 * A view that enables the drawing of figures in it.
 */
public class DrawingCanvasView extends View {
    // Handler for basic gestures.
    private final GestureDetector gestureDetector;

    // Handler for the pinch-to-scale gesture.
    private final ScaleGestureDetector scaleDetector;

    // Contains each figure to draw in the canvas.
    List<Figure> figures = new ArrayList<>();

    // Stores the configuration for the figure to draw.
    Paint paint = new Paint();

    // Current color.
    int currentColor = Color.BLACK;

    // The figure that is currently selected.
    Figure currentFigure;

    // Represents the currently active figure that will be drawn on user interaction.
    FigureCategory currentDrawingFigureMode = FigureCategory.Square;

    // The pointer ID that initiated the current gesture. Used to track the movement of figures.
    private int initialGesturePointerId;

    /**
     * {@inheritDoc}
     */
    public DrawingCanvasView(Context context) {
        super(context);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
        this.scaleDetector = new ScaleGestureDetector(getContext(), new FigureScaleGestureListener(this));
    }

    /**
     * {@inheritDoc}
     */
    public DrawingCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
        this.scaleDetector = new ScaleGestureDetector(getContext(), new FigureScaleGestureListener(this));
    }

    /**
     * {@inheritDoc}
     */
    public DrawingCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
        this.scaleDetector = new ScaleGestureDetector(getContext(), new FigureScaleGestureListener(this));
    }

    /**
     * Adds a {@link Circle} figure to the current canvas.
     * @param figure The figure to add to the canvas.
     */
    public void addFigure(Figure figure) {
        figure.Color = this.currentColor;

        this.figures.add(figure);
    }

    private GestureDetector buildGestureDetector() {
        GestureDetector.OnGestureListener simpleOnGestureListener =
                new FigureGestureDetectorListener(this);

        return new GestureDetector(getContext(), simpleOnGestureListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point touchPoint = new Point((int)event.getX(), (int) event.getY());

                if (this.currentDrawingFigureMode == FigureCategory.HandDrawnLine) {
                    HandDrawnLine handDrawnLine = new HandDrawnLine(touchPoint);

                    this.addFigure(handDrawnLine);

                    this.currentFigure = handDrawnLine;
                }
                else {
                    this.currentFigure = this.selectFigure(touchPoint);
                }

                // Track the pointer that started the event.
                this.initialGesturePointerId = event.getPointerId(event.getActionIndex());

                break;
            case MotionEvent.ACTION_MOVE:
                if(this.currentFigure == null) {
                    break;
                }

                // Use only the tracked pointer to move the figure. Ignore the others.
                Point position = TouchPointerUtils.getCurrentPointerPosition(event, this.initialGesturePointerId);

                if (this.currentFigure instanceof HandDrawnLine)
                {
                     ((HandDrawnLine) this.currentFigure).LinePath.lineTo(position.x, position.y);
                }
                else if (position != null) {
                    this.currentFigure.Centre = position;
                }

                break;
            case MotionEvent.ACTION_POINTER_UP:
                // If the initial pointer is removed, stop tracking the figure.
                if (event.getPointerId(event.getActionIndex()) == this.initialGesturePointerId)
                {
                    this.initialGesturePointerId = -1;
                    this.currentFigure = null;
                }

                break;
        }

        // Refer the event to the other listeners.
        this.gestureDetector.onTouchEvent(event);
        this.scaleDetector.onTouchEvent(event);

        this.invalidate();

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        for (Figure figure : figures) {
            paint.setColor(figure.Color);

            figure.drawFigure(canvas, this.paint);
        }
    }

    /**
     * Performs the initial configuration of the {@link Paint} used in the canvas to draw figures.
     * @param paint The paint to configure.
     * @return The configured paint.
     */
    private static Paint ConfigurePaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        return paint;
    }

    /**
     * From a given touch points, determines which figure was selected.
     * @param touchPoint The point where the user has touched.
     * @return The figure, if any, that was selected by the touch point. Otherwise returns null.
     */
    private Figure selectFigure(Point touchPoint) {
        for (Figure f : this.figures) {
            if (f.isPointInFigureArea(touchPoint)) {
                return f;
            }
        }

        return null;
    }
}
