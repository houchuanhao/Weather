package com.example.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.weather.MyDefind.MyGridView;
import com.example.weather.MyDefind.Utils;
import com.example.weather.MyDefind.city.City;
import com.example.weather.MyDefind.city.CityDao;
import com.example.weather.MyDefind.city.CityFactory;
public class MainActivity extends Activity implements ActInterface{
	private List<City> cityList=null;
	private List<View> viewList;
	private ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CityDao.setContext(this);
		City city;
		city=new City();
		city.analysis();
		//cityList=CityFactory.getCityList();
		setView(cityList);;
		updateView();
	}
	public void hideTop(View view){
//		Log.i("点击事件", "执行了hideTop(View view),点击了mainActivity中的按钮");
//		View v=(View)findViewById(R.id.topview);
//		v.setVisibility(View.VISIBLE);
		Intent intent = new Intent(this, CityListActivity.class); 
	     this.startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void setView(List _cityList) {  //与数据库中的数据一致
		// TODO Auto-generated method stub
		if(_cityList==null){
			_cityList=City.getAll();
			this.cityList=_cityList;
		}
		if(_cityList==null||cityList.size()==0){
			Log.w("数据库没有值","MainActivity.setView()");
			return;
		}
		viewList=CityViewFactory.getViewList(_cityList, this);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		PagerAdapter pagerAdapter = new PagerAdapter(){
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return viewList.size();
			}
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(viewList.get(position));
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(viewList.get(position));
				return viewList.get(position);
			}
		};
		viewPager.setAdapter(pagerAdapter);
	}
	@Override
	public void updateView() { //重新请求数据，并更新数据库，也更新View
		// TODO Auto-generated method stub
		setView(cityList);
	}



}
