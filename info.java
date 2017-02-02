package com.sabari.augumentedreality;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class info extends AppCompatActivity {
    JSONObject jo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        try {
             jo=new data().getdata(getIntent().getExtras().getString("uuid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView lv=(ListView)findViewById(R.id.listView);
        String []s={"name","use"};
        ArrayAdapter a=new ArrayAdapter(this,R.layout.row,s);
        lv.setAdapter(a);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        try {
                            Snackbar.make((RelativeLayout)findViewById(R.id.rv),jo.getString("name"),Snackbar.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            }
        });


    }
}
