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
	GridView gridView;
	GridView gv;

	private View view1, view2, view3;
	private List<View> viewList;// view����
	private ViewPager viewPager; // ��Ӧ��viewPager
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CityDate city=CityFactory.getCity("hello");
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		view1 = new CityViewFactory(city, this).getView();
		view2 = new CityViewFactory(city, this).getView();
		view3 = new CityViewFactory(city, this).getView();
		viewList = new ArrayList<View>();// ��Ҫ��ҳ��ʾ��Viewװ��������
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
		
//		gv = (GridView) this.findViewById(R.id.gridView1);
//		// 1��׼������Դ
//		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
//		for(int i=0;i<7;i++){
//			Map<String,String> map=new HashMap<String,String>();
//			map.put("day","����һ");
//			map.put("weather", "����ת��");
//			map.put("max", "36");
//			map.put("min", "22");
//			list.add(map);
//		}
//		String from[]={"day","weather","max","min"};
//		int[] to={R.id.textView11,R.id.textView22,R.id.textView33,R.id.textView44};
//		// 2���½�������
//		Adapter adapter=new SimpleAdapter(this, list, R.layout.list, from, to);
//		gv.setAdapter((ListAdapter) adapter);
//		// 3������������
//		gridView =(GridView)findViewById(R.id.gridView2);
//		setGridView();
//		
		//findViewById(R.id.bottom).set
		//	handleMid();
	}
	private  void addListener() {
		ScrollView sc=(ScrollView)findViewById(R.id.scrollView1);
		//sc.add
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        float density = dm.density;
	        int gridviewWidth = (int) ((size )* (length ) * density);
	        int itemWidth = (int) (length * density);

	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
	        gridView.setLayoutParams(params); // ����GirdView���ֲ���,���򲼾ֵĹؼ�
	        gridView.setColumnWidth(itemWidth); // �����б����
	        gridView.setHorizontalSpacing(5); // �����б���ˮƽ���
	        gridView.setStretchMode(GridView.NO_STRETCH);
	        gridView.setNumColumns(size); // ����������=�б�������

	        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
	                timeStateList);
	        gridView.setAdapter(adapter);
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