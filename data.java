package com.sabari.augumentedreality;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sabarinathan on 1/2/2017.
 */
public class data {
    JSONObject jo;
    public JSONObject getdata(String a) throws JSONException {
        switch (a){
            case "ac27a0802f9647f3bff353dab16e278b":
                jo=new JSONObject();
                jo.put("name","Scissors");
                jo.put("use","It is used to cut soft things");
                break;
            default:break;
        }
        return jo;





    }
}
