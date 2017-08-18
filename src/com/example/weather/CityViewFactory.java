package com.example.weather;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.weather.MainActivity.GridViewAdapter;
import com.example.weather.MainActivity.TimeState;
import com.example.weather.MyDefind.CityDate;

public class CityViewFactory {
	//����ȱ��������
	Activity activity;
	CityDate cityDate;
	View view;
	TextView cityName;
	TextView currentWeather;
	TextView currentTemperature;
	TextView weekday;//�������ڼ�
	TextView todayMax;  //����¶�
	TextView todayMin;//�����
	HorizontalScrollView hsv;
	GridView gv24;//24Сʱ
	GridView gv7;//һ��
	CityViewFactory(CityDate cityDate,Activity activity){
		
		this.cityDate=cityDate;
		this.activity=activity;
		
		view= activity.getLayoutInflater().inflate(R.layout.weatherlayout, null);
		 //setContent();
	}
	public View getView(){
		 int a=3;
		 cityName=(TextView)view.findViewById(R.id.cityName);

		
		 currentWeather=(TextView)view.findViewById(R.id.currentWeather);
		 currentTemperature=(TextView)view.findViewById(R.id.currentTemperature);
		 weekday=(TextView)view.findViewById(R.id.weekday);//�������ڼ�
		 todayMax=(TextView)view.findViewById(R.id.todayMax);  //����¶�
		 todayMin=(TextView)view.findViewById(R.id.todayMin);//�����
		 hsv=(HorizontalScrollView)view.findViewById(R.id.hsv);
		 gv24=(GridView)view.findViewById(R.id.gv24);
		 gv7=(GridView)view.findViewById(R.id.gv7);
		 
		 setContent();
		 return view;
	}
	public void setContent(){
		
		cityName.setText(cityDate.getCityName());
		currentWeather.setText(cityDate.getCurrentWeather());
		currentTemperature.setText(cityDate.getCurrentTemperature()+"��");
		todayMax.setText(cityDate.getTodayMax()+"��");
		todayMin.setText(cityDate.getTodayMin()+"��");
		setGv7();
		setGv24();
	}
	public void setGv24(){
		
		setGridView();
		
	}
	public void setGv7(){
		String from[]={"day","weather","max","min"};
		int[] to={R.id.textView11,R.id.textView22,R.id.textView33,R.id.textView44};
		// 2���½�������
		Adapter adapter=new SimpleAdapter(activity, cityDate.getWeekState7(), R.layout.list, from, to);
	//��������������bug
		gv7.setAdapter((ListAdapter) adapter);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	 private void setGridView() {
		 	List timeStateList=new ArrayList();
		 	for(int i=0;i<24;i++){
		 		TimeState ts=new TimeState();
		 		ts.setT(i);
		 		ts.setTem(24);
		 		ts.setWeather("����");
		 		timeStateList.add(ts);
		 	}
	        int size = timeStateList.size();
	        int length = 100;
	        DisplayMetrics dm = new DisplayMetrics();
	        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	        float density = dm.density;
	        int gridviewWidth = (int) ((size )* (length ) * density);
	        int itemWidth = (int) (length * density);

	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
	        gv24.setLayoutParams(params); // ����GirdView���ֲ���,���򲼾ֵĹؼ�
	        gv24.setColumnWidth(itemWidth); // �����б����
	        gv24.setHorizontalSpacing(5); // �����б���ˮƽ���
	        gv24.setStretchMode(GridView.NO_STRETCH);
	        gv24.setNumColumns(size); // ����������=�б�����

	        GridViewAdapter adapter = new GridViewAdapter(activity.getApplicationContext(),
	                timeStateList);
	        gv24.setAdapter(adapter);
	    }
	    /**GirdView ����������*/
	 public class GridViewAdapter extends BaseAdapter {
	        Context context;
	        List<TimeState> list;
	        public GridViewAdapter(Context _context, List<TimeState> _list) {
	            this.list = _list;
	            this.context = _context;
	        }

	        @Override
	        public int getCount() {
	            return list.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            return list.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            LayoutInflater layoutInflater = LayoutInflater.from(context);
	            convertView = layoutInflater.inflate(R.layout.midlist, null);
	            TextView time = (TextView) convertView.findViewById(R.id.time);
	            TextView state = (TextView) convertView.findViewById(R.id.state);
	            TextView temperature = (TextView) convertView.findViewById(R.id.temperature);
	            TimeState ts = list.get(position);

	            time.setText(String.valueOf(ts.getT()));
	            state.setText(ts.getWeather());
	            temperature.setText(String.valueOf(ts.getTem()));
	            return convertView;
	        }
	    }

	 public class TimeState{
		 int t;
		 String weather;
		 int tem;
		public int getT() {
			return t;
		}
		public void setT(int t) {
			this.t = t;
		}
		public String getWeather() {
			return weather;
		}
		public void setWeather(String weather) {
			this.weather = weather;
		}
		public int getTem() {
			return tem;
		}
		public void setTem(int tem) {
			this.tem = tem;
		}
		 
	 }
}
