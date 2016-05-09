package application.controller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundAnsware implements Runnable{
	
	String url;
	
	public BackgroundAnsware(String url) {
		this.url = url;
	}
	
	protected String getResult (String sUrl) {
		String result = null;
		try {
			URL url = new URL(sUrl);
			HttpURLConnection urlConnection =  (HttpURLConnection)url.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			result = inputStream.toString();
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
