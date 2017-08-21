package com.example.weather;

import java.util.List;

import com.example.weather.MyDefind.Utils;
import com.example.weather.MyDefind.city.City;
import com.example.weather.MyDefind.city.CityFactory;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class CityListActivity extends Activity {
	List cityList=null;
	GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		setView();
		updateView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.city_list, menu);
		return true;
	}
	public void setView(List _cityList){ //设置为当前参数
		cityList=_cityList;
		if(_cityList==null){
			Log.w("空指针","位于CityListActivity.setView(List _cityList");
			return;
		}
		gv=(GridView)findViewById(R.id.cityList);
		gv.setAdapter(new GridViewAdapter(getApplicationContext(),_cityList,R.layout.cityitem) {

			@Override
			public void setConvertView(View convertView, Object item) {
				// TODO Auto-generated method stub
				final City city=(City)item;
				TextView cityName=(TextView)convertView.findViewById(R.id.cityitemcityname);
				TextView time=(TextView)convertView.findViewById(R.id.cityitemtime);
				TextView temperature=(TextView)convertView.findViewById(R.id.cityitemtemperature);
				Button delete=(Button)convertView.findViewById(R.id.cityitemdelete);
				cityName.setText(city.getCityName());
				time.setText(Utils.dateToString(city.getUpdatTime()));
				temperature.setText(city.getCurrentTemperature()+"°");
				delete.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						city.remove();
						setView();
					}
				});
			}
		});
		gv.setVerticalSpacing(1);
	}
	public void setView(){  //设置为数据库中的值
		cityList=City.getAll();
		setView(cityList);
	}
	public void updateView(){
		
		setView();
	}
}
