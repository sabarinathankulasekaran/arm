package com.sabari.augumentedreality;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.craftar.CraftARActivity;
import com.craftar.CraftARCloudRecognition;
import com.craftar.CraftARError;
import com.craftar.CraftARImage;
import com.craftar.CraftARItem;
import com.craftar.CraftARResult;
import com.craftar.CraftARSDK;
import com.craftar.CraftARSearchResponseHandler;
import com.craftar.CraftARTracking;
import com.craftar.ImageRecognition;

import java.util.ArrayList;
import java.util.Iterator;

public class Recogonization extends CraftARActivity implements CraftARSearchResponseHandler ,ImageRecognition.SetCollectionListener{


    CraftARSDK cs;
    CraftARTracking ct;
    CraftARCloudRecognition cc;
    Button b,b1,b2;
    AnimationDrawable a;
    int r=0;
    String uuid;
    @Override
    public void onPostCreate() {



        View v=getLayoutInflater().inflate(R.layout.activity_recogonization,null);
        setContentView(v);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

        ColorDrawable f = new ColorDrawable(Color.BLACK);
        ColorDrawable f2 = new ColorDrawable(Color.GRAY);

        a = new AnimationDrawable();
        a.addFrame(f, 100);
        a.addFrame(f2, 100);
        a.setOneShot(false);
        rl.setBackgroundDrawable(a);
        a.start();
        cs=CraftARSDK.Instance();
        ct=CraftARTracking.Instance();
        cs.startCapture(this);
        b=(Button)findViewById(R.id.button);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cs.singleShotSearch();
            }

        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cs.getCamera().restartCapture();
            }
        });
       b2.setVisibility(View.INVISIBLE);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b=new Bundle();
                b.putString("uuid",uuid);
                Intent in=new Intent(getApplicationContext(),info.class);
                in.putExtras(b);
                startActivity(in);

            }
        });

    }

    @Override
    public void onCameraOpenFailed() {

    }

    @Override
    public void onPreviewStarted(int i, int i1) {
cc=CraftARCloudRecognition.Instance();
        cc.setCraftARSearchResponseHandler(this);
        cc.setCollection("e2c33efda3dd4f55",this);
        cs.setSearchController(cc.getSearchController());
    }

    @Override
    public void searchResults(ArrayList<CraftARResult> arrayList, long l, int i) {
cs.getCamera().stopCapture();
        if(arrayList.size()==0){
            Toast.makeText(getApplicationContext(),"No result found",Toast.LENGTH_LONG).show();
        }
        else
        {

            Iterator it=arrayList.iterator();
            for(int j=0;j<arrayList.size();j++)
             {
                CraftARResult cr = arrayList.get(j);
                CraftARItem ci = cr.getItem();
                if(!ci.isAR()) {

                    //   Toast.makeText(getApplicationContext(),cr.getScore(),Toast.LENGTH_LONG).show();
uuid=ci.getItemId();
                    a.stop();
                    b2.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    @Override
    public void searchFailed(CraftARError craftARError, int i) {

    }

    @Override
    public void collectionReady() {

    }

    @Override
    public void setCollectionFailed(CraftARError craftARError) {

    }


}
