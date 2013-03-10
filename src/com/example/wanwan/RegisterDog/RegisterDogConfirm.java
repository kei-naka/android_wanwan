package com.example.wanwan.RegisterDog;

import com.example.wanwan.R;
import com.example.wanwan.model.wanwanDBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterDogConfirm extends Activity {
	
	private TextView nameTextView;
	private TextView birthdayTextView;
	private TextView sexTextView;
	private TextView breedTextView;
	private TextView nrmWghtTextView;
	private CharSequence nrmWghtFrom = null;
	private CharSequence nrmWghtTo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_dog_confirm);
		
		nameTextView = (TextView) findViewById(R.id.RDCName);
		birthdayTextView = (TextView) findViewById(R.id.RDCBirthday);
		sexTextView = (TextView) findViewById(R.id.RDCSex);
		breedTextView = (TextView) findViewById(R.id.RDCBreed);
		nrmWghtTextView = (TextView) findViewById(R.id.RDCNrmWeight);
		
		Bundle data = getIntent().getExtras();
		if (data != null) {
			// なまえを読み込み
			nameTextView.setText(data.getCharSequence("RDName"));
			// TODO なまえが空だったらどうする？
			
			// 誕生日を読み込み
			birthdayTextView.setText(data.getCharSequence("RDBirthday"));
			
			// 性別を読み込み
			sexTextView.setText(data.getCharSequence("RDSex"));
			
			// 犬種を読み込み
			breedTextView.setText(data.getCharSequence("RDBreed"));
			
			// 適正体重の from と to を読み込んで、strings.xml の定義から設定する文字列を作成 
			nrmWghtFrom = data.getCharSequence("RDNrmWeightFrom");
			nrmWghtTo = data.getCharSequence("RDNrmWeightTo");
			
			if (nrmWghtFrom.length() > 0 || nrmWghtTo.length() > 0) {
				nrmWghtTextView.setText(getString(R.string.betweenKg, nrmWghtFrom, nrmWghtTo));
			}
		}
		
		// 戻るボタン
		Button btnBack = (Button) findViewById(R.id.RDCBackBtn);
		btnBack.setOnClickListener(lstnrBtnBack);
		
		// 保存ボタン
		Button btnSave = (Button) findViewById(R.id.RDCSaveBtn);
		btnSave.setOnClickListener(lstnrBtnSave);
	}
	
	/**
	 * 戻るボタンリスナー
	 * このアクティビティを終了する
	 */
	OnClickListener lstnrBtnBack = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};
	
	/**
	 * 保存ボタンリスナー
	 * プロフィール情報をDBに登録する。
	 * 【最終的な手順】
	 *   1. DBに同じ名前のプロフィールがあるかチェック
	 *   2. 手順1 で同じ名前のプロフィールが既に登録済み → 手順4 へ
	 *   3. 手順1 で同じ名前のプロフィールが存在しない → 手順9 へ
	 *   4. 上書き保存、新規保存、キャンセルするか確認ダイアログを出す。
	 *   　　上書き保存 → 手順5へ
	 *   　　新規保存 → 手順9へ
	 *   5. 同じ名前のプロフィールが複数ある → 手順7へ移動
	 *   6. 同じ名前のプロフィールが1つだけ → 手順8へ移動
	 *   7. 上書きするプロフィールを選択するダイアログ表示
	 *   8. update した旨のダイアログを出して TOP へ移動
	 *   9. insert した旨のダイアログを出して TOP へ移動
	 *   
	 * ※ただし、上記手順の実装には時間がかかる。
	 * 一通り動くものができるまで、今は次に示す簡易版で実装する。
	 * （＝名前が同じものがある場合には対応していない）
	 * 
	 * 【簡易版】
	 *   1. DBに同じ名前のプロフィールがあるかチェック
	 *   2. 手順1 で同じ名前のプロフィールが既に登録済み
	 *   → 名前を変更するよう促すダイアログを出す
	 *   3. 手順1 で同じ名前のプロフィールが存在しない
	 *   →insert した旨のダイアログを出して TOP へ移動
	 * 
	 * 登録できた時は、ダイアログを出して、トップページに戻る
	 * 登録できなかった時は、エラーダイアログを出して、このページにとどまる
	 */
	OnClickListener lstnrBtnSave = new OnClickListener() {
		@Override
		public void onClick(View view) {
			String name = nameTextView.getText().toString();
			
			wanwanDBHelper helper = new wanwanDBHelper(RegisterDogConfirm.this);
			SQLiteDatabase db = helper.getWritableDatabase();

			// name は空じゃない前提
			Cursor prof = db.query(wanwanDBHelper.getTableDogInfo()
				,new String[] {"name", "birthday", "sex", "breed"}
				, "name = '" + name + "'", null, null, null, null);

			if (prof.getCount() != 0) {
				/*
				 * 2. 手順1 で同じ名前のプロフィールが既に登録済み
				 * → 名前を変更するよう促すダイアログを出す
				 */
				AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterDogConfirm.this);
				dialog.setTitle(R.string.headerRD);
				dialog.setMessage(
						getString(R.string.errDuplicate, name)
						+ getString(R.string.errChange, "なまえ")
						);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface di, int which) {}
				});
				dialog.create();
				dialog.show();
			} else {
				/* 
				 * 3. 手順1 で同じ名前のプロフィールが存在しない
				 * →insert した旨のダイアログを出して TOP へ移動
				 */
				
				Cursor maxId = db.query(wanwanDBHelper.getTableDogInfo(), 
						new String[] {"id"}, null, null, null, null, null);
						// db.rawQuery("select max(id) from dog_info", null);
				int id = 1;
				if (maxId.getCount() != 0) {
					maxId.moveToLast();
					id = maxId.getInt(0) + 1;
				}
				Log.d("RDC", "id: " + id);
				maxId.close();

				ContentValues values = new ContentValues();
				values.put("id", id);
				values.put("name", name);
				values.put("birthday", birthdayTextView.getText().toString());
				values.put("sex", sexTextView.getText().toString());
				values.put("breed", breedTextView.getText().toString());
				values.put("nrmwght_min", nrmWghtFrom.toString());
				values.put("nrmwght_max", nrmWghtTo.toString());
				values.put("active_flg", 1);
				
				try {
					/*
					 * INSERT INTO dog_info
					 *   (id, name, birthday, sex, breed, nrmwght_min, nrmwght_max, active_flg)
					 * VALUES
					 *   (...);
					 */
					db.insert(wanwanDBHelper.getTableDogInfo(), "1", values);
					
					/*
					 * UPDATE dog_info
					 * SET active_flg = 0
					 * WHERE name <> 'name';
					 */
					ContentValues flag = new ContentValues();
					flag.put("active_flg", 0);
					db.update(wanwanDBHelper.getTableDogInfo(), flag, "name <> '" + name + "'", null);
					
					AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterDogConfirm.this);
					dialog.setTitle(R.string.headerRD);
					dialog.setMessage(getString(R.string.RDOK, nameTextView.getText()));
					dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.Top.Top.class);
							startActivity(intent);
							finish();
						}
					});
					dialog.create();
					dialog.show();
					
				} catch (Exception e) {
					e.printStackTrace();
					AlertDialog.Builder errDialog = new AlertDialog.Builder(RegisterDogConfirm.this);

					errDialog.setTitle(R.string.headerRD);
					errDialog.setMessage(R.string.errInsertProf);
					errDialog.setPositiveButton(R.string.btnOK, null);

					errDialog.create();
					errDialog.show();
				} finally {
					db.close();
					prof.close();
				}
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
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.Top.Top.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.showHistory:
    		Log.d("top", "option 'showHistory' is clicked.");
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.ShowHistory.ShowHistory.class);
    		startActivity(intent);
    		break;

    	case R.id.inputWeight:
    		Log.d("top", "option 'inputWeight' is clicked.");
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.InputWeight.InputWeight.class);
    		startActivity(intent);
    		break;

    	case R.id.inputFood:
    		Log.d("top", "option 'inputFood' is clicked.");
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.InputFood.InputFood.class);
    		startActivity(intent);
    		break;

    	case R.id.switchFood:
    		Log.d("top", "option 'switchFood' is clicked.");
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.SwitchFood.SwitchFood.class);
    		startActivity(intent);
    		break;

    	case R.id.registerFood:
    		Log.d("top", "option 'registerFood' is clicked.");
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.RegisterFood.RegisterFood.class);
    		startActivity(intent);
    		break;
    		
    	case R.id.registerDog:
    		Log.d("top", "option 'registerDog' is clicked.");
    		intent = new Intent(RegisterDogConfirm.this, com.example.wanwan.RegisterDog.RegisterDog.class);
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
