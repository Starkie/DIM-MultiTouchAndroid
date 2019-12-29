package dim.drawingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class ColorPicker {
    private DrawingCanvasView drawingCanvasView;

    public ColorPicker(DrawingCanvasView drawingCanvasView) {

        this.drawingCanvasView = drawingCanvasView;
    }

    public void showColorPicker(Context context) {
        new ColorPickerDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
            .setTitle("Select Color")
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton(context.getString(android.R.string.ok),
                    new ColorEnvelopeListener() {
                        @Override
                        public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                            drawingCanvasView.currentColor = envelope.getColor();
                        }
                    })
            .setNegativeButton(context.getString(android.R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
            .attachAlphaSlideBar(false)
            .attachBrightnessSlideBar(true)
            .show();
    }
}
