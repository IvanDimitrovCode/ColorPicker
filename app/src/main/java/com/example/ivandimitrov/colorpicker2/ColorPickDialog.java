package com.example.ivandimitrov.colorpicker2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ivan Dimitrov on 12/19/2016.
 */

public class ColorPickDialog extends DialogFragment {
    DrawView colorPicker;
    SliderView slider;
    MyScrollView myScrollView;
    Button button;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.color_picker, null);
        colorPicker = (DrawView) view.findViewById(R.id.draw_view);
        slider = (SliderView) view.findViewById(R.id.slider_view);
        myScrollView = (MyScrollView) view.findViewById(R.id.my_scroll_view);
        button = (Button) view.findViewById(R.id.button_color);

        colorPicker.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    myScrollView.setScrolling(true);
                } else {
                    Bitmap mBitmap = colorPicker.getBitmap();
                    int xCord = (int) motionEvent.getX();
                    int yCord = (int) motionEvent.getY();
                    if (xCord > mBitmap.getWidth() || yCord > mBitmap.getHeight()
                            || (yCord < 0 || xCord < 0)) {
                        return false;
                    }
                    myScrollView.setScrolling(false);
                    int pixel = mBitmap.getPixel(xCord, yCord);
                    slider.setColor(pixel);
                    button.setBackgroundColor(pixel);
                    button.setText(String.format("#%08X", pixel));
                }
                return true;
            }
        });

        slider.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent motionEvent) {
                Bitmap mBitmap = slider.getBitmap();
                int xCord = (int) motionEvent.getX();
                int yCord = (int) motionEvent.getY();
                if (xCord > mBitmap.getWidth() || yCord > mBitmap.getHeight()
                        || (yCord < 0 || xCord < 0)) {
                    return false;
                }
                int pixel = mBitmap.getPixel(xCord, yCord);
                int value = Color.blue(pixel) + Color.red(pixel) + Color.green(pixel);
                if (value < 350) {
                    button.setTextColor(Color.WHITE);
                } else {
                    button.setTextColor(Color.BLACK);
                }
                button.setBackgroundColor(pixel);
                button.setText(String.format("#%08X", pixel));
                return true;
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
