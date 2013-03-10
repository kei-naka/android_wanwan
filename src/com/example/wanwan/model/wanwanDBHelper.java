package com.example.wanwan.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class wanwanDBHelper extends SQLiteOpenHelper {
	
	private static final String TAG="DatabaseOpenHelper";
	private static final String DB_NAME = "wanwan";
	private static final String TABLE_DOG_INFO = "dog_info";
	
	private static int DB_VERSION = 1;

	public static String getTableDogInfo() {
		return TABLE_DOG_INFO;
	}
	
	public wanwanDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE dog_info (");
		sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL");
		sql.append(", name TEXT NOT NULL");
		sql.append(", birthday TEXT");
		sql.append(", sex TEXT");
		sql.append(", breed TEXT");
		sql.append(", nrmwght_min REAL");
		sql.append(", nrmwght_max REAL");
		sql.append(", photo INTEGER");
		sql.append(", active_flg INTEGER NOT NULL");
		sql.append(")");
		db.execSQL(sql.toString());
		Log.d(TAG, "execute sql command: " + sql.toString());
	}

	// TODO write onUpgrade logic
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
}
