package com.example.weather;

import java.util.List;

import com.example.weather.MyDefind.CityDate;
import com.example.weather.MyDefind.CityFactory;
import com.example.weather.MyDefind.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

public class CityListActivity extends Activity {

	GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		setView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.city_list, menu);
		return true;
	}

	
	public void setView(){
		gv=(GridView)findViewById(R.id.cityList);
		List cityList=CityFactory.getCityList();
		gv.setAdapter(new GridViewAdapter(getApplicationContext(),cityList,R.layout.cityitem) {

			@Override
			public void setConvertView(View convertView, Object item) {
				// TODO Auto-generated method stub
				CityDate city=(CityDate)item;
				TextView cityName=(TextView)convertView.findViewById(R.id.cityitemcityname);
				TextView time=(TextView)convertView.findViewById(R.id.cityitemtime);
				TextView temperature=(TextView)convertView.findViewById(R.id.cityitemtemperature);
				cityName.setText(city.getCityName());
				time.setText(Utils.dateToString(city.getUpdatTime()));
				String str=Utils.dateToString(city.getUpdatTime());
				temperature.setText(city.getCurrentTemperature()+"бу");
			}
		});
	}
}
