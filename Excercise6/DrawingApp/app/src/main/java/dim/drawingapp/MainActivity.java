package dim.drawingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

import dim.drawingapp.Figures.FigureCategory;
import dim.drawingapp.canvas.DrawingCanvasView;

public class MainActivity extends AppCompatActivity {

    private DrawingCanvasView drawingCanvasView;

    private ColorPicker colorPicker;
    private SaveActionHandler saveActionHandler;

    private HashMap<FigureCategory, ImageButton> imageButtonsHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.drawingCanvasView = this.findViewById(R.id.drawingCanvas);
        this.colorPicker = new ColorPicker(this.drawingCanvasView);
        this.saveActionHandler = new SaveActionHandler(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        this.imageButtonsHashMap = new HashMap<>();
        this.imageButtonsHashMap.put(FigureCategory.HandDrawnLine, (ImageButton) findViewById(R.id.handDrawnLineButton));
        this.imageButtonsHashMap.put(FigureCategory.Circle, (ImageButton) findViewById(R.id.circleButton));
        this.imageButtonsHashMap.put(FigureCategory.Square, (ImageButton) findViewById(R.id.squareButton));

        this.switchFigureMode(FigureCategory.Square);

        // TODO: store the drawing state in the activity.
        // TODO: add a save drawing button.
        // TODO: add a delete figure mode.
    }

    public void switchToSquareMode(View view) {
        this.switchFigureMode(FigureCategory.Square);
    }

    public void switchToHandDrawnLineMode(View view) {
        this.switchFigureMode(FigureCategory.HandDrawnLine);
    }

    public void switchToCircleMode(View view) {
        this.switchFigureMode(FigureCategory.Circle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save:
                Bitmap bitmap = this.drawingCanvasView.exportToBitmap();
                this.saveActionHandler.saveImage(bitmap);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeDrawColor(View view) {
        this.colorPicker.showColorPicker(this);
    }

    private void switchFigureMode(FigureCategory category) {
        this.drawingCanvasView.currentDrawingFigureMode = category;

        for (ImageButton imageButton : imageButtonsHashMap.values())
        {
            imageButton.setActivated(false);
        }

        ColorFilter colorFilter = new ColorFilter();
        // colorFilter.

        this.imageButtonsHashMap.get(category)
            .setActivated(true);
    }
}
