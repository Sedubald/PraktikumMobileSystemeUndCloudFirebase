package praktikum.msuc.hsas.de.bitspleasepraktikumfirebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadProfilePicTask extends AsyncTask<Object, Void, Bitmap> {
    ImageView iv_profilePic;
    @Override
    protected Bitmap doInBackground(Object... objects) {
        URL url = null;
        Bitmap bmp = null;
        iv_profilePic = (ImageView) objects[0];
        try {
            url = new URL(objects[1].toString());
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        iv_profilePic.setImageBitmap(result);
    }

}
