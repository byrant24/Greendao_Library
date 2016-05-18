package com.example.anmol.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Anmol on 11/05/16.
 */
public class SplashScreen extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences m_sharedPref;
        m_sharedPref = getSharedPreferences(
                "com.example.anmol.myapplication", Context.MODE_PRIVATE);

        String emailId = m_sharedPref.getString("email_id", "");

        if(emailId.equals(""))
        {
            Intent intent = new Intent(SplashScreen.this, GooglePlusLoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
    }
