package edu.hrbeu.ice.imagelistview;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by ice on 2016/5/27.
 */
public class MyImageLoader {

    private ImageView mImageView ;
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mImageView.setImageBitmap((Bitmap)msg.obj);
        }
    };

    public void getImageByThread(ImageView imageView, final String url) {
        mImageView = imageView;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getImage(url);
                Message message = Message.obtain();
                message.obj=bitmap;
                handler.sendMessage(message);

            }
        }.start();
    }

    public Bitmap getImage(String imageurl) {
        Bitmap bitmap;
        InputStream is = null;
        URL url = null;
        try {
            url = new URL(imageurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return null;
    }

}