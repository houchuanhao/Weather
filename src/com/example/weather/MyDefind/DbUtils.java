package com.example.weather.MyDefind;

import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtils extends SQLiteOpenHelper {
	SQLiteDatabase db;
	
	public DbUtils(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
		// TODO Auto-generated constructor stub
	}
	public DbUtils(Context context, String name){
		this(context, name, null, 1);

	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table city (_id Integer primary key autoincrement, citynumber Integer)";
		db.execSQL(sql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void add(List cityList){
//		String sql = "insert into person insert into person (name, age, sex)values(?, ?, ?)";
//		db.execSQL(sql, args);
		db = this.getWritableDatabase();
		ContentValues cvs = new ContentValues();
		Iterator iterator=cityList.iterator();
		while(iterator.hasNext()){
			CityDate city=(CityDate)iterator.next();
			cvs.put("citynumber", city.getCity());
			db.insert("city", null, cvs);
		}
		
	}
//	public void update(String args[]){
////		db.execSQL("update person set name=?, age=?, sex=? where _id=?", args);
//		db = this.getWritableDatabase();
//		ContentValues cvs = new ContentValues();
//		cvs.put("name", args[0]);
//		cvs.put("age", args[1]);
//		cvs.put("sex", args[2]);
//		String array[] = {args[3]};
//		db.update("person", cvs, "_id=", array);
//	}
	public void delete(int id){
//		db.execSQL("delete from person where _id=?", args);
		db = this.getWritableDatabase();
		String array[] = {String.valueOf(id)};
		db.delete("person", "_id=", array);
	}
	public Cursor query(){
		db = this.getWritableDatabase();
//		return db.query("person", null, null, null, null, null, null);
//		return db.rawQuery("select * from person", null);
		return db.rawQuery("select * from city", null);
	}
//	public List<int>getCity(){
//		Cursor result=query();
//		//while(result.)
//	}
}
