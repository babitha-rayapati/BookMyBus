package com.example.babit_000.bookmybus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class main extends AppCompatActivity {
    EditText et_date;
    Spinner sp_from,sp_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_date=(EditText)findViewById(R.id.editText);
        sp_from=(Spinner)findViewById(R.id.spinner);
        sp_to=(Spinner)findViewById(R.id.spinner2);
    }
    @Override
    protected void onStart() {
        super.onStart();
        EditText editText6=(EditText)findViewById(R.id.editText);
        editText6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialog dialog=new DateDialog(view);
                android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
                dialog.show(ft,"DialogPicker");

            }
        });
    }
    public void searchUsing(View v)
    {

        String from,to,date;
        date=et_date.getText().toString();
        from=sp_from.getSelectedItem().toString();
        to=sp_to.getSelectedItem().toString();
        SharedPreferences sharedPreferences=main.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(getString(R.string.pref_date),date);
        editor.commit();
        //SharedPreferences sharedPreferences=Customer.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        //un=sharedPreferences.getString(getString(R.string.pref_username),"");
        String urls="?from="+from+"&to="+to+"&date="+date;
        new BackgroundTask1(this).execute(urls);
    }

    public class BackgroundTask1 extends AsyncTask<String,Void,String> {

        private Context context;
        ProgressDialog loading;
        String resultnew="";
        public BackgroundTask1(Context cxt)
        {
            context=cxt;
        }
        private static final String GETDATA_URL="http://cabvit.esy.es/getBus.php";

        @Override
        protected void onPreExecute() {
// TODO Auto-generated method stub
            super.onPreExecute();
            loading=ProgressDialog.show(context, "Please Wait","Loading", true, true);

        }
        @Override
        protected String doInBackground(String... arg0) {
// TODO Auto-generated method stub
            BufferedReader br=null;
            StringBuffer sb;
            String s=arg0[0];
            try
            {

                URL url=new URL(GETDATA_URL+s);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                sb=new StringBuffer();
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
            }
            catch (Exception e) {
// TODO: handle exception
                return e.toString();
            }
            return sb.toString().trim();

        }
        @Override
        protected void onProgressUpdate(Void... values) {
// TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
// TODO Auto-generated method stub
            super.onPostExecute(result);
            loading.dismiss();
            Intent intent = new Intent(main.this,busDetails.class);
            intent.putExtra("result_text",result);
            startActivity(intent);

        }

    }
}
