package com.example.weather;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.weather.MyDefind.GridViewAdapter;
import com.example.weather.MyDefind.HttpTask;
import com.example.weather.MyDefind.MyGridView;
import com.example.weather.MyDefind.Utils;
import com.example.weather.MyDefind.city.City;
import com.example.weather.MyDefind.city.CityDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class AddcityActivity extends Activity {
	boolean success=false;
	TextView tv;
	GridView gv;
	EditText edit;
	List cityList=new ArrayList();
	private  City city;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcity);
		CityDao.setContext(this);
		getData();
		tv=(TextView)findViewById(R.id.addcity_textview);
		edit=(EditText)findViewById(R.id.addcity_text);
		edit.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				setGv(edit.getText().toString());
				tv.setText(edit.getText().toString());
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
	//	updateGv(cityList);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addcity, menu);
		return true;
	}
	private void updateGv(List _cityList){
		cityList=_cityList;
	}
	private void setGv(final String cityName){
		gv=(GridView)findViewById(R.id.addcity_gv);
		List childList = new ArrayList();
		if(success==false){
			childList.add(new City());
		}
		else{
			childList=Utils.getLikeCity(cityName, cityList); //cityList可能只有一个yuan
		}
			gv.setAdapter(new GridViewAdapter(getApplicationContext(), childList, R.layout.addcity_item) {
				
				@Override
				public void setConvertView(View itemView, Object item) {
					// TODO Auto-generated method stub
					
					Button cityButton=(Button)itemView.findViewById(R.id.addcity_item_button);
					if(success==false)
						cityButton.setText("正在查询城市");
					else{
						  city=(City)item;
						cityButton.setText(city.getCityName());
						cityButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg0) {
								Intent intent=new Intent();
								intent.putExtra("citynumber", city.getCityNumber());
								intent.putExtra("cityname", city.getCityName());
								setResult(1, intent);
								//startActivity(intent);
								finish();
							}
						});
					}
				}
			});
	}

	private void getData(){
		new HttpTask() {
			@Override
			public void success() {
				// TODO Auto-generated method stub
				cityList=new ArrayList();
				String json=super.getResponse();
				try {
					JSONObject date=new JSONObject(json);
					JSONArray provinceList=date.getJSONArray("list");//34个省
					for(int i=0;i<provinceList.length();i++){
						JSONArray _cityList=provinceList.getJSONObject(i).getJSONArray("list");//济南，青岛，烟台
						String str=_cityList.toString();
						for(int j=0;j<_cityList.length();j++){
								JSONObject downtown=_cityList.getJSONObject(j);
								City city=new City();
								city.setCityNumber(downtown.getString("city_id"));
								city.setCityName(downtown.getString("name"));
								cityList.add(city);
						}
					}
					success=true;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void faild() {
				// TODO Auto-generated method stub
				
			}
		}.execute("http://api.yytianqi.com/citylist/id/1");
	}//
}
