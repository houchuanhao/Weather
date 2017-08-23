package com.example.weather.MyDefind.city;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.weather.MyDefind.HttpTask;

import android.database.Cursor;
import android.util.Log;

public class City implements BasicDao{
	public static String key="k6vatqjq53wt86n1";
	private static CityDao cityDao=new CityDao();
	int updatenum=0;
	int id;//在sqllit数据库中的id
	String cityNumber; //http请求中对应的编码
	String CityName;
	int currentTemperature;
	Date updatTime; //更新时间
	String currentWeather;//当前天气
	int todayMin;//今日最低温
	int todayMax;//今日最高温
	List timeState24;//24小时天气
	List weekState7;//一周天气
	List otherAttribute;//其他属性，比如日出，日落。。
	String forecast7d;//7天
	String weatherhours;//24小时
	String observ;//实时间天气
	public  City(){
		if(CityDao.activity==null){
			Log.e("CityDao的静态成员没有初始化","在创建CityDao对象前必须执行CityDao.setContext(Context activity)函数");

		}
		//cityDao=new CityDao();
	}
	

	public static CityDao getCityDao() {
		return cityDao;
	}


	public static void setCityDao(CityDao cityDao) {
		City.cityDao = cityDao;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCityNumber() {
		return cityNumber;
	}


	public void setCityNumber(String cityNumber) {
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


	public String getForecast7d() {
		return forecast7d;
	}


	public void setForecast7d(String forecast7d) {
		this.forecast7d = forecast7d;
	}


	public String getWeatherhours() {
		return weatherhours;
	}


	public void setWeatherhours(String weatherhours) {
		this.weatherhours = weatherhours;
	}


	public String getObserv() {
		return observ;
	}


	public void setObserv(String observ) {
		this.observ = observ;
	}


	@Override
	public void save() {
		if(cityDao.exist(this.cityNumber)){
			cityDao.update(this);
		}
		else
			cityDao.add(this);
		// TODO Auto-generated method stub
	}
	@Override
	public void remove() {
		cityDao.delete(id);
		// TODO Auto-generated method stub
		
	}
	public static void remove(int _id){
		cityDao.delete(_id);
	}
	public static List getAll() {
		List cityList=new ArrayList();

		Cursor cursor=cityDao.query();
		while(cursor.moveToNext()) {
		    //光标移动成功
			City city=CityFactory.getCity("");
			city.id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
			//city.json=cursor.getString(cursor.getColumnIndex("json"));
			city.analysis();
			cityList.add(city);
		   //把数据取出
		}
		// TODO Auto-generated method stub
		return cityList;
	}
	@Override
	public void analysis() {
		// TODO Auto-generated method stub
		new HttpTask() {
			
			@Override
			public void success() {
				// TODO Auto-generated method stub
				String str=super.getResponse();
				currentWeather=super.getResponse();
				
					try {	
						Log.i("json",str);
						JSONObject json1 = new JSONObject(str);
						JSONObject date;
						date = json1.getJSONObject("data");
						Log.i("data",date.toString());
						String cityName=(String) date.get("cityName");
						Log.i("testjson___cityName",cityName);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			
			@Override
			public void faild() {
				// TODO Auto-generated method stub
				
			}
		}.execute("http://api.yytianqi.com/weatherhours?city=CH010100&key=k6vatqjq53wt86n1");
		
	}
	public void update(){
		String url="http://api.yytianqi.com/weatherhours?city="+getCityNumber()+"&key="+key;
		new HttpTask() {
			
			@Override
			public void success() {
				// TODO Auto-generated method stub
				
				//remove();
				save();
			}
			
			@Override
			public void faild() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	public City reget(){
		List cityList=cityDao.getAll();
		Iterator iterator=cityList.iterator();
		while(iterator.hasNext()){
			//if()
			City ccity=(City)iterator.next();
			if(ccity.cityNumber.equals(cityNumber)){
				return ccity;
			}
		}
		return null;
	}
	public  void updateCity(){
		new HttpTask(){//7天天气预报

			@Override
			public void success() {
				// TODO Auto-generated method stub
				forecast7d=super.getResponse();
				save();
				updatenum++;
			}

			@Override
			public void faild() {
				// TODO Auto-generated method stub
				
			}
			
		}.execute("http://api.yytianqi.com/forecast7d?city="+cityNumber+"&key=k6vatqjq53wt86n1");
		new HttpTask(){ //天气实况

			@Override
			public void success() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void faild() {
				// TODO Auto-generated method stub
				
			}
			
		}.execute("http://api.yytianqi.com/observe?city=CH010100&key=k6vatqjq53wt86n1");
	}
}
