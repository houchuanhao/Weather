package com.example.weather.MyDefind;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String dateToString(Date date){ //ֻ��Сʱ
	      //  SimpleDateFormat matter=new SimpleDateFormat("Now Time��' y��M��d��Hʱm��s");
			SimpleDateFormat matter=new SimpleDateFormat("HH:mm");
			String time=matter.format(date);
			return time;
		}
}
