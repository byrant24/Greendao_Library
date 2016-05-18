package com.example.anmol.myapplication;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
;

public class MainActivity extends AppCompatActivity implements RecycleAdaptor.EditNoteInterface, NoteFragment.AddNoteInterface {

    Toolbar toolbar;
    TextView nameTV;
    SharedPreferences m_sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        nameTV=(TextView)findViewById(R.id.name);


        m_sharedPref = getSharedPreferences(
                "com.example.anmol.myapplication", Context.MODE_PRIVATE);

        String name = m_sharedPref.getString("account_name", "");
        nameTV.setText(name);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            toolbar.inflateMenu(R.menu.sign_out_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.sign_out:

                        SharedPreferences prefs;
                        prefs = getSharedPreferences(
                                "com.example.anmol.myapplication", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("email_id", "");
                        editor.putString("account_name", "");

                        editor.commit();


                        Intent intent = new Intent(MainActivity.this, GooglePlusLoginActivity.class);
                        startActivity(intent);
                        finish();

                        return true;

                    default:
                        break;
                }
                return false;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NoteFragment noteFragment = NoteFragment.newInstance();
        fragmentTransaction.replace(R.id.main_frag, noteFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void editNote(int id,String note) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EditNoteFragment editNoteFragment = EditNoteFragment.newInstance(id,note);
        fragmentTransaction.replace(R.id.main_frag, editNoteFragment).addToBackStack(getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public void addNote(String email) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddNoteFragment addNoteFragment = AddNoteFragment.newInstance(email);
        fragmentTransaction.replace(R.id.main_frag, addNoteFragment).addToBackStack(getClass().getName());
        fragmentTransaction.commit();
    }

}


