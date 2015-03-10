package com.lvable.pixelart;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Jiaqi Ning on 22/2/2015.
 */
public class PixelArt {
    public static enum SHAPE{
        SQUARE,CIRCLE,DIAMOND
    }
    
    public static Bitmap renderPixels(Bitmap bitmap,int resolution,int size,SHAPE option) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(canvasBitmap);
        int halfsize = size / 2;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        int cols = width / resolution;
        int rows = height / resolution;
        int top = 0;
        int left = 0;
        int right = resolution;
        int bottom = resolution;


        for (int row = 0;row < rows;row++){
            for (int col = 0;col < cols; col++){
                int pixelX = left + halfsize;
                int pixelY = top + halfsize;
                if (pixelX > width){
                    pixelX = width;
                }
                if (pixelY > height){
                    pixelX = height;
                }
                int choosePixelIndex = pixelX+ pixelY*width;
                paint.setColor(pixels[choosePixelIndex]);

                switch (option){
                    case CIRCLE:
                        canvas.drawCircle(left+halfsize,top+halfsize,halfsize,paint);
                        break;
                    case SQUARE:
                        canvas.drawRect(left,top,right,bottom,paint);
                        break;
                    case DIAMOND:
                        Path path = new Path();
                        path.moveTo(left,top + halfsize);
                        path.lineTo(left+halfsize,top);
                        path.lineTo(left+size,top+halfsize);
                        path.lineTo(left+halfsize,bottom);
                        path.lineTo(left,top+halfsize);
                        path.close();
                        canvas.drawPath(path,paint);
                        break;
                }
                right += resolution;
                left  += resolution;
            }
            left = 0;
            right = resolution;
            top += resolution;
            bottom += resolution;
        }

        return canvasBitmap;
    }

}
