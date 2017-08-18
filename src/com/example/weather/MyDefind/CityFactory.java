package com.example.weather.MyDefind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityFactory {
	public  static CityDate getCity(String response){
		List weekState7;
		List timeState24;
		CityDate city=new CityDate();
		city.setCity(1);
		city.setCityName("����");
		city.setCurrentTemperature(30);
		city.setCurrentWeather("����");
		city.setTodayMax(33);
		city.setTodayMin(22);
		weekState7=new ArrayList<Map<String,String>>();
		for(int i=0;i<7;i++){
			Map<String,String> map=new HashMap<String,String>();
			map.put("day","����һ");
			map.put("weather", "����ת��");
			map.put("max", "36");
			map.put("min", "22");
			weekState7.add(map);
		}
		
		timeState24=new ArrayList<Map<String,String>>();
		for(int i=0;i<24;i++){
			Map<String,String> map=new HashMap<String,String>();
			map.put("time",i+"ʱ");
			map.put("weather", "����ת��");
			map.put("temperature", "36");
			timeState24.add(map);
		}
		city.setWeekState7(weekState7);
		city.setTimeState24(timeState24);
		
		
		
		return city;
		
	}
}
