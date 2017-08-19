package com.example.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
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

import com.example.weather.MyDefind.CityDate;
import com.example.weather.MyDefind.CityFactory;
import com.example.weather.MyDefind.MyGridView;

public class MainActivity extends Activity {
	private GridView gv;
	private View view1, view2, view3;
	private List<View> viewList;// view数组
	private ViewPager viewPager; // 对应的viewPager
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gv=(GridView)findViewById(R.id.cityList);
		List cityList=CityFactory.getCityList();
		
		
		
		
		CityDate city=CityFactory.getCity("hello");
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		view1 = new CityViewFactory(city, this).getView();
		view2 = new CityViewFactory(city, this).getView();
		view3 = new CityViewFactory(city, this).getView();
		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
