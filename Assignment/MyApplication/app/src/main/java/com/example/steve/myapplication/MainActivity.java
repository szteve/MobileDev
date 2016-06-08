package com.example.steve.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    //create toolbar/action bar help from http://developer.android.com/reference/android/widget/Toolbar.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //make buttons work in main page
        Button myList = (Button)findViewById(R.id.btn1);
        myList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List.class);
                startActivity(intent);
            }
        });

        //make button redirect to internet got from http://stackoverflow.com/questions/4930228/open-a-url-on-click-of-ok-button-in-android
        Button mypic = (Button)findViewById(R.id.btn2);
        mypic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://www.google.ie/imghp?hl=en&tab=wi&ei=5IpbVvWZKsW4PMvxk7AP&ved=0EKouCBIoAQ");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        });

        //make button redirect to internet
        Button mybrowse = (Button)findViewById(R.id.btn3);
        mybrowse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Uri uri = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }


}
