package com.example.wanwan.InputFood;

import com.example.wanwan.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class InputFood extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_food);
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
    		intent = new Intent(InputFood.this, com.example.wanwan.Top.Top.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showHistory:
    		Log.d("top", "option 'showHistory' is clicked.");
    		intent = new Intent(InputFood.this, com.example.wanwan.ShowHistory.ShowHistory.class);
    		startActivity(intent);
    		break;

    	case R.id.inputWeight:
    		Log.d("top", "option 'inputWeight' is clicked.");
    		intent = new Intent(InputFood.this, com.example.wanwan.InputWeight.InputWeight.class);
    		startActivity(intent);
    		break;

    	case R.id.inputFood:
    		Log.d("top", "option 'inputFood' is clicked.");
    		break;

    	case R.id.switchFood:
    		Log.d("top", "option 'switchFood' is clicked.");
    		intent = new Intent(InputFood.this, com.example.wanwan.SwitchFood.SwitchFood.class);
    		startActivity(intent);
    		break;

    	case R.id.registerFood:
    		Log.d("top", "option 'registerFood' is clicked.");
    		intent = new Intent(InputFood.this, com.example.wanwan.RegisterFood.RegisterFood.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.registerDog:
    		Log.d("top", "option 'registerDog' is clicked.");
    		intent = new Intent(InputFood.this, com.example.wanwan.RegisterDog.RegisterDog.class);
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
