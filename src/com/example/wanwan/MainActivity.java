package com.example.wanwan;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	private final double DEFAULT_TIMES = 1.0;
	private final double EASY_EX_TIMES = 1.11;
	private final double MIDDLE_EX_TIMES = 1.67;
	private final double HEAVY_EX_TIMES = 2.22;
	private final double AGE_BORNED_TIMES = 2.22;
	private final double AGE_LT4MONTH_TIMES = 1.67;
	private final double AGE_LT9MONTH_TIMES = 1.39;
	private final double AGE_LT12MONTH_TIMES = 1.11;
	private final double AGE_ELDER_TIMES = 0.78;
	private final double PREGNANT_LT4WEEKS_TIMES = 1.11;
	private final double PREGNANT_LT6WEEKS_TIMES = 1.39;
	private final double PREGNANT_LAST_TIMES = 1.67;
	private final double MANUAL_LESSFOOD_TIMES = 0.6;
	private final double MANUAL_MOREFOOD_TIMES = 1.4;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        EditText dogWeight = (EditText) findViewById(R.id.dogWeight);
        dogWeight.setFocusable(true);
        dogWeight.setFocusableInTouchMode(true);
        
        Button btnCalcFood = (Button) findViewById(R.id.btnCalcFood);
        btnCalcFood.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 避妊・去勢倍率
				double bcTimes = DEFAULT_TIMES;
				CheckBox chkBox = (CheckBox) findViewById(R.id.checkBC);
				if (chkBox.isChecked()) {
					bcTimes = 0.89;
				}
				
				// 運動量倍率
				double exTimeTimes = DEFAULT_TIMES;
				Spinner exTime = (Spinner) findViewById(R.id.dogExTimePDay);
				if ("軽い運動・労働（3時間未満）".equals(exTime.getSelectedItem().toString())) {
					exTimeTimes = EASY_EX_TIMES;
				} else if ("中程度の運動・労働（6時間未満）".equals(exTime.getSelectedItem().toString())) {
					exTimeTimes = MIDDLE_EX_TIMES;
				} else if ("重労働（6時間以上)".equals(exTime.getSelectedItem().toString())) {
					exTimeTimes = HEAVY_EX_TIMES;
				}
				
				// 年齢倍率
				double ageTimes = DEFAULT_TIMES;
				Spinner dogAge = (Spinner) findViewById(R.id.dogAgeCategory);
				if ("授乳期".equals(dogAge.getSelectedItem().toString())) {
					ageTimes = AGE_BORNED_TIMES;
				} else if ("離乳～生後4ヶ月未満".equals(dogAge.getSelectedItem().toString())) {
					ageTimes = AGE_LT4MONTH_TIMES;
				} else if ("生後4ヶ月～10ヶ月未満".equals(dogAge.getSelectedItem().toString())) {
					ageTimes = AGE_LT9MONTH_TIMES;
				} else if ("生後10ヶ月～12ヶ月".equals(dogAge.getSelectedItem().toString())) {
					ageTimes = AGE_LT12MONTH_TIMES;
				} else if ("高齢犬".equals(dogAge.getSelectedItem().toString())) {
					ageTimes = AGE_ELDER_TIMES;
				}
				
				// 妊娠倍率
				double pregTimes = DEFAULT_TIMES;
				Spinner pregnant = (Spinner) findViewById(R.id.dogSexCategory);
				if ("メス（妊娠1～4週）".equals(pregnant.getSelectedItem().toString())) {
					pregTimes = PREGNANT_LT4WEEKS_TIMES;
				} else if ("メス（妊娠5～6週）".equals(pregnant.getSelectedItem().toString())) {
					pregTimes = PREGNANT_LT6WEEKS_TIMES;
				} else if ("メス（妊娠後期）".equals(pregnant.getSelectedItem().toString())) {
					pregTimes = PREGNANT_LAST_TIMES;
				}
				
				// 体重取得
				EditText dogWeightText = (EditText) findViewById(R.id.dogWeight);
				double dogWeight = 0.0;
				Log.d("main", "dog weight: " + dogWeightText.getText());
				try {
					dogWeight = Double.parseDouble(dogWeightText.getText().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (dogWeight == 0.0) {
					// TODO error process
					Log.e("main", "dog weight must > 0");
				}
				
				// 給餌方針倍率
				double ownerTimes = DEFAULT_TIMES;
				Spinner owner = (Spinner) findViewById(R.id.weightDirection);
				if ("ダイエットさせたい".equals(owner.getSelectedItem().toString())) {
					ownerTimes = MANUAL_LESSFOOD_TIMES;
				} else if ("体重をつけさせたい".equals(owner.getSelectedItem().toString())) {
					ownerTimes = MANUAL_MOREFOOD_TIMES;
				}
				
				// カロリー計算
				double totalCarolie = Math.pow(dogWeight, 3);
				totalCarolie = Math.sqrt(totalCarolie);
				totalCarolie = Math.sqrt(totalCarolie);
				totalCarolie *= 110.0;
				totalCarolie *= bcTimes; 
				totalCarolie *= exTimeTimes;
				totalCarolie *= ageTimes;
				totalCarolie *= pregTimes;
				totalCarolie *= ownerTimes; 
				
				// String item = (String) dogAge.getSelectedItem();
				Log.d("main", "final carolie p day: " + String.valueOf(totalCarolie));
				
				Intent intent = new Intent(MainActivity.this, CalcFoodResultActivity.class);
				intent.putExtra("totalCarolie", String.valueOf((int)totalCarolie));
				startActivityForResult(intent, 1);
			}
		});
        
        /*
        Button btnDogProfile = (Button) findViewById(R.id.btnDogProfileHeader);
        btnDogProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout dogProfile = (LinearLayout) findViewById(R.id.lytDogProfile);
				ImageView ic = (ImageView) findViewById(R.id.icDogProfileHeader);
				if (dogProfile.getVisibility() == View.GONE){
					dogProfile.setVisibility(View.VISIBLE);
					ic.setImageResource(R.drawable.ic_up_arrow);
				} else {
					dogProfile.setVisibility(View.GONE);
					ic.setImageResource(R.drawable.ic_down_arrow);
				}
			}
		});
		*/
        
        /*
        Button btnHistory = (Button) findViewById(R.id.btnPreviousFoodHeader);
        btnHistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout history = (LinearLayout) findViewById(R.id.lyPreviousFood);
				ImageView ic = (ImageView) findViewById(R.id.icPreviousFoodHeader);
				if (history.getVisibility() == View.GONE){
					history.setVisibility(View.VISIBLE);
					ic.setImageResource(R.drawable.ic_up_arrow);
				} else {
					history.setVisibility(View.GONE);
					ic.setImageResource(R.drawable.ic_down_arrow);
				}
			}
		});
		*/
    }
    
    public void onClickDogProfileHeader(View v) {
    	LinearLayout history = (LinearLayout) findViewById(R.id.lytDogProfile);
		ImageView ic = (ImageView) findViewById(R.id.icDogProfileHeader);
		if (history.getVisibility() == View.GONE){
			history.setVisibility(View.VISIBLE);
			ic.setImageResource(R.drawable.ic_up_arrow);
		} else {
			history.setVisibility(View.GONE);
			ic.setImageResource(R.drawable.ic_down_arrow);
		}
    }
    
    public void onClickPreviousFoodHeader(View v) {
    	LinearLayout history = (LinearLayout) findViewById(R.id.lyPreviousFood);
		ImageView ic = (ImageView) findViewById(R.id.icPreviousFoodHeader);
		if (history.getVisibility() == View.GONE){
			history.setVisibility(View.VISIBLE);
			ic.setImageResource(R.drawable.ic_up_arrow);
		} else {
			history.setVisibility(View.GONE);
			ic.setImageResource(R.drawable.ic_down_arrow);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
