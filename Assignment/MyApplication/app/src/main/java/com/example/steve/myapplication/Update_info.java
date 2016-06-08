package com.example.steve.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class Update_info extends AppCompatActivity
{

    //create variables for each column name
    private EditText editName, editGift, editPrice, editStore, editBought;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_info);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);



        try
        {
            final DBmanager db = new DBmanager(this);
            db.open();
            editName = (EditText) findViewById(R.id.editName);
            editGift = (EditText) findViewById(R.id.editGift);
            editPrice = (EditText) findViewById(R.id.editPrice);
            editStore = (EditText) findViewById(R.id.editStore);
            editBought = (EditText) findViewById(R.id.editBought);

            //save button
            Button myList = (Button)findViewById(R.id.btn2);


            myList.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    String row_id = getIntent().getStringExtra("string1_id");
                    String insertName = editName.getText().toString();
                    String insertGift = editGift.getText().toString();
                    String insertPrice = editPrice.getText().toString();
                    String insertStore = editStore.getText().toString();
                    String insertBought = editBought.getText().toString();
                    //call update function from dbmanager
                    db.updateGift(row_id,insertName, insertGift, insertPrice, insertStore, insertBought);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Update successfull!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Update_info.this, List.class);
                    startActivity(intent);

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
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();


        if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}