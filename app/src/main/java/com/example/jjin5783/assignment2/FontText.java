package com.example.jjin5783.assignment2;

/**
 * Created by jjin5783 on 7/9/2017.
 */

//FontText Class
public class FontText {
    //set text attributes
    private String text;
    private int wm_text_pos;
    private String wm_text_color;
    private Integer wm_text_size;
    private String wm_text_font;
    //Get text
    public String getText()
    {
        return text;
    }
    //set text
    public void setText(String text)
    {
        this.text = text;
    }
    //get text position
    public int getWm_text_pos()
    {
        return wm_text_pos;
    }
    //set text position
    public void setWm_text_pos(int wm_text_pos)
    {
        this.wm_text_pos = wm_text_pos;
    }
    //get text color
    public String getWm_text_color()
    {
        return wm_text_color;
    }
    //set text color
    public void setWm_text_color(String wm_text_color)
    {
        this.wm_text_color = wm_text_color;
    }
    //get text size
    public Integer getWm_text_size()
    {
        return wm_text_size;
    }
    //set text size
    public void setWm_text_size(Integer wm_text_size)
    {
        this.wm_text_size = wm_text_size;
    }
    //get text font
    public String getWm_text_font()
    {
        return wm_text_font;
    }
    //set text font
    public void setWm_text_font(String wm_text_font)
    {
        this.wm_text_font = wm_text_font;
    }
    public FontText(String text, int wm_text_pos, String wm_text_color,
                    Integer wm_text_size, String wm_text_font) {
        super();
        this.text = text;
        this.wm_text_pos = wm_text_pos;
        this.wm_text_color = wm_text_color;
        this.wm_text_size = wm_text_size;
        this.wm_text_font = wm_text_font;
    }
    public FontText(){}

}
