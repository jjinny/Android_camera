package com.example.jjin5783.assignment2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.Image;
import android.view.View;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.width;
import static com.example.jjin5783.assignment2.R.attr.height;


/**
 * Created by jjin5783 on 7/9/2017.
 */

public class AddText extends View {

    //implement class
    public AddText(Context context) {
        super(context);
    }
    public void drawTextInImg(FontText text) {

        int textWidth=8;
        int textHeight=8;
        int left = 0;
        int top = textHeight;

        //Get the position(seperate the screen to grids and get the position)
        if(text.getWm_text_pos()==2){
            left = width/2;
        }
        if(text.getWm_text_pos()==3){
            left = width -textWidth;
        }
        if(text.getWm_text_pos()==4){
            top = height/2;
        }
        if(text.getWm_text_pos()==5){
            left = width/2;
            top = height/2;
        }
        if(text.getWm_text_pos()==6){
            left = width -textWidth;
            top = height/2;
        }
        if(text.getWm_text_pos()==7){
            top = height - textHeight;
        }
        if(text.getWm_text_pos()==8){
            left = width/2;
            top = height - textHeight;
        }
        if(text.getWm_text_pos()==9){
            left = width -textWidth;
            top = height - textHeight;
        }
    }
}