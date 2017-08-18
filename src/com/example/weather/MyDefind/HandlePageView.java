package com.example.weather.MyDefind;

import java.util.List;

import android.view.View;

public  class HandlePageView implements ViewInterface{
	View view;
	CityDate cityDate;
	@Override
	public void sendRequest() {
		// TODO Auto-generated method stub
		new HttpTask() {
			
			@Override
			public void success() {
				// TODO Auto-generated method stub
				cityDate=JsonUtil.getCityDate(super.getResponse());
				setToView();
			}
			
			@Override
			public void faild() {
				// TODO Auto-generated method stub
				
			}
		}.execute("http://www.baidu.com");
	}
	@Override
	public void setToView() {
		// TODO Auto-generated method stub
	}
	
}
interface ViewInterface{
	void sendRequest();//发送请求,得到天气，气温等等
	void setToView();//将数据放到View中
}
