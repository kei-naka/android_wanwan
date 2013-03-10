package com.example.wanwan.Top;

import com.example.wanwan.R;
import com.example.wanwan.model.wanwanDBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Top extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top);
        
        wanwanDBHelper helper = new wanwanDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor Prof = null;
        try {
        	/*
        	 * SELECT
        	 *   name, birthday, sex, breed, nrmwght_min, nrmwght_max
        	 * FROM
        	 *   dog_profile
        	 * WHERE
        	 *   active_flg = '1';
        	 */
        	Prof = db.query(wanwanDBHelper.getTableDogInfo()
        			, new String[] {"name", "birthday", "sex", "breed", "nrmwght_min", "nrmwght_max"}
        			, "active_flg = '1'", null, null, null, null);
        	
        	if (Prof.getCount() == 0) {
        		Prof.close();
        		db.close();
        		
        		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        		dialog.setTitle(R.string.app_name);
        		dialog.setMessage(
        			getString(R.string.NoSomething, "プロフィール")
        			+ getString(R.string.MakeSomething, "プロフィール"));
        		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        			@Override
        			public void onClick(DialogInterface _dialog, int which) {
        				Intent intent = new Intent(Top.this, com.example.wanwan.RegisterDog.RegisterDog.class);
        				startActivity(intent);
        				
        			}
        		});
        		dialog.create();
        		dialog.show();
        	} else {
	    		// 体重は後で
        		/*
	    		Cursor c = db.query(DatabaseOpenHelper.getTableDogProfile()
	    			, new String[] {"name", "birthday", "sex", "breed", "nrmwght_min", "nrmwght_max"}
	    			, "active_flg = '1'", null, null, null, null);
	
	    		if (c.getCount() == 0) {
	    			Log.e("TOP", "profile exists, but all active_flag != 1");
	    		}
	    		*/
        		
	    		if (Prof.getCount() > 1) {
	    			Log.e("TOP", "more than 1 profile has active_flag == 1");
	    		}
	
	    		Prof.moveToFirst();
	
	    		TextView nameTV = (TextView) findViewById(R.id.TOPName);
	    		TextView birthdayTV = (TextView) findViewById(R.id.TOPBirthday);
	    		TextView sexTV = (TextView) findViewById(R.id.TOPSex);
	    		TextView breedTV = (TextView) findViewById(R.id.TOPBreed);
	    		TextView nrmwghtTV = (TextView) findViewById(R.id.TOPNormalWeight);
	
	    		nameTV.setText(Prof.getString(0));
	    		birthdayTV.setText(Prof.getString(1));
	    		sexTV.setText(Prof.getString(2));
	    		breedTV.setText(Prof.getString(3));
	    		String nrmwght = "";
	    		if (Prof.getString(4).length() > 0 || Prof.getString(5).length() > 0) {
	    			nrmwght = getString(R.string.betweenKg, Prof.getString(4), Prof.getString(5));
	    		}
	    		nrmwghtTV.setText(nrmwght);
        	}

        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	db.close();
        	if (Prof != null) {
        		Prof.close();
        	}
        }
    }
    
    public void TOPImgListener(View view) {
    	Intent intent = new Intent(Top.this, com.example.wanwan.Top.EditPhoto.class);
    	startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.common_static, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
    	switch (item.getItemId()) {
    	case R.id.gotoTop:
    		Log.d("top", "option 'gotoTop' is clicked.");
    		break;
    		
    	case R.id.showHistory:
    		Log.d("top", "option 'showHistory' is clicked.");
    		intent = new Intent(Top.this, com.example.wanwan.ShowHistory.ShowHistory.class);
    		startActivity(intent);
    		break;

    	case R.id.inputWeight:
    		Log.d("top", "option 'inputWeight' is clicked.");
    		intent = new Intent(Top.this, com.example.wanwan.InputWeight.InputWeight.class);
    		startActivity(intent);
    		break;

    	case R.id.inputFood:
    		Log.d("top", "option 'inputFood' is clicked.");
    		intent = new Intent(Top.this, com.example.wanwan.InputFood.InputFood.class);
    		startActivity(intent);
    		break;

    	case R.id.switchFood:
    		Log.d("top", "option 'switchFood' is clicked.");
    		intent = new Intent(Top.this, com.example.wanwan.SwitchFood.SwitchFood.class);
    		startActivity(intent);
    		break;

    	case R.id.registerFood:
    		Log.d("top", "option 'registerFood' is clicked.");
    		intent = new Intent(Top.this, com.example.wanwan.RegisterFood.RegisterFood.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.registerDog:
    		Log.d("top", "option 'registerDog' is clicked.");
    		intent = new Intent(Top.this, com.example.wanwan.RegisterDog.RegisterDog.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showTemplate:
    		Log.d("top", "option 'showTemplate' is clicked.");
    		break;
    		
    	default:
    		break;
    	}
    	return true;
    }
}
