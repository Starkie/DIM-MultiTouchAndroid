package dim.drawingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

import dim.drawingapp.Figures.CurrentFigureMode;
import dim.drawingapp.canvas.DrawingCanvasView;

public class MainActivity extends AppCompatActivity {

    private DrawingCanvasView drawingCanvasView;

    private ColorPicker colorPicker;
    private SaveActionHandler saveActionHandler;

    private HashMap<CurrentFigureMode, ImageButton> imageButtonsHashMap;

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
        this.imageButtonsHashMap.put(CurrentFigureMode.HandDrawnLine, (ImageButton) findViewById(R.id.handDrawnLineButton));
        this.imageButtonsHashMap.put(CurrentFigureMode.Circle, (ImageButton) findViewById(R.id.circleButton));
        this.imageButtonsHashMap.put(CurrentFigureMode.Square, (ImageButton) findViewById(R.id.squareButton));
        this.imageButtonsHashMap.put(CurrentFigureMode.Delete, (ImageButton) findViewById(R.id.deleteButton));

        this.switchFigureMode(CurrentFigureMode.Square);
    }

    public void switchToSquareMode(View view) {
        this.switchFigureMode(CurrentFigureMode.Square);
    }

    public void switchToHandDrawnLineMode(View view) {
        this.switchFigureMode(CurrentFigureMode.HandDrawnLine);
    }

    public void switchToCircleMode(View view) {
        this.switchFigureMode(CurrentFigureMode.Circle);
    }

    public void switchToDeleteMode(View view) {
        this.switchFigureMode(CurrentFigureMode.Delete);
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

    private void switchFigureMode(CurrentFigureMode category) {
        this.drawingCanvasView.currentDrawingFigureMode = category;

        for (ImageButton imageButton : imageButtonsHashMap.values())
        {
            imageButton.setActivated(false);
        }

        ImageButton button = this.imageButtonsHashMap.get(category);

        if (button != null) {
            button.setActivated(true);
        }
    }
}
