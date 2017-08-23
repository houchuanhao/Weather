package com.example.weather.MyDefind.city;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	boolean exist(String cityNumber){
		
		List list=getAll();
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			City city=(City)iterator.next();
			if(city.getCityNumber()!=null&&city.getCityNumber().equals(cityNumber))
				return true;
		}
		return false;
	}

	public CityDao(){

		this(activity,dbname,null,1);

	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table city (_id Integer primary key autoincrement, citynumber text,forecast7d text,weatherhours text,observ text)";
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
		cvs.put("citynumber", city.getCityNumber());
		cvs.put("forecast7d", city.getForecast7d());
		cvs.put("weatherhours", city.getWeatherhours());
		cvs.put("observ", city.getObserv());
		db.insert(tablename, null, cvs);
	}
	public void update(City city){
		//db.execSQL("update person set name=?, age=?, sex=? where _id=?", args);
		//db = this.getWritableDatabase();
		ContentValues cvs = new ContentValues();
		cvs.put("forecast7d",city.getForecast7d());
		cvs.put("weatherhours",city.getWeatherhours());
		cvs.put("observ",city.getObserv());
		String array[] = {city.getCityNumber()};
		db.update(tablename, cvs, "citynumber=?",array);
	}
	public void delete(int _id){
//		db.execSQL("delete from person where _id=?", args);
		db = this.getWritableDatabase();
		String array[] = {String.valueOf(_id)};
		db.delete(tablename, "_id=?", array);
		int _$abc;
	}
	public Cursor query(){
		db = this.getWritableDatabase();
		return db.query(tablename, null, null, null, null, null, null);
//		return db.rawQuery("select * from person", null);
	}
	public List getAll(){
		List cityList=new ArrayList();
		Cursor cursor=query();
		while(cursor.moveToNext()) {
		    //光标移动成功
			City city=new City();
			city.id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
			city.forecast7d=cursor.getString(cursor.getColumnIndex("forecast7d"));
			city.weatherhours=cursor.getString(cursor.getColumnIndex("weatherhours"));
			city.observ=cursor.getString(cursor.getColumnIndex("observ"));
			city.cityNumber=cursor.getString(cursor.getColumnIndex("citynumber"));
			city.analysis();
			cityList.add(city);
			}
		return cityList;
		}
}
