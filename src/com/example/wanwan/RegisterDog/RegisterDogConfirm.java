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
			// �Ȃ܂���ǂݍ���
			nameTextView.setText(data.getCharSequence("RDName"));
			// TODO �Ȃ܂����󂾂�����ǂ�����H
			
			// �a������ǂݍ���
			birthdayTextView.setText(data.getCharSequence("RDBirthday"));
			
			// ���ʂ�ǂݍ���
			sexTextView.setText(data.getCharSequence("RDSex"));
			
			// �����ǂݍ���
			breedTextView.setText(data.getCharSequence("RDBreed"));
			
			// �K���̏d�� from �� to ��ǂݍ���ŁAstrings.xml �̒�`����ݒ肷�镶������쐬 
			nrmWghtFrom = data.getCharSequence("RDNrmWeightFrom");
			nrmWghtTo = data.getCharSequence("RDNrmWeightTo");
			
			if (nrmWghtFrom.length() > 0 || nrmWghtTo.length() > 0) {
				nrmWghtTextView.setText(getString(R.string.betweenKg, nrmWghtFrom, nrmWghtTo));
			}
		}
		
		// �߂�{�^��
		Button btnBack = (Button) findViewById(R.id.RDCBackBtn);
		btnBack.setOnClickListener(lstnrBtnBack);
		
		// �ۑ��{�^��
		Button btnSave = (Button) findViewById(R.id.RDCSaveBtn);
		btnSave.setOnClickListener(lstnrBtnSave);
	}
	
	/**
	 * �߂�{�^�����X�i�[
	 * ���̃A�N�e�B�r�e�B���I������
	 */
	OnClickListener lstnrBtnBack = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};
	
	/**
	 * �ۑ��{�^�����X�i�[
	 * �v���t�B�[������DB�ɓo�^����B
	 * �y�ŏI�I�Ȏ菇�z
	 *   1. DB�ɓ������O�̃v���t�B�[�������邩�`�F�b�N
	 *   2. �菇1 �œ������O�̃v���t�B�[�������ɓo�^�ς� �� �菇4 ��
	 *   3. �菇1 �œ������O�̃v���t�B�[�������݂��Ȃ� �� �菇9 ��
	 *   4. �㏑���ۑ��A�V�K�ۑ��A�L�����Z�����邩�m�F�_�C�A���O���o���B
	 *   �@�@�㏑���ۑ� �� �菇5��
	 *   �@�@�V�K�ۑ� �� �菇9��
	 *   5. �������O�̃v���t�B�[������������ �� �菇7�ֈړ�
	 *   6. �������O�̃v���t�B�[����1���� �� �菇8�ֈړ�
	 *   7. �㏑������v���t�B�[����I������_�C�A���O�\��
	 *   8. update �����|�̃_�C�A���O���o���� TOP �ֈړ�
	 *   9. insert �����|�̃_�C�A���O���o���� TOP �ֈړ�
	 *   
	 * ���������A��L�菇�̎����ɂ͎��Ԃ�������B
	 * ��ʂ蓮�����̂��ł���܂ŁA���͎��Ɏ����ȈՔłŎ�������B
	 * �i�����O���������̂�����ꍇ�ɂ͑Ή����Ă��Ȃ��j
	 * 
	 * �y�ȈՔŁz
	 *   1. DB�ɓ������O�̃v���t�B�[�������邩�`�F�b�N
	 *   2. �菇1 �œ������O�̃v���t�B�[�������ɓo�^�ς�
	 *   �� ���O��ύX����悤�����_�C�A���O���o��
	 *   3. �菇1 �œ������O�̃v���t�B�[�������݂��Ȃ�
	 *   ��insert �����|�̃_�C�A���O���o���� TOP �ֈړ�
	 * 
	 * �o�^�ł������́A�_�C�A���O���o���āA�g�b�v�y�[�W�ɖ߂�
	 * �o�^�ł��Ȃ��������́A�G���[�_�C�A���O���o���āA���̃y�[�W�ɂƂǂ܂�
	 */
	OnClickListener lstnrBtnSave = new OnClickListener() {
		@Override
		public void onClick(View view) {
			String name = nameTextView.getText().toString();
			
			wanwanDBHelper helper = new wanwanDBHelper(RegisterDogConfirm.this);
			SQLiteDatabase db = helper.getWritableDatabase();

			// name �͋󂶂�Ȃ��O��
			Cursor prof = db.query(wanwanDBHelper.getTableDogInfo()
				,new String[] {"name", "birthday", "sex", "breed"}
				, "name = '" + name + "'", null, null, null, null);

			if (prof.getCount() != 0) {
				/*
				 * 2. �菇1 �œ������O�̃v���t�B�[�������ɓo�^�ς�
				 * �� ���O��ύX����悤�����_�C�A���O���o��
				 */
				AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterDogConfirm.this);
				dialog.setTitle(R.string.headerRD);
				dialog.setMessage(
						getString(R.string.errDuplicate, name)
						+ getString(R.string.errChange, "�Ȃ܂�")
						);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface di, int which) {}
				});
				dialog.create();
				dialog.show();
			} else {
				/* 
				 * 3. �菇1 �œ������O�̃v���t�B�[�������݂��Ȃ�
				 * ��insert �����|�̃_�C�A���O���o���� TOP �ֈړ�
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
	
	// ���j���[���C���t���[�g
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.common_static, menu);
    	return true;
    }
    
	// ���j���[�A�N�V����
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
