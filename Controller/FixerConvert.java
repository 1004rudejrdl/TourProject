package controller;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONObject;

import model.FixerRate;

import java.io.BufferedReader;
public class FixerConvert {
	public static String conversion(String date,String base) {
		BufferedReader br=null;
		String newUrls1="http://data.fixer.io/api/latest?access_key=c24748fb2337d3f8056d18106842c2f4&format=1&symbols=KRW,JPY,CNY,USD,EUR";
		String newUrls=String.format(newUrls1, date,base);
		URL url=null;
		StringBuffer sb=new StringBuffer();
		try {
			url=new URL(newUrls); // http://api.fixer.io
			System.out.println(newUrls);
			br=new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String msg="";
			// http://api.fixer.io/2017-06-09?base=USD를 읽어 들인다.
			while((msg=br.readLine())!=null){
				sb.append(msg);
			}
		} catch (Exception e) {
			
		}
		return sb.toString();
	}
	public static FixerRate  convert2(String  data,String base) throws Exception{
		JSONObject jObject = new JSONObject(data);
		JSONObject rates=jObject.getJSONObject("rates");

		FixerRate fixerrate=new FixerRate();
		fixerrate.date=jObject.getString("date");
		fixerrate.base=base;//jObject.getString("base");
		if(base.equals("USD")){
			double fixbase=rates.getDouble("USD");
			fixerrate.usd=rates.getDouble("USD")/fixbase;
			fixerrate.krw=rates.getDouble("KRW")/fixbase;
			fixerrate.jpy=rates.getDouble("JPY")/fixbase;
			fixerrate.eur=rates.getDouble("EUR")/fixbase;
			fixerrate.cny=rates.getDouble("CNY")/fixbase;
		}else if(base.equals("KRW")){
			double fixbase=rates.getDouble("KRW");
			fixerrate.usd=rates.getDouble("USD")/fixbase;
			fixerrate.krw=rates.getDouble("KRW")/fixbase;
			fixerrate.jpy=rates.getDouble("JPY")/fixbase;
			fixerrate.eur=rates.getDouble("EUR")/fixbase;
			fixerrate.cny=rates.getDouble("CNY")/fixbase;
		}else if(base.equals("JPY")){
			double fixbase=rates.getDouble("JPY");
			fixerrate.usd=rates.getDouble("USD")/fixbase;
			fixerrate.krw=rates.getDouble("KRW")/fixbase;
			fixerrate.jpy=rates.getDouble("JPY")/fixbase;
			fixerrate.eur=rates.getDouble("EUR")/fixbase;
			fixerrate.cny=rates.getDouble("CNY")/fixbase;
		}  else if(base.equals("EUR")){
			double fixbase=rates.getDouble("EUR");
			fixerrate.usd=rates.getDouble("USD")/fixbase;
			fixerrate.krw=rates.getDouble("KRW")/fixbase;
			fixerrate.jpy=rates.getDouble("JPY")/fixbase;
			fixerrate.eur=rates.getDouble("EUR")/fixbase;
			fixerrate.cny=rates.getDouble("CNY")/fixbase;
		} else if(base.equals("CNY")){
			double fixbase=rates.getDouble("CNY");
			fixerrate.usd=rates.getDouble("USD")/fixbase;
			fixerrate.krw=rates.getDouble("KRW")/fixbase;
			fixerrate.jpy=rates.getDouble("JPY")/fixbase;
			fixerrate.eur=rates.getDouble("EUR")/fixbase;
			fixerrate.cny=rates.getDouble("CNY")/fixbase;
		}	
		return fixerrate;
	}
	public static FixerRate  convert(String  date,String base){
		String conversions=conversion(date, base);
		FixerRate fixerRate=null;
		try {
			fixerRate= convert2(conversions, base);
		} catch (Exception e) {
			System.out.println(e);
		}
		return fixerRate;
	}
}
	  
