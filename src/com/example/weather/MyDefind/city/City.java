package com.example.weather.MyDefind.city;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

public class City implements BasicDao{
	private static CityDao cityDao=new CityDao();
	int id;//��sqllit���ݿ��е�id
	int cityNumber; //http�����ж�Ӧ�ı���
	String CityName;
	int currentTemperature;
	Date updatTime; //����ʱ��
	String currentWeather;//��ǰ����
	int todayMin;//���������
	int todayMax;//���������
	List timeState24;//24Сʱ����
	List weekState7;//һ������
	List otherAttribute;//�������ԣ������ճ������䡣��
	String json="json��";//��city��json��
	public  City(){
		if(CityDao.activity==null){
			Log.e("CityDao�ľ�̬��Աû�г�ʼ��","�ڴ���CityDao����ǰ����ִ��CityDao.setContext(Context activity)����");

		}
		//cityDao=new CityDao();
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
	public static void remove(int _id){
		cityDao.delete(_id);
	}
	public static List getAll() {
		List cityList=new ArrayList();

		Cursor cursor=cityDao.query();
		while(cursor.moveToNext()) {
		    //����ƶ��ɹ�
			City city=CityFactory.getCity("");
			city.id=Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
			city.json=cursor.getString(cursor.getColumnIndex("json"));
			city.analysis();
			cityList.add(city);
		   //������ȡ��
		}
		// TODO Auto-generated method stub
		return cityList;
	}
	@Override
	public void analysis() {
		// TODO Auto-generated method stub
		currentWeather="����֮���json";
	}

	

}
