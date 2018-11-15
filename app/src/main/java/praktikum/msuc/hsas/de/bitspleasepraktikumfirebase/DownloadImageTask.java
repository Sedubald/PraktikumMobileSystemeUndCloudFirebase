package praktikum.msuc.hsas.de.bitspleasepraktikumfirebase;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/*
    We need to download images from another task.
    There is no network access from the main thread,
    we would receive a NetworkOnMainThreadException.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView iv;

    public DownloadImageTask(ImageView iv) {
        this.iv = iv;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bmp = null;

        try {
            InputStream in = new java.net.URL(url).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }
    protected void onPostExecute(Bitmap result) {
        iv.setImageBitmap(result);
    }
}