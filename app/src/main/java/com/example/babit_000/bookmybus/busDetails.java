package com.example.babit_000.bookmybus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class busDetails extends AppCompatActivity {
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        Bundle extras = getIntent().getExtras();
        String result_text = extras.getString("result_text");


        String[] res_str = result_text.split(";", result_text.length());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list2, res_str);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SharedPreferences sharedPreferences=busDetails.this.getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
                date=sharedPreferences.getString(getString(R.string.pref_date),"");
                abc();

                /*Intent intent = new Intent(busDetails.this,book.class);
                startActivity(intent);*/
            }
        });
    }
    public void abc()
    {
        String urls="?date="+date;
        new BackgroundTask2(this).execute(urls);
    }

    public class BackgroundTask2 extends AsyncTask<String,Void,String> {

        private Context context;
        ProgressDialog loading;
        String resultnew="";
        public BackgroundTask2(Context cxt)
        {
            context=cxt;
        }
        private static final String GETDATA_URL="http://cabvit.esy.es/invalidateBus.php";

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
            Intent intent = new Intent(busDetails.this,book.class);
            intent.putExtra("result_text",result);
            startActivity(intent);

        }

    }
}
