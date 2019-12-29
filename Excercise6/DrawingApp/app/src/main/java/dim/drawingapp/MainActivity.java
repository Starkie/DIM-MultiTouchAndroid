package dim.drawingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dim.drawingapp.Figures.FigureCategory;

public class MainActivity extends AppCompatActivity {

    private DrawingCanvasView drawingCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.drawingCanvasView = this.findViewById(R.id.drawingCanvas);
        // TODO: Implement a remove mode.
        // TODO: Triangle, Circle.
        // TODO: COlor picker.
    }

    public void switchToSquareMode(View view) {
        this.drawingCanvasView.currentDrawingFigureMode = FigureCategory.Square;
    }

    public void switchToLineMode(View view) {
        this.drawingCanvasView.currentDrawingFigureMode = FigureCategory.Line;
    }

    public void switchToCircleMode(View view) {
        this.drawingCanvasView.currentDrawingFigureMode = FigureCategory.Circle;
    }
}
