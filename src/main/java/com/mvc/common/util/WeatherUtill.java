package com.mvc.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherUtill {
	
	@Value("${weather.URL}")
	private String URL; 
	
	@Value("${weather.serviceKey}")
	private String SERVICE_KEY;
	
	@Value("${weather.pageNo}")
	private String PAGE_NO;
	
	@Value("${weather.numOfRows}")
	private String NUM_OF_ROWS;
	
	@Value("${weather.nx}")
	private String NX;
	
	@Value("${weather.ny}")
	private String NY;
	
	public String setRequestURL() {
		// 오늘의 날짜 가져오기
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
		
		String today = now.format(todayFormatter);
		String time = now.format(timeFormatter);
		
		// Debugging
		System.out.println("time : " + time);
		System.out.println("time Integer : " + Integer.parseInt(time));
		
		// 만약 아직 오늘 데이터를 가져올 수 없다면 어제 데이터를 가져온다.
		if(Integer.parseInt(time) <= 820) {
			LocalDateTime yesterday = now.minusDays(1);
			today = yesterday.format(todayFormatter);
		}
		
		// Debugging
		System.out.println("today : " + today);
		
		// URL 만들기	
//		StringBuilder urlBuilder = new StringBuilder(URL);
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");

		try {
//		    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICE_KEY);
//		    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(PAGE_NO, "UTF-8")); 
//		    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(NUM_OF_ROWS, "UTF-8")); 
//		    urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); 
//		    urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8"));
//		    urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0800", "UTF-8")); 
//		    urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(NX, "UTF-8")); 
//		    urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(NY, "UTF-8"));

	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=f6Z8eHTrMw4Z%2BW9dB6rvzXP4JYV2mTOfcrHxAldnD0wWfyYTA5NqORX102vcS9S3nUbLRkI%2F0%2BgzcPxfl0O0OQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("7", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0800", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("89", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("90", "UTF-8")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return urlBuilder.toString();
	}
	
	public WeatherCode getTodayWeather() throws IOException {

		URL url = new URL(setRequestURL());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        System.out.println(sb.toString());
        
        // JSON Parsing
        WeatherCode result = null;
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(sb.toString());
            JsonNode itemNode = rootNode.path("response").path("body").path("items").path("item");
            
            for (JsonNode node : itemNode) {
                String fcstValue = node.path("fcstValue").asText();
                System.out.println("fcstValue: " + fcstValue);
                
                result = Arrays.stream(WeatherCode.values())
                			.filter(v -> fcstValue.equals(v.getCode()))
                			.findFirst()
                			.orElse(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        rd.close();
        conn.disconnect();
        
        System.out.println("result : " + result.getCode());
        
        return result;
	} 
	
	// Test
	public static void main(String[] args) throws IOException{
		// 오늘의 날짜 가져오기
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter todayFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
		
		String today = now.format(todayFormatter);
		String time = now.format(timeFormatter);
		
		// Debugging
		System.out.println("time : " + time);
		System.out.println("time Integer : " + Integer.parseInt(time));
		
		// 만약 아직 오늘 데이터를 가져올 수 없다면 어제 데이터를 가져온다.
		if(Integer.parseInt(time) <= 820) {
			LocalDateTime yesterday = now.minusDays(1);
			today = yesterday.format(todayFormatter);
		}
		
		// Debugging
		System.out.println("today : " + today);
		
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=f6Z8eHTrMw4Z%2BW9dB6rvzXP4JYV2mTOfcrHxAldnD0wWfyYTA5NqORX102vcS9S3nUbLRkI%2F0%2BgzcPxfl0O0OQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("7", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0800", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("89", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("90", "UTF-8")); 
			      
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        System.out.println(sb.toString());
        
        // JSON Parsing
        WeatherCode result = null;
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(sb.toString());
            JsonNode itemNode = rootNode.path("response").path("body").path("items").path("item");
            
            for (JsonNode node : itemNode) {
                String fcstValue = node.path("fcstValue").asText();
                System.out.println("fcstValue: " + fcstValue);
                
                result = Arrays.stream(WeatherCode.values())
                			.filter(v -> fcstValue.equals(v.getCode()))
                			.findFirst()
                			.orElse(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        rd.close();
        conn.disconnect();
        
        System.out.println("result : " + result.getCode());
        
        rd.close();
        conn.disconnect();
	}
}
