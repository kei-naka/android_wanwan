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
				
		        // �A���[�g�_�C�A���O�̃^�C�g����ݒ肵�܂�
		        alertDialogBuilder.setTitle("�ۑ����邾�ƁI�H");
		        
		        // �A���[�g�_�C�A���O�̃��b�Z�[�W��ݒ肵�܂�
		        alertDialogBuilder.setMessage("�̏d 12.3 kg\n�h�b�O�t�[�hA �� 123g\n�Ŗ{���ɓo�^���Ă����̂��I�H");
		        
		        // �A���[�g�_�C�A���O�̍m��{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
		        alertDialogBuilder.setPositiveButton("�\���I�I�I",
		                new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which) {
		        				// TODO Auto-generated method stub
		        				Intent intent = new Intent(InputCurrentFoodActivity.this, MainActivity.class);
		        				startActivity(intent);
		                    }
		                });

		        // �A���[�g�_�C�A���O�̔ے�{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
		        alertDialogBuilder.setNegativeButton("�҂āE�E�E",
		                new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which) {
		                    }
		                });
		        
		        // �A���[�g�_�C�A���O�̃L�����Z�����\���ǂ�����ݒ肵�܂�
		        alertDialogBuilder.setCancelable(true);
		        AlertDialog alertDialog = alertDialogBuilder.create();
		        // �A���[�g�_�C�A���O��\�����܂�
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
