package com.example.wanwan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcFoodResultActivity extends Activity {
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.calc_food_result);
		
		Bundle data = getIntent().getExtras();
		if (data != null) {
			String totalCarolieResult = (String) data.getCharSequence("totalCarolie");
			
			TextView totalCarolie = (TextView) findViewById(R.id.totalCarolie);
			totalCarolie.setText(totalCarolieResult + " kcal");
			
			TextView eachCarolie = (TextView) findViewById(R.id.eachCarolie);
			int eachCarolieResult = Integer.parseInt(totalCarolieResult) / 3;
			eachCarolie.setText(String.valueOf(eachCarolieResult) + " kcal");
		}
		
		Button btn = (Button) findViewById(R.id.btnInputCurFoodInfo);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CalcFoodResultActivity.this, InputCurrentFoodActivity.class);
				startActivity(intent);
			}
		});
	}
	
	/* @Override
	public void startActivity(Intent intent) {
		super.finish();
		super.startActivity(intent);
	} */
}
