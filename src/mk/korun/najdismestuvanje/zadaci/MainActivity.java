package mk.korun.najdismestuvanje.zadaci;

import mk.korun.najdismestuvanje.R;
import mk.korun.najdismestuvanje.R.id;
import mk.korun.najdismestuvanje.R.layout;
import mk.korun.najdismestuvanje.R.menu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SharedPreferences sharedPrefs = getSharedPreferences("najdisme_prefs", Context.MODE_PRIVATE);
        if(!sharedPrefs.contains("firstRun")) {
        	Toast.makeText(this, "Welcome, this is the first time you run the application.", Toast.LENGTH_SHORT).show();
        	Editor spEditor = sharedPrefs.edit();
            spEditor.putBoolean("firstRun", false);
            spEditor.commit();
        } else {
        	Toast.makeText(this, "Welcome again.", Toast.LENGTH_SHORT).show();
        }
        
        // Dynamically add fragment to FrameLayout
        FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.frame_container, new MainFragment());
		fragmentTransaction.commit();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    

}
