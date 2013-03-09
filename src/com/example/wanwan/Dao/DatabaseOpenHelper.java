package com.example.wanwan.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "wanwanDB";
	private static final String TABLE_DOG_PROFILE = "dog_info";

	public static String getTableDogProfile() {
		return TABLE_DOG_PROFILE;
	}
	// for test
	/*private final String[][] init_data = {
			{"ç≤ÅXñÿÇ≥ÇÒ", "1983/02/13", "ÉIÉX", "å¢éÌÇP", "10.5", "12.5", "0"}
			,{"Ç€Çø", "2012/01/23", "ÉÅÉX", "å¢éÌÇQ", "12.3", "23.4", "1"}
	}; */
	
	public DatabaseOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("CREATE TABLE dog_info (");
			sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL");
			sql.append(", name TEXT NOT NULL");
			sql.append(", birthday TEXT");
			sql.append(", sex TEXT");
			sql.append(", breed TEXT");
			sql.append(", nrmwght_min REAL");
			sql.append(", nrmwght_max REAL");
			sql.append(", active_flg INTEGER NOT NULL");
			sql.append(")");
			db.execSQL(sql.toString());
			Log.d("DOH", sql.toString());
			
			// for test
			/*
			for (String[] data : init_data) {
				ContentValues values = new ContentValues();
				values.put("name", data[0]);
				values.put("sex", data[1]);
				values.put("birthday", data[2]);
				values.put("breed", data[3]);
				values.put("nrmwght_min", Double.parseDouble(data[4]));
				values.put("nrmwght_max", Double.parseDouble(data[5]));
				values.put("active_flg", data[6]);
				db.insert("dog_info", null, values);
			}
			*/
			
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
