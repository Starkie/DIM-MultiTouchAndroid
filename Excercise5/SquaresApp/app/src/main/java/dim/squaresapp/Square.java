package dim.squaresapp;

import android.graphics.Point;

public class Square {
    private static final float MIN_SQUARE_RADIUS = 25;
    private static final float MAX_SQUARE_RADIUS = 200;

    public Point Centre;
    public int Color;
    public float Radius;

    public void scaleSquare(float scaleFactor) {
        float auxRadius = scaleFactor * Radius;
        auxRadius = Math.max(MIN_SQUARE_RADIUS, auxRadius);
        auxRadius = Math.min(MAX_SQUARE_RADIUS, auxRadius);

        this.Radius = auxRadius;
    }

    public boolean isPointInSquareArea(Point point) {
        return point.x <= (this.Centre.x + this.Radius) &&
                point.x >= (this.Centre.x - this.Radius) &&
                point.y <= (this.Centre.y + this.Radius) &&
                point.y >= (this.Centre.y - this.Radius);
    }
}