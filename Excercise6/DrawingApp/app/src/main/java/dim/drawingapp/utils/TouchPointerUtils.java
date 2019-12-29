package dim.drawingapp.utils;

import android.graphics.Point;
import android.view.MotionEvent;

public class TouchPointerUtils {
    /**
     * Determines the position of a pointer, given its id, in a given {@link MotionEvent}.
     * @param event The event in which to locate the pointer.
     * @param pointerId The identifier of the pointer to locate.
     * @return The position of the pointer id, if it was present on the motion event.
     *         Otherwise, returns null.
     */
    public static Point getCurrentPointerPosition(MotionEvent event, int pointerId) {
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
}
