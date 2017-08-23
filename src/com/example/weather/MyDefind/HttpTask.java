package com.example.weather.MyDefind;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
//ʹ��ʱֻ��Ҫ��дsuccess()��faild()��������������
public abstract class HttpTask extends AsyncTask<String, String, Boolean> implements SuccessFaild{

	private String response;
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		//��һ��������HttpClient����
        HttpClient httpCient = new DefaultHttpClient();
        //�ڶ�����������������Ķ���,�����Ƿ��ʵķ�������ַ
        HttpGet httpGet = new HttpGet(params[0]);
        try {
            //��������ִ�����󣬻�ȡ��������������Ӧ����
            HttpResponse httpResponse = httpCient.execute(httpGet);
            //���Ĳ��������Ӧ��״̬�Ƿ����������״̬���ֵ��200��ʾ����
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                //���岽������Ӧ������ȡ�����ݣ��ŵ�entity����
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity,"utf-8");//��entity���е�����ת��Ϊ�ַ���
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
		if(success){ //����ɹ���response���յ���json��
			success();
		}
		else{//����ʧ��
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