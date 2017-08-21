package com.example.weather;

import java.util.ArrayList;
import java.util.List;

import com.example.weather.MyDefind.city.City;
import com.example.weather.MyDefind.city.CityDao;
import com.example.weather.MyDefind.city.CityFactory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class TestActivity extends Activity {
	List cityList;
	GridView gv;
	MultiAutoCompleteTextView id;
	MultiAutoCompleteTextView json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		gv=(GridView)findViewById(R.id.activity_test_gridview);
		cityList=new ArrayList();
		CityDao.setContext(this);
		id=(MultiAutoCompleteTextView)findViewById(R.id.id);
		json=(MultiAutoCompleteTextView)findViewById(R.id.json);
	}
	public void add(View v){
		City city=CityFactory.getCity("");
		city.setJson(json.getText().toString());
		city.save();
	}
	public void delete(View v){
		if(cityList.size()>0){
			City city=(City)cityList.get(0);
			city.remove();
		}
		select(v);
	}
	public void select(View v){
		cityList=City.getAll();
		gv.setAdapter(new MyGridViewAdapter(this, cityList, R.layout.cityitem));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	class MyGridViewAdapter extends GridViewAdapter{

		public MyGridViewAdapter(Context _context, List _list, int _itemLayout) {
			super(_context, _list, _itemLayout);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void setConvertView(View itemView, Object item) {
			// TODO Auto-generated method stub
			City city=(City)item;
			TextView id=(TextView)itemView.findViewById(R.id.cityitemcityname);
			TextView json=(TextView)itemView.findViewById(R.id.cityitemtemperature);
			id.setText(String.valueOf(city.getId()));
			json.setText(city.getJson());
		}

		
	}

}
