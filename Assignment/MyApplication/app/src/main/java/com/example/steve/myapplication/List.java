package com.example.steve.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import java.sql.SQLException;

public class List extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //set list design linking to xml file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_design);

        //set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);


        try
        {
            //set database
            DBmanager db = new DBmanager(this);
            //open database
            db.open();
            //display list
            ListView Var1 = (ListView) findViewById(R.id.List_id);
            //set cursors to database
            Cursor mcursor = db.displayList();


            //create string for list names
            final String[] list = new String[]{db.LIST_NAME};

            //make name clickable to take to further info
            int[] var1 = new int[]{R.id.Listname};
            //display list names using simple cursor adapter
            SimpleCursorAdapter myadapter = new SimpleCursorAdapter(this, R.layout.list_design2, mcursor, list, var1, 0);
            Var1.setAdapter(myadapter);
            //set intent so that list changes to next screen to the name info screen
            Var1.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Intent intent = new Intent(List.this, Name_info.class);
                    String string1 = new String(Long.toString(id));
                    intent.putExtra("string1_id", string1);
                    startActivity(intent);
                }
            });

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //make toolbar work
    private void setActionBar(Toolbar toolbar)
    {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //create 3 dot menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        //make button redirect using intent
        if (id == R.id.action_settings)
        {
            Intent intent = new Intent(List.this, EditField.class);
            startActivity(intent);
        }


        if (id == android.R.id.home)
        {
            Intent intent2 = new Intent(List.this, MainActivity.class);
            startActivity(intent2);
        }

        return super.onOptionsItemSelected(item);
    }

}
