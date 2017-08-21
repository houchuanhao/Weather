package com.example.weather.MyDefind.city;

import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

public class City implements BasicDao{
	private static CityDao cityDao;
	int id;//在sqllit数据库中的id
	int cityNumber; //http请求中对应的编码
	String CityName;
	int currentTemperature;
	Date updatTime; //更新时间
	String currentWeather;//当前天气
	int todayMin;//今日最低温
	int todayMax;//今日最高温
	List timeState24;//24小时天气
	List weekState7;//一周天气
	List otherAttribute;//其他属性，比如日出，日落。。
	String json="json串";//该city的json串
	public static void City(){
		if(CityDao.activity==null){
			Log.e("CityDao的静态成员没有初始化","在创建CityDao对象前必须执行CityDao.setContext(Context activity)函数");

		}
		cityDao=new CityDao();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(int cityNumber) {
		this.cityNumber = cityNumber;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public int getCurrentTemperature() {
		return currentTemperature;
	}
	public void setCurrentTemperature(int currentTemperature) {
		this.currentTemperature = currentTemperature;
	}
	public Date getUpdatTime() {
		return updatTime;
	}
	public void setUpdatTime(Date updatTime) {
		this.updatTime = updatTime;
	}
	public String getCurrentWeather() {
		return currentWeather;
	}
	public void setCurrentWeather(String currentWeather) {
		this.currentWeather = currentWeather;
	}
	public int getTodayMin() {
		return todayMin;
	}
	public void setTodayMin(int todayMin) {
		this.todayMin = todayMin;
	}
	public int getTodayMax() {
		return todayMax;
	}
	public void setTodayMax(int todayMax) {
		this.todayMax = todayMax;
	}
	public List getTimeState24() {
		return timeState24;
	}
	public void setTimeState24(List timeState24) {
		this.timeState24 = timeState24;
	}
	public List getWeekState7() {
		return weekState7;
	}
	public void setWeekState7(List weekState7) {
		this.weekState7 = weekState7;
	}
	public List getOtherAttribute() {
		return otherAttribute;
	}
	public void setOtherAttribute(List otherAttribute) {
		this.otherAttribute = otherAttribute;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	@Override
	public void save() {
		cityDao.add(this);
		// TODO Auto-generated method stub
	}
	@Override
	public void remove() {
		cityDao.delete(id);
		// TODO Auto-generated method stub
		
	}
	@Override
	public List getAll() {
		Cursor cursor=cityDao.query();
		while(cursor.moveToNext()) {
		    //光标移动成功

			id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
			json=cursor.getString(cursor.getColumnIndex("json"));
			analysis();
		   //把数据取出
		}
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void analysis() {
		// TODO Auto-generated method stub
		currentWeather="解析之后的json";
	}

	

}
