package com.example.weather.MyDefind;

import java.util.Date;
import java.util.List;

public class CityDate {
	private

	int City;
	String CityName;
	int currentTemperature;
	Date updatTime; //����ʱ��
	String currentWeather;//��ǰ����
	int todayMin;//���������
	int todayMax;//���������
	List timeState24;//24Сʱ����
	List weekState7;//һ������
	List otherAttribute;//�������ԣ������ճ������䡣��
	
	public int getCity() {
		return City;
	}
	public void setCity(int city) {
		City = city;
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

}
