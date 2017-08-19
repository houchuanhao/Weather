package com.example.weather.MyDefind;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String dateToString(Date date){ //只有小时
	      //  SimpleDateFormat matter=new SimpleDateFormat("Now Time：' y年M月d日H时m分s");
			SimpleDateFormat matter=new SimpleDateFormat("HH:mm");
			String time=matter.format(date);
			return time;
		}
}
