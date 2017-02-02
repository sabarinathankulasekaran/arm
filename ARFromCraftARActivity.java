

package com.sabari.augumentedreality;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.craftar.CraftARActivity;
import com.craftar.CraftARCloudRecognition;
import com.craftar.CraftARError;
import com.craftar.CraftARItem;
import com.craftar.CraftARItemAR;
import com.craftar.CraftARResult;
import com.craftar.CraftARSDK;
import com.craftar.CraftARSDKException;
import com.craftar.CraftARSearchResponseHandler;
import com.craftar.CraftARTracking;
import com.craftar.ImageRecognition;


public class ARFromCraftARActivity extends CraftARActivity implements CraftARSearchResponseHandler, ImageRecognition.SetCollectionListener {

	private final String TAG = "ARFromCraftARActivity";

	private View mScanningLayout;

	CraftARSDK mCraftARSDK;
	CraftARTracking mTracking;
	CraftARCloudRecognition mCloudIR;

	@Override
	public void onPostCreate() {
        View mainLayout = getLayoutInflater().inflate(R.layout.activity_ar_programmatically_ar_from_craftar, null);
        setContentView(mainLayout);

        mScanningLayout = findViewById(R.id.layout_scanning);

       
        mCraftARSDK = CraftARSDK.Instance();
        mCraftARSDK.startCapture(this);

       
        mCloudIR = CraftARCloudRecognition.Instance();
        
        mCloudIR.setCraftARSearchResponseHandler(this);

      
        mCraftARSDK.setSearchController(mCloudIR.getSearchController());

        mTracking = CraftARTracking.Instance();

    }

    @Override
    public void onPreviewStarted(int frameWidth, int frameHeight) {
       
        mCloudIR.setCollection(Config.MY_COLLECTION_TOKEN, this);
    }

    @Override
    public void collectionReady() {
      
        mCraftARSDK.startFinder();
    }

    @Override
    public void setCollectionFailed(CraftARError craftARError) {
       
        Log.d(TAG, "SetCollection failed " + craftARError.getErrorMessage());
    }


    @Override
    public void searchResults(ArrayList<CraftARResult> results, long searchTime, int requestCode) {
       
        if(results.size() != 0) {
         
          
            CraftARResult result = results.get(0); //In this example, we get only the best result.

            
            CraftARItem item = result.getItem();
            if (item.isAR()) {
             
                mCraftARSDK.stopFinder();

             
                CraftARItemAR myARItem = (CraftARItemAR)item;
          
                if (error == null) {
                    mTracking.startTracking();
                    mScanningLayout.setVisibility(View.GONE);
                } else {
                    Log.e(TAG, error.getErrorMessage());
                }

            }

        }
    }

    @Override
    public void searchFailed(CraftARError craftARError, int requestCode) {
       
        Log.d(TAG,"Search failed : "+craftARError.getErrorMessage());
    }

    @Override
    public void finish() {
        
        mCraftARSDK.stopFinder();
        mTracking.stopTracking();
        mTracking.removeAllItems();
        super.finish();
    }

	@Override
	public void onCameraOpenFailed() {
		Toast.makeText(getApplicationContext(), "Camera error", Toast.LENGTH_SHORT).show();				
	}
	
}
