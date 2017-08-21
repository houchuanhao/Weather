package com.example.weather.MyDefind.city;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CityDao extends SQLiteOpenHelper {
	SQLiteDatabase db;
	public static Context activity=null;
	public static final String dbname="city";
	public static final String tablename="city";
	public CityDao(Context context, String name, CursorFactory factory,
			int version) {

		super(context, name, factory, version);
		
		// TODO Auto-generated constructor stub
	}
	public CityDao(){

		this(activity,dbname,null,1);

	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table city (_id Integer primary key autoincrement, json text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	public static void setContext(Context _activity){
		CityDao.activity=_activity;
	}
	public void add(City city){
//		String sql = "insert into person insert into person (name, age, sex)values(?, ?, ?)";
//		db.execSQL(sql, args);
		db = this.getWritableDatabase();
		ContentValues cvs = new ContentValues();
		cvs.put("json", city.getJson());
		db.insert(tablename, null, cvs);
	}
	public void update(City city){
//		db.execSQL("update person set name=?, age=?, sex=? where _id=?", args);
		db = this.getWritableDatabase();
		ContentValues cvs = new ContentValues();
		cvs.put("json",city.getJson());
		String array[] = {String.valueOf(city.getId())};
		db.update(tablename, cvs, "_id=",array);
	}
	public void delete(int id){
//		db.execSQL("delete from person where _id=?", args);
		db = this.getWritableDatabase();
		String array[] = {String.valueOf(id)};
		db.delete(tablename, "_id=", array);
	}
	public Cursor query(){
		db = this.getWritableDatabase();
		return db.query(tablename, null, null, null, null, null, null);
//		return db.rawQuery("select * from person", null);
	}
	
	
	
	
	
	
	
}
