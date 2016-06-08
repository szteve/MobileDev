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
import android.widget.Toast;

import java.sql.SQLException;

public class Name_info extends AppCompatActivity
{
    DBmanager db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_infodesign);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        //display information of all row based on the list id
        try
        {
            final String id = getIntent().getStringExtra("row_id");
            DBmanager db = new DBmanager(this);
            //open database
            db.open();
            String row_id = getIntent().getStringExtra("string1_id");
            ListView Var1 = (ListView) findViewById(R.id.List_id);



            //create cursor to retrieve columns based on id
            Cursor mcursor = db.GetInfo(row_id);
            final String[] var2 = new String[]{db.LIST_NAME,db.LIST_GIFT,db.LIST_PRICE,db.LIST_STORE,db.LIST_BOUGHT};
            int[] var3 = new int[]{R.id.Listname,R.id.Listgift,R.id.Listprice,R.id.Liststore,R.id.Listbought};

            //display all rows in table for specific id
            SimpleCursorAdapter myadapter = new SimpleCursorAdapter(this, R.layout.name_infodesign2, mcursor, var2, var3, 0);
            Var1.setAdapter(myadapter);
            Var1.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {

                }
            });

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    private void setActionBar(Toolbar toolbar)
    {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.name_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //3 dot menu
        if (id == R.id.update)
        {
            String row_id = getIntent().getStringExtra("string1_id");
            Intent intent = new Intent(Name_info.this, Update_info.class);
            intent.putExtra("string1_id", row_id);
            startActivity(intent);
        }
        if (id == R.id.delete)
        {

            //deletes the info currently selected
            try {
                String row_id = getIntent().getStringExtra("string1_id");
                db = new DBmanager(this);
                db.open();
                db.deleteRow(row_id);
                db.close();
                Toast.makeText(Name_info.this, "Person deleted" + row_id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Name_info.this, List.class);
                startActivity(intent);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }

        if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}