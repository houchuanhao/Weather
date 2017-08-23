package com.example.weather.MyDefind;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.weather.MyDefind.city.City;

public class Utils {
	public static String dateToString(Date date){ //只有小时
	      //  SimpleDateFormat matter=new SimpleDateFormat("Now Time：' y年M月d日H时m分s");
			if(date==null)
				return dateToString(new Date());
			SimpleDateFormat matter=new SimpleDateFormat("HH:mm");
			String time=matter.format(date);
			return time;
		}
    public static boolean like(String str1,String str2 ){
      	 String[] array1 = str1.split(str2);
      	 String[] array2 = str2.split(str1);
      	 if(array1.length>=2||array2.length>=2||str1.equals(str2))
      		 return true;
      	 return false;
      }
    public static List getLikeCity(String cityName,List cityList){
  
    	List child=new ArrayList();
    	if(cityList==null||cityList.size()==0)
    		return child;
    	Iterator iterator=cityList.iterator();
    	while(iterator.hasNext()){
    		City city=(City)iterator.next();
    		if(like(city.getCityName(), cityName)){
    			child.add(city);
    		}
    	}
    	return child;
    }

}
