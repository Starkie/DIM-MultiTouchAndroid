package dim.drawingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dim.drawingapp.Figures.FigureCategory;

public class MainActivity extends AppCompatActivity {

    private DrawingCanvasView drawingCanvasView;

    private ColorPicker colorPicker;
    private SaveActionHandler saveActionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.drawingCanvasView = this.findViewById(R.id.drawingCanvas);
        this.colorPicker = new ColorPicker(this.drawingCanvasView);
        this.saveActionHandler = new SaveActionHandler(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    public void switchToSquareMode(View view) {
        this.drawingCanvasView.currentDrawingFigureMode = FigureCategory.Square;
    }

    public void switchToHandDrawnLineMode(View view) {
        this.drawingCanvasView.currentDrawingFigureMode = FigureCategory.HandDrawnLine;
    }

    public void switchToCircleMode(View view) {
        this.drawingCanvasView.currentDrawingFigureMode = FigureCategory.Circle;
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
}
