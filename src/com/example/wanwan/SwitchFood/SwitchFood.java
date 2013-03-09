package com.example.wanwan.SwitchFood;

import com.example.wanwan.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;

public class SwitchFood extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switch_food);
		
		Spinner spinner = (Spinner) findViewById(R.id.SFAlertTime);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String item = (String) (((Spinner) parent).getSelectedItem());
				Log.d("SwitchFood", ((Spinner) parent).getSelectedItem() + " is selected.");
				TextView textView = (TextView) view;
				Log.d("SwitchFood", "parent.getSlectedItem: " + item);
				Log.d("SwitchFood", "view.getText: " + textView.getText());
				Log.d("SwitchFood", "position: " + String.valueOf(position));
				Log.d("SwitchFood", "id: " + String.valueOf(id));
				if (position == 0) {
					// îNóÓÇ≈ê›íË
					LinearLayout alertAgeLyt = (LinearLayout) findViewById(R.id.SFAlertAgeLyt);
					alertAgeLyt.setVisibility(View.VISIBLE);
					LinearLayout alertWghtLyt = (LinearLayout) findViewById(R.id.SFAlertWeightLyt);
					alertWghtLyt.setVisibility(View.GONE);
					LinearLayout alertPrdLyt = (LinearLayout) findViewById(R.id.SFAlertPeriodLyt);
					alertPrdLyt.setVisibility(View.GONE);
				} else if (position == 1) {
					// ëÃèdÇ≈ê›íË
					LinearLayout alertAgeLyt = (LinearLayout) findViewById(R.id.SFAlertAgeLyt);
					alertAgeLyt.setVisibility(View.GONE);
					LinearLayout alertWghtLyt = (LinearLayout) findViewById(R.id.SFAlertWeightLyt);
					alertWghtLyt.setVisibility(View.VISIBLE);
					LinearLayout alertPrdLyt = (LinearLayout) findViewById(R.id.SFAlertPeriodLyt);
					alertPrdLyt.setVisibility(View.GONE);
				} else if (position == 2) {
					// ä˙ä‘Ç≈ê›íË
					LinearLayout alertAgeLyt = (LinearLayout) findViewById(R.id.SFAlertAgeLyt);
					alertAgeLyt.setVisibility(View.GONE);
					LinearLayout alertWghtLyt = (LinearLayout) findViewById(R.id.SFAlertWeightLyt);
					alertWghtLyt.setVisibility(View.GONE);
					LinearLayout alertPrdLyt = (LinearLayout) findViewById(R.id.SFAlertPeriodLyt);
					alertPrdLyt.setVisibility(View.VISIBLE);
				} else {
					LinearLayout alertAgeLyt = (LinearLayout) findViewById(R.id.SFAlertAgeLyt);
					alertAgeLyt.setVisibility(View.GONE);
					LinearLayout alertWghtLyt = (LinearLayout) findViewById(R.id.SFAlertWeightLyt);
					alertWghtLyt.setVisibility(View.GONE);
					LinearLayout alertPrdLyt = (LinearLayout) findViewById(R.id.SFAlertPeriodLyt);
					alertPrdLyt.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
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
    		intent = new Intent(SwitchFood.this, com.example.wanwan.Top.Top.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showHistory:
    		Log.d("top", "option 'showHistory' is clicked.");
    		intent = new Intent(SwitchFood.this, com.example.wanwan.ShowHistory.ShowHistory.class);
    		startActivity(intent);
    		break;

    	case R.id.inputWeight:
    		Log.d("top", "option 'inputWeight' is clicked.");
    		intent = new Intent(SwitchFood.this, com.example.wanwan.InputWeight.InputWeight.class);
    		startActivity(intent);
    		break;

    	case R.id.inputFood:
    		Log.d("top", "option 'inputFood' is clicked.");
    		intent = new Intent(SwitchFood.this, com.example.wanwan.InputFood.InputFood.class);
    		startActivity(intent);
    		break;

    	case R.id.switchFood:
    		Log.d("top", "option 'switchFood' is clicked.");
    		break;

    	case R.id.registerFood:
    		Log.d("top", "option 'registerFood' is clicked.");
    		intent = new Intent(SwitchFood.this, com.example.wanwan.RegisterFood.RegisterFood.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.registerDog:
    		Log.d("top", "option 'registerDog' is clicked.");
    		intent = new Intent(SwitchFood.this, com.example.wanwan.RegisterDog.RegisterDog.class);
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
