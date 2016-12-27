package com.example.babit_000.bookmybus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class book extends AppCompatActivity {
    CheckBox cb,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        cb=(CheckBox)findViewById(R.id.checkBox);
        cb2=(CheckBox)findViewById(R.id.checkBox2);
        cb3=(CheckBox)findViewById(R.id.checkBox3);
        cb4=(CheckBox)findViewById(R.id.checkBox4);
        cb5=(CheckBox)findViewById(R.id.checkBox5);
        cb6=(CheckBox)findViewById(R.id.checkBox6);
        cb7=(CheckBox)findViewById(R.id.checkBox7);
        cb8=(CheckBox)findViewById(R.id.checkBox8);
        cb9=(CheckBox)findViewById(R.id.checkBox9);

        SharedPreferences sharedPreferences=book.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        date=sharedPreferences.getString(getString(R.string.pref_date),"");

        Bundle extras = getIntent().getExtras();
        String result_text = extras.getString("result_text");
        if(result_text.equalsIgnoreCase("no buses available"))
        {

        }
        else
        {
            String[] res_str = result_text.split(",", result_text.length());
            for (int i=0;i<res_str.length;i++)
            {
int mynum=Integer.parseInt(res_str[i]);
                switch (mynum)
                {
                    case 1:
                    {
                        cb.setEnabled(false);
                        break;
                    }
                    case 2:
                    {
                        cb2.setEnabled(false);
                        break;
                    }
                    case 3:
                    {
                        cb3.setEnabled(false);
                        break;
                    }
                    case 4:
                    {
                        cb4.setEnabled(false);
                        break;
                    }
                    case 5:
                    {
                        cb5.setEnabled(false);
                        break;
                    }
                    case 6:
                    {
                        cb6.setEnabled(false);
                        break;
                    }
                    case 7:
                    {
                        cb7.setEnabled(false);
                        break;
                    }
                    case 8:
                    {
                        cb8.setEnabled(false);
                        break;
                    }
                    case 9:
                    {
                        cb9.setEnabled(false);
                        break;
                    }
                }
            }
        }


    }

    public void book(View v)
    {
        String seats_sel="";
        if(cb.isChecked())
            seats_sel+="1,";
        if(cb2.isChecked())
            seats_sel+="2,";
        if(cb3.isChecked())
            seats_sel+="3,";
        if(cb4.isChecked())
            seats_sel+="4,";
        if(cb5.isChecked())
            seats_sel+="5,";
        if(cb6.isChecked())
            seats_sel+="6,";
        if(cb7.isChecked())
            seats_sel+="7,";
        if(cb8.isChecked())
            seats_sel+="8,";
        if(cb9.isChecked())
            seats_sel+="9,";


String busno="bus1";
        String urlSuffix = "?selected="+seats_sel+"&busno="+busno+"&date="+date;

        //Toast.makeText(this,seats_sel,Toast.LENGTH_LONG).show();
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(urlSuffix);

        /*cb.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setClickable(false);*/



    }
}
