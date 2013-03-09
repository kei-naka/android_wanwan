package com.example.wanwan.RegisterDog;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.wanwan.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class RegisterDog extends Activity {
	
	EditText RDBirthday;
	EditText RDSex;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_dog);
		
		// 誕生日のダイアログピッカーを設定するためにここで要素を取得
		RDBirthday = (EditText) findViewById(R.id.RDBirthday);
		
		// 性別の複数選択を設定するためにここで要素を取得
		RDSex = (EditText) findViewById(R.id.RDSex);
		
		// 次へボタン
		Button RDNextBtn = (Button) findViewById(R.id.RDNextBtn);
		RDNextBtn.setOnClickListener(RDNBlistener);
	}
	
	// 誕生日のダイアログピッカーリスナー
	public void RDBirthdayListener(View view) {
		Log.d("RegisterDog", "RDBirthdayListener is clicked.");
		
		// 初期値は 1982/2/13 に設定
		int year = 1983;
		int month = 1;	// 0 - 12
		int day = 13;
		
		if (RDBirthday.getText().length() > 0) {
				// ! "".equals(RDBirthday.getText())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date birthday = sdf.parse(RDBirthday.getText().toString());
				year = birthday.getYear() + 1900;
				month = birthday.getMonth();
				day = birthday.getDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		DatePickerDialog datePickerDialog = new DatePickerDialog(this, DPDlistener, year, month, day);
		datePickerDialog.show();
	}
	
	// ダイアログピッカーのリスナー
	OnDateSetListener DPDlistener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			monthOfYear++;
			Log.d("RegisterDog", year + "/" + monthOfYear + "/" + dayOfMonth + " is set.");
			RDBirthday.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
		}
	};
	
	// 性別の複数選択リスナー
	public void RDSexListener(View view) {
		AlertDialog.Builder sexDialog = new AlertDialog.Builder(RegisterDog.this);
		sexDialog.setTitle(getString(R.string.RDSexHdr, "性別"));
		sexDialog.setSingleChoiceItems(R.array.RDSexArr, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					RDSex.setText("");
					break;
				case 1:
					RDSex.setText("オス");
					break;
				case 2:
					RDSex.setText("メス");
					break;
				}
			}
		});
		sexDialog.setPositiveButton("close", null);
		sexDialog.create();
		sexDialog.show();
	}
	
	// 次へボタンリスナー
	OnClickListener RDNBlistener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Log.d("RD", "RDNextBtn is clicked.");
			// なまえ
			EditText RDName = (EditText) findViewById(R.id.RDName);
			
			// 誕生日はonCreateで取得済み
			// RDBirthday = (EditText) findViewById(R.id.RDBirthday);
			
			// 性別はonCreateで取得済み
			// RDSex = (EditText) findViewById(R.id.RDSex);
			
			// 犬種
			EditText RDBreed = (EditText) findViewById(R.id.RDBreed);
			
			// 適正体重
			EditText RDNrmWeightFrom = (EditText) findViewById(R.id.RDNrmWeightFrom);
			EditText RDNrmWeightTo = (EditText) findViewById(R.id.RDNrmWeightTo);
			
			// なまえが空の場合は先に進まない
			if ("".equals(RDName.getText().toString())) {
				// dialog
				AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterDog.this);
				dialog.setTitle(R.string.errInput);
				dialog.setMessage(getString(R.string.errEmpty, "なまえ"));
				dialog.setIcon(R.drawable.error);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int which) {}
				});
				dialog.create();
				dialog.show();
			} else {
				// set intent and go to next activity
				Intent intent = new Intent(RegisterDog.this, com.example.wanwan.RegisterDog.RegisterDogConfirm.class);
				intent.putExtra("RDName", RDName.getText());
				intent.putExtra("RDBirthday", RDBirthday.getText());
				intent.putExtra("RDSex", RDSex.getText());
				intent.putExtra("RDBreed", RDBreed.getText());
				intent.putExtra("RDNrmWeightFrom", RDNrmWeightFrom.getText());
				intent.putExtra("RDNrmWeightTo", RDNrmWeightTo.getText());
				startActivity(intent);
			}
		}
	};

	// メニューをインフレート
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.common_static, menu);
    	return true;
    }
    
	// メニューアクション
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
    	switch (item.getItemId()) {
    	case R.id.gotoTop:
    		Log.d("top", "option 'gotoTop' is clicked.");
    		intent = new Intent(RegisterDog.this, com.example.wanwan.Top.Top.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showHistory:
    		Log.d("top", "option 'showHistory' is clicked.");
    		intent = new Intent(RegisterDog.this, com.example.wanwan.ShowHistory.ShowHistory.class);
    		startActivity(intent);
    		break;

    	case R.id.inputWeight:
    		Log.d("top", "option 'inputWeight' is clicked.");
    		intent = new Intent(RegisterDog.this, com.example.wanwan.InputWeight.InputWeight.class);
    		startActivity(intent);
    		break;

    	case R.id.inputFood:
    		Log.d("top", "option 'inputFood' is clicked.");
    		intent = new Intent(RegisterDog.this, com.example.wanwan.InputFood.InputFood.class);
    		startActivity(intent);
    		break;

    	case R.id.switchFood:
    		Log.d("top", "option 'switchFood' is clicked.");
    		intent = new Intent(RegisterDog.this, com.example.wanwan.SwitchFood.SwitchFood.class);
    		startActivity(intent);
    		break;

    	case R.id.registerFood:
    		Log.d("top", "option 'registerFood' is clicked.");
    		intent = new Intent(RegisterDog.this, com.example.wanwan.RegisterFood.RegisterFood.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.registerDog:
    		Log.d("top", "option 'registerDog' is clicked.");
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
