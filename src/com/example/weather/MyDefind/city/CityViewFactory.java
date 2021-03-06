package com.example.weather.MyDefind.city;

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.weather.R;
import com.example.weather.MyDefind.GridViewAdapter;
import com.example.weather.R.id;
import com.example.weather.R.layout;

public class CityViewFactory {
	//这里缺个适配器
	Activity activity;
	City cityDate;
	View view;
	TextView cityName;
	TextView currentWeather;
	TextView currentTemperature;
	TextView weekday;//今天星期几
	TextView todayMax;  //最高温度
	TextView todayMin;//最低温
	HorizontalScrollView hsv;
	GridView gv24;//24小时
	GridView gv7;//一周
	CityViewFactory(City cityDate,Activity activity){
		this.cityDate=cityDate;
		this.activity=activity;
		view= activity.getLayoutInflater().inflate(R.layout.weatherlayout, null);
		 //setContent();
	}
	public static List<View> getViewList(List<City> cityList,Activity activity){
		if(cityList==null){
			//Log.w
			Log.w("CityViewFactory.getViewList:","参数cityList为空");
			return new ArrayList();
		}
		Iterator iteator=cityList.iterator();
		List<View> viewList=new ArrayList<View>();
		while(iteator.hasNext()){
			View view=new CityViewFactory((City)iteator.next(), activity).getView();
			viewList.add(view);
		}
		return viewList;
	}
	public View getView(){
		 int a=3;
		 cityName=(TextView)view.findViewById(R.id.cityName);
		 currentWeather=(TextView)view.findViewById(R.id.currentWeather);
		 currentTemperature=(TextView)view.findViewById(R.id.currentTemperature);
		 weekday=(TextView)view.findViewById(R.id.weekday);//今天星期几
		 todayMax=(TextView)view.findViewById(R.id.todayMax);  //最高温度
		 todayMin=(TextView)view.findViewById(R.id.todayMin);//最低温
		 hsv=(HorizontalScrollView)view.findViewById(R.id.hsv);
		 gv24=(GridView)view.findViewById(R.id.gv24);
		 gv7=(GridView)view.findViewById(R.id.gv7);
		 setContent();
		 return view;
	}
	public void setContent(){
		cityName.setText(cityDate.getCityName());
		currentWeather.setText(cityDate.getCurrentWeather());
		currentTemperature.setText(cityDate.getCurrentTemperature()+"°");
		todayMax.setText(cityDate.getTodayMax()+"°");
		todayMin.setText(cityDate.getTodayMin()+"°");
		setGv7();
		setGv24();
	}
	public void setGv7(){
		String from[]={"day","weather","max","min"};
		int[] to={R.id.textView11,R.id.textView22,R.id.textView33,R.id.textView44};
		// 2、新建适配器
		Adapter adapter=new SimpleAdapter(activity, cityDate.getWeekState7(), R.layout.list, from, to);
	//下面这条语句出了bug
		gv7.setAdapter((ListAdapter) adapter);
	}
	 private void setGv24() {
		 	List timeStateList=cityDate.getTimeState24();
	        int size = timeStateList.size();
	        int length = 100;
	        DisplayMetrics dm = new DisplayMetrics();
	        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	        float density = dm.density;
	        int gridviewWidth = (int) ((size )* (length ) * density);
	        int itemWidth = (int) (length * density);

	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
	        gv24.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
	        gv24.setColumnWidth(itemWidth); // 设置列表项宽
	        gv24.setHorizontalSpacing(5); // 设置列表项水平间距
	        gv24.setStretchMode(GridView.NO_STRETCH);
	        gv24.setNumColumns(size); // 设置列数量=列表集合数
	        GridViewAdapter adapter = new GridViewAdapter(activity.getApplicationContext(),
	                timeStateList,R.layout.midlist){

						@Override
						public void setConvertView(View convertView, Object item) {
							// TODO Auto-generated method stub
							 	Map ts =(Map) item;
					            TextView time = (TextView) convertView.findViewById(R.id.time);
					            TextView weather = (TextView) convertView.findViewById(R.id.weather);
					            TextView temperature = (TextView) convertView.findViewById(R.id.temperature);
					           
					            time.setText(ts.get("time")+"时");
					            weather.setText(ts.get("weather").toString());
					            temperature.setText(ts.get("temperature")+"°");
						}
	        	
	        };
	        gv24.setAdapter(adapter);
	    }
	    /**GirdView 数据适配器*/
}
