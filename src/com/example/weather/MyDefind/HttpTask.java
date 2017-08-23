package com.example.weather.MyDefind;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
//使用时只需要重写success()和faild()这两个函数即可
public abstract class HttpTask extends AsyncTask<String, String, Boolean> implements SuccessFaild{

	private String response;
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		//第一步：创建HttpClient对象
        HttpClient httpCient = new DefaultHttpClient();
        //第二步：创建代表请求的对象,参数是访问的服务器地址
        HttpGet httpGet = new HttpGet(params[0]);
        try {
            //第三步：执行请求，获取服务器发还的相应对象
            HttpResponse httpResponse = httpCient.execute(httpGet);
            //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //第五步：从相应对象当中取出数据，放到entity当中
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity,"utf-8");//将entity当中的数据转换为字符串
                return true;
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return false;
	
	}
	@Override
	protected void onPostExecute(final Boolean success) {
		if(success){ //请求成功，response是收到的json串
			success();
		}
		else{//请求失败
			faild();
		}
	}

	@Override
	protected void onCancelled() {
		//task = null;
	}
	
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public void faild(){
		
	}
}

interface SuccessFaild{
	void success();
	void faild();
}