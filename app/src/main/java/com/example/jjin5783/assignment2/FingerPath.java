package com.example.jjin5783.assignment2;

/**
 * Created by jjin5783 on 7/9/2017.
 */

import android.graphics.Path;
//FigerPath class
public class FingerPath {
    //initialize path attributes
    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;
    public Path path;

    //get attributes
    public FingerPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}