package dim.drawingapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveActionHandler {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh-mm-ss");

    private Activity parentActivity;

    public SaveActionHandler(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    public boolean saveImage(Bitmap bitmap) {
        verifyStoragePermissions(this.parentActivity);

        String drawingFileName = getImageFileName();
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), drawingFileName);

        Toast.makeText(this.parentActivity, "Saving...", Toast.LENGTH_SHORT).show();

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            Toast.makeText(this.parentActivity, "File saved: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        // Scan the file so it appears in the gallery.
        MediaScannerConnection.scanFile(this.parentActivity, new String[] { file.getPath() }, new String[] { "image/jpeg" }, null);

        return true;
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    private static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    1);
        }
    }

    private String getImageFileName() {
        Date date = new Date(System.currentTimeMillis());

        return dateFormat.format(date) + ".png";
    }
}
