package com.example.android.minireddit.networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Aly on 3/14/2019.
 */
public class DownloadImageTask  extends AsyncTask<String, Void, Bitmap> {
    /**
     * The Bm imageview to contaion the downloaded image.
     */
    ImageView bmImage;

    /**
     * Instantiates a new Download image task.
     *
     * @param bmImage the bm image
     */
    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }


    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
