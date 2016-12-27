package com.example.ivandimitrov.colorpicker2;

/**
 * Created by Ivan Dimitrov on 12/19/2016.
 */

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    private Bitmap bitmap;
    private Paint paint = new Paint();
    private double red = 255;
    private double green = 0;
    private double blue = 0;
    private int cordX = 0;
    private Canvas canvas;
    private Canvas canvas2;
    private int colorPickerWidth = 768;
    private int colorPickerHeight = 500;
    private double step = 2;

    public DrawView(Context context) {
        super(context);
        this.setDrawingCacheEnabled(true);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setDrawingCacheEnabled(true);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setDrawingCacheEnabled(true);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void onDraw(Canvas canvas) {
        this.canvas = canvas;
        oldDraw();
    }

    void oldDraw() {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
            return;
        }
        calculateMeasurements();

        bitmap = Bitmap.createBitmap(colorPickerWidth, colorPickerHeight, Bitmap.Config.RGB_565);
        canvas2 = new Canvas(bitmap);

        while (green < 255) {
            paint.setColor(Color.rgb((int) red, (int) green, (int) blue));
            drawColorPicker();
            green += step;
        }
        green = 255;

        while (red > 0) {
            paint.setColor(Color.rgb((int) red, (int) green, (int) blue));
            drawColorPicker();
            red -= step;
        }
        red = 0;

        while (blue < 255) {
            paint.setColor(Color.rgb((int) red, (int) green, (int) blue));
            drawColorPicker();
            blue += step;
        }
        blue = 255;

        while (green > 0) {
            paint.setColor(Color.rgb((int) red, (int) green, (int) blue));
            drawColorPicker();
            green -= step;
        }
        green = 0;

        while (red < 255) {
            paint.setColor(Color.rgb((int) red, (int) green, (int) blue));
            drawColorPicker();
            red += step;
        }
        red = 255;

        while (blue > 0) {
            paint.setColor(Color.rgb((int) red, (int) green, (int) blue));
            drawColorPicker();
            blue -= step;
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        colorPickerWidth = xNew;
    }

    private void calculateMeasurements() {
        double tempWidth;
        tempWidth = colorPickerWidth / 6;
        step = 255 / tempWidth;
    }

    public void drawColorPicker() {
        int color = paint.getColor();
        Shader shader = new LinearGradient(0, 0, 0, colorPickerHeight, color, Color.WHITE, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas2.drawLine(cordX++, 0, cordX, colorPickerHeight, paint);
    }
}
