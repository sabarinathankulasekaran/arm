package com.sabari.augumentedreality;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.craftar.CraftARSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CraftARSDK.Instance().init(getApplicationContext());
        startActivity(new Intent(getApplicationContext(),Recogonization.class));
        if(Build.VERSION.SDK_INT<=21){
            startActivity(new Intent(getApplicationContext(),Recogonization.class));
        }
        if(((ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA))!= PackageManager.PERMISSION_GRANTED)&&((ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED))){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.INTERNET},1);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

startActivity(new Intent(getApplicationContext(),Recogonization.class));

                } else {

                    Toast.makeText(getApplicationContext(),"need permission",Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }
}
