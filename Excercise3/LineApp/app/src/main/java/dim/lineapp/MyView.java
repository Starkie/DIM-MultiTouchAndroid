package dim.lineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.Hashtable;
import java.util.Random;

public class MyView extends View {

    Hashtable<Integer, Line> pointersTable = new Hashtable<>();

    // Random number generator, to obtain the color of the current line.
    Random rdm = new Random();

    // Stores the configuration for the line to draw.
    Paint paint = new Paint();

    // The colour that will be used to draw the line.
    int color = Color.BLACK;

    public MyView(Context context) {
        super(context);

        this.paint = ConfigurePaint(paint);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.paint = ConfigurePaint(paint);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.paint = ConfigurePaint(paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Draw the stored lines.
        for (Line line : pointersTable.values()) {
            // Set the line's color.
            paint.setColor(line.Color);

            // Draw the line on the stored coordinates of the touch events.
            canvas.drawLine(line.LineStart.x, line.LineStart.y, line.LineEnd.x, line.LineEnd.y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        int index = event.getActionIndex();

        int pointerId = event.getPointerId(index);

        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                // Store the start of the line.
                MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                event.getPointerCoords(pointerId, pointerCoords);

                // Calculate the random color for the line
                this.color = rdm.nextInt();

                // Store the current pointer.
                this.pointersTable.put(pointerId, this.createLine(pointerCoords.x, pointerCoords.y, color));

                break;
            case MotionEvent.ACTION_MOVE:
                // Update every pointers line.
                for (int pointerKey : this.pointersTable.keySet()) {
                    MotionEvent.PointerCoords pCoords = new MotionEvent.PointerCoords();

                    event.getPointerCoords(pointerKey, pCoords);

                    Point point = new Point((int) pCoords.x, (int) pCoords.y);

                    Line pointerLine = this.pointersTable.get(pointerKey);
                    pointerLine.LineEnd = point;
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                // Remove the line of the current pointer.
                this.pointersTable.remove(pointerId);

                break;
            default:
                Log.d("APP", "onTouchEvent: event:" + event);
        }

        // Invalidate the current view to force the line's redraw.
        this.invalidate();

        return true;
    }

    private static Paint ConfigurePaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        return paint;
    }

    private Line createLine(float startX, float startY, float endX, float endY, int color) {
        Line line = new Line();

        line.Color = color;
        line.LineStart = new Point((int)startX, (int)startY);
        line.LineEnd = new Point((int)endX, (int)endY);

        return line;
    }

    private Line createLine(float startX, float startY, int color) {
        Line line = new Line();

        line.Color = color;
        line.LineStart = new Point((int)startX, (int)startY);
        line.LineEnd = new Point((int)startX, (int)startY);

        return line;
    }
}
