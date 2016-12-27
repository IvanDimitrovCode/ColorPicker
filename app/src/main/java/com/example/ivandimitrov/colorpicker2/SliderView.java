package com.example.ivandimitrov.colorpicker2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ivan Dimitrov on 12/19/2016.
 */

public class SliderView extends View {
    int color = Color.BLUE;
    Paint paint = new Paint();
    Shader shader;
    int sliderWidth;
    int sliderHeight = 150;
    private Bitmap bitmap;
    private Canvas canvas;
    private Canvas canvas2;

    public SliderView(Context context) {
        super(context);
    }

    public SliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SliderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setColor(int color) {
        this.color = color;
        bitmap.recycle();
        this.invalidate();
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        sliderWidth = xNew;
    }

    @Override
    public void onDraw(Canvas canvas) {
        this.canvas = canvas;
        drawSlider();
    }

    private void drawSlider() {

        bitmap = Bitmap.createBitmap(sliderWidth, sliderHeight, Bitmap.Config.RGB_565);
        canvas2 = new Canvas(bitmap);

        paint = new Paint();
        int startY = 0;
        paint.setColor(color);
        shader = new LinearGradient(0, 0, sliderWidth, 0, color, Color.BLACK, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        for (int i = 0; i < sliderHeight; i++, startY++) {
            canvas2.drawLine(0, startY, sliderWidth, startY, paint);
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
