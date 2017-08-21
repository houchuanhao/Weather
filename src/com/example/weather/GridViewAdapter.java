package com.example.weather;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
public abstract class GridViewAdapter extends BaseAdapter implements GridViewAdapterInterface{

    Context context;
    List list;
    int itemLayout;
    public GridViewAdapter(Context _context, List _list,int _itemLayout) {
        this.list = _list;
        this.context = _context;
        this.itemLayout=_itemLayout;
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

    //position是代表第几行，
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(itemLayout, null);
        Object item=list.get(position);
        setConvertView(convertView,item);
        return convertView;
	}
//	@Override
//	public void setConvertView(View convertView,Object item) {
//		// TODO Auto-generated method stub
//		CityDate city=(CityDate)item;
//		TextView cityName=(TextView)convertView.findViewById(R.id.cityitemcityname);
//		TextView temperature=(TextView)convertView.findViewById(R.id.cityitemtemperature);
//		TextView time=(TextView)convertView.findViewById(R.id.cityitemtime);
//		cityName.setText(city.getCityName());
//		temperature.setText(city.getCurrentTemperature());
//		time.setText(Utils.dateToString(city.getUpdatTime()));
//	}
	
}

interface 	GridViewAdapterInterface{
	void setConvertView(View itemView,Object item);
}