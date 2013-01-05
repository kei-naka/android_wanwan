package com.example.wanwan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputCurrentFoodActivity extends Activity {
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.input_current_food);
		
		EditText curWeight = (EditText) findViewById(R.id.curWeight);
		curWeight.setFocusable(true);
		curWeight.setFocusableInTouchMode(true);
		
		EditText curCarolie = (EditText) findViewById(R.id.curCarolie);
		curCarolie.setFocusable(true);
		curCarolie.setFocusableInTouchMode(true);
		
		Button btn = (Button) findViewById(R.id.btnSaveCurFoodInfo);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InputCurrentFoodActivity.this);
				
		        // アラートダイアログのタイトルを設定します
		        alertDialogBuilder.setTitle("保存するだと！？");
		        
		        // アラートダイアログのメッセージを設定します
		        alertDialogBuilder.setMessage("体重 12.3 kg\nドッグフードA を 123g\nで本当に登録していいのか！？");
		        
		        // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		        alertDialogBuilder.setPositiveButton("構わん！！！",
		                new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which) {
		        				// TODO Auto-generated method stub
		        				Intent intent = new Intent(InputCurrentFoodActivity.this, MainActivity.class);
		        				startActivity(intent);
		                    }
		                });

		        // アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		        alertDialogBuilder.setNegativeButton("待て・・・",
		                new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which) {
		                    }
		                });
		        
		        // アラートダイアログのキャンセルが可能かどうかを設定します
		        alertDialogBuilder.setCancelable(true);
		        AlertDialog alertDialog = alertDialogBuilder.create();
		        // アラートダイアログを表示します
		        alertDialog.show();

			}
		});
	}
	
	@Override
	public void startActivity(Intent intent) {
		super.finish();
		super.startActivity(intent);
	}
}
