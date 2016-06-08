package com.example.steve.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class EditField extends AppCompatActivity
{

    //pass values into class
    private EditText editName, editGift, editPrice, editStore, editBought;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_design);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        //inserts into table


        try
        {
            final DBmanager db = new DBmanager(this);
            //open database
            db.open();

            //show table fields
            editName = (EditText) findViewById(R.id.editName);
            editGift = (EditText) findViewById(R.id.editGift);
            editPrice = (EditText) findViewById(R.id.editPrice);
            editStore = (EditText) findViewById(R.id.editStore);
            editBought = (EditText) findViewById(R.id.editBought);

            //make save button functional
            Button myList = (Button)findViewById(R.id.btn2);


            myList.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    //insert values into database
                    String row_id = getIntent().getStringExtra("string1_id");
                    String insertName = editName.getText().toString();
                    String insertGift = editGift.getText().toString();
                    String insertPrice = editPrice.getText().toString();
                    String insertStore = editStore.getText().toString();
                    String insertBought = editBought.getText().toString();
                    db.insertGift(insertName, insertGift, insertPrice, insertStore, insertBought);
                    //close database
                    db.close();
                    //brief toast message
                    Toast.makeText(getApplicationContext(), "New Entry successfull!",
                            Toast.LENGTH_SHORT).show();
                    //go back to the list page after insert values
                    Intent intent = new Intent(EditField.this, List.class);
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
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //
        if (id == R.id.action_settings)
        {
            return true;
        }

        //back button to go to previous screen
        if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}