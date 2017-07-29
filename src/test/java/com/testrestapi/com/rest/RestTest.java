package com.testrestapi.com.rest;

import static com.jayway.restassured.RestAssured.get;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestTest {
	
	static Response resp;
	static JSONArray jsonResponse;
	
	@BeforeTest
	public static void setup(){
		
		
		resp = get("https://restcountries.eu/rest/v2/currency/inr"); //make get request to fetch Currency's API details
		try {
			jsonResponse = new JSONArray(resp.asString());
		} catch (JSONException e) {
			
			e.printStackTrace(); //Fetching response in JSON format
		}  
		
	}
	
	@Test (priority=1)
	public void getRequestFindName() throws JSONException {
		
		String name = jsonResponse.getJSONObject(1).getString("name");
		System.out.println("Country's Name is: " + name);
		Assert.assertEquals(name, "India");
		
	}
	
	@Test (priority=2)
	public void getRequestFindCapital() throws JSONException {
		
		String capital = jsonResponse.getJSONObject(1).getString("capital");
		System.out.println("It's Capital: " + capital);
		Assert.assertEquals(capital, "New Delhi");
		
	}
	
			
	@Test (priority=3)
	public void getRequestFindRegion () throws JSONException{
		
		String region = jsonResponse.getJSONObject(1).getString("region"); //Fetching value of capital parameter
		System.out.println("Belongs to continents of: " + region);
		Assert.assertEquals(region, "Asia");
		
	}
	
	@Test (priority=4)
	public void getRequestFindCurrencies () throws JSONException{
		
		/* String currencies = jsonResponse.getJSONObject(1).getString("currencies[1].name"); //Fetching value of currencies parameter
		System.out.println(currencies);
		Assert.assertEquals(currencies, "Indian rupee"); */
		
		JsonPath jsonPath = new JsonPath(resp.asString());
		Object currencies = jsonPath.get("currencies[1].name");
		System.out.println("Currencies which in Use : " + currencies + " (INR)");
		Assert.assertEquals(currencies.toString(), "[Indian rupee]");
		
	}
	
	@Test (priority=5)
	public void getRequestFindPopulation () throws JSONException{
		
		String population = jsonResponse.getJSONObject(1).getString("population"); //Fetching value of capital parameter
		System.out.println("and Having total Population of : " + population);
		Assert.assertEquals(population, "1295210000");
		System.out.println("");
	}
	
	
}