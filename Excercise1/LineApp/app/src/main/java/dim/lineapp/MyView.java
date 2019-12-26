package dim.lineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.Random;

public class MyView extends View {

    // Random number generator, to obtain the color of the current line.
    Random rdm = new Random();

    // Stores the configuration for the line to draw.
    Paint paint = new Paint();

    // Stores the coordinates of the touch event to draw the line.
    float startX, startY, endX, endY;

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
        // Set the line's color.
        paint.setColor(this.color);

        // Draw the line on the stored coordinates of the touch events.
        canvas.drawLine(this.startX, this.startY, this.endX, this.endY, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Store the start of the line.
                this.startX = event.getX();
                this.startY = event.getY();

                // Calculate the random color for the line
                this.color = rdm.nextInt();

                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
                // Store the end of the line.
                this.endX = event.getX();
                this.endY = event.getY();

                // Invalidate the current view to force the line's redraw.
                this.invalidate();

                break;
            case MotionEvent.ACTION_UP:
                // Reset the line's coordinates.
                this.endY = this.endX = this.startX = this.startY = -1;

                break;
            default:
                Log.i("App", event.toString());
        }

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
}
