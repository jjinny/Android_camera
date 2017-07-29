package com.example.jjin5783.assignment2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

//follow tutorial from two websites
//http://wintechtutorials.com/blog/android-capture-image-from-camera-and-select-image-from-gallery/
//https://www.ssaurel.com/blog/learn-to-create-a-paint-application-for-android/
public class MainActivity extends AppCompatActivity {

    //initialize FontText and PaintView from FontText and PaintView class
    private FontText newText;
    private PaintView paintView;
    //initialize imageView
    ImageView ivImage;
    //initialize camera requst
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    //initialize the button
    Button paint;
    Button addText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //add image from xml file
        ivImage = (ImageView) findViewById(R.id.ivImage);
        //add float action button from xml file
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //create click event for the button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
        //add paint and addText button from xml file
        paint = (Button)findViewById(R.id.button);
        addText = (Button)findViewById(R.id.button2);
        //add EditText and set it invisible at first
        EditText add = new EditText(this);
        add.setCursorVisible(false);
        add.setTag("input");
        //add sketch overlay method
        sketch();
        //add adding text method
        addText();
    }

    //Create a method to show alert dialog builder
    private void SelectImage(){
        //Create options in menu
        final CharSequence[] items={"Camera","Gallery", "Cancel"};
        //initialize alertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //add a title for the menu
        builder.setTitle("Add Image");
        //add options functions
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //camera
                if (items[i].equals("Camera")) {
                    //create intent to launch image_capture activity
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                    //gallery
                } else if (items[i].equals("Gallery")) {
                    //create intent to get image
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);
                    //cancel
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }

    //create onActivityResult to
    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        //Allow opening the camera
        if(resultCode== Activity.RESULT_OK){
            //choose camera
            if(requestCode==REQUEST_CAMERA){
                //create bitmap to put photo
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bmp);
                //choose images
            }else if(requestCode==SELECT_FILE){
                //images menu
                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
            }

        }
    }

    //Create sketch method
    public void sketch(){
        paint.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //add paintView from xml
                        paintView = (PaintView) findViewById(R.id.paintView);
                        //initialize displaymetrics for describing information about the display
                        DisplayMetrics metrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(metrics);
                        //use the method from panitView class
                        paintView.init(metrics);
                    }
                }
        );
    }

    //Create addText method
    public void addText(){
        //create button click event(when click addText button)
        paint.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //make editText visible
                        addText.setCursorVisible(true);
                        //get the text from editText
                        String text = addText.toString();
                        //get the location where user click and add text there
                        newText.setText(text);
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
