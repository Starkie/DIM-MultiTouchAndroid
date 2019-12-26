package dim.squaresapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

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

    public SquareDrawingCanvas(Context context) {
        super(context);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
    }

    public SquareDrawingCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
    }

    public SquareDrawingCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.paint = ConfigurePaint(paint);
        this.gestureDetector = buildGestureDetector();
    }

    public void addSquare(Point centre) {
        Square square = new Square();

        square.Centre = centre;
        square.Scale = 40;
        square.Color = this.rdm.nextInt();

        this.squares.add(square);
    }

    @NotNull
    private GestureDetector buildGestureDetector() {
        GestureDetector.OnGestureListener simpleOnGestureListener =
                new SquareGestureDetectorListener(this);

        return new GestureDetector(getContext(), simpleOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        this.invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the stored squares.
        for (Square rect : squares) {
            paint.setColor(rect.Color);

            canvas.drawRect(
                    rect.Centre.x - rect.Scale,
                    rect.Centre.y - rect.Scale,
                    rect.Centre.x + rect.Scale,
                    rect.Centre.y + rect.Scale, paint);
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
}
