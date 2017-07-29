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
	
	//initializing the Response and JSONArray Static variables so that we can reuse it.
	
	static Response resp;
	static JSONArray jsonResponse;
	
	@BeforeTest
	public static void setup(){
		
		//Setting up the API and storing it's Response in resp declaired variable in desired format(JSON). 
		
		resp = get("https://restcountries.eu/rest/v2/currency/inr"); //make get request to fetch Currency's API details
		try {
			jsonResponse = new JSONArray(resp.asString());
		} catch (JSONException e) {
			
			e.printStackTrace(); //Fetching response in JSON format
		} //Such that it should not through any exceptional error.  
		
	}
	
	/*All these mentioned annotation (@Test) are test cases to fetch the required data and assert (Validate) the same.
	 So that it should show the proper result what we wanted.
	 In this demo, I have extracted JSON's node[1] value and validate the same. */
	
	@Test (priority=1) 
	public void getRequestFindName() throws JSONException {
		
		String name = jsonResponse.getJSONObject(1).getString("name"); //Fetching the Country Name.
		System.out.println("Country's Name is: " + name);
		Assert.assertEquals(name, "India");
		
	}
	
	@Test (priority=2)
	public void getRequestFindCapital() throws JSONException {
		
		String capital = jsonResponse.getJSONObject(1).getString("capital"); //Fetching the Country's Capital.
		System.out.println("It's Capital: " + capital);
		Assert.assertEquals(capital, "New Delhi");
		
	}
	
			
	@Test (priority=3)
	public void getRequestFindRegion () throws JSONException{
		
		String region = jsonResponse.getJSONObject(1).getString("region"); //Fetching the Continent of Country.
		System.out.println("Belongs to continents of: " + region);
		Assert.assertEquals(region, "Asia");
		
	}
	
	@Test (priority=4)
	public void getRequestFindCurrencies () throws JSONException{
		
		JsonPath jsonPath = new JsonPath(resp.asString());
		Object currencies = jsonPath.get("currencies[1].name"); //Giving the JSON path to the get the curruncie's data.
		System.out.println("Currencies which in Use : " + currencies + " (INR)"); //Fetching currencie's data used in the Country.
		Assert.assertEquals(currencies.toString(), "[Indian rupee]");
		
	}
	
	@Test (priority=5)
	public void getRequestFindPopulation () throws JSONException{
		
		String population = jsonResponse.getJSONObject(1).getString("population"); //Fetching Country's Population.
		System.out.println("and Having total Population of : " + population);
		Assert.assertEquals(population, "1295210000");
		System.out.println("");
	}
	
	
}