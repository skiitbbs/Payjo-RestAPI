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
	
	//Declaring the Static Variable for Response and JSONArray to reuse in code. 
	static Response resp;
	static JSONArray jsonResponse;
	
	@BeforeTest
	public static void setup(){
		
		//This function is going to run before test starts and setup the API and jsonResponse.
		
		resp = get("https://restcountries.eu/rest/v2/currency/inr"); //make get request to fetch Currency's API details
		try {
			jsonResponse = new JSONArray(resp.asString());
		} catch (JSONException e) {
			
			e.printStackTrace(); //Fetching response in JSON format
		}  
		
	}
	
	/* Below given all annotation (@Test) is actually test cases which are going to run on the given priority basis.
	 * This test functions are going to get the desired value and Compare(Validate) the same.
	 * So that we will be sure that function is getting the correct value using assertion.
	 * For the Demo purpose I have take JSON object[1] value which is contains the Data of Indian currencies. 
	*/
	
	@Test (priority=1)
	public void getRequestFindName() throws JSONException {
		
		String name = jsonResponse.getJSONObject(1).getString("name"); // Going to fetch the Country Name and validate the same.
		System.out.println("Country's Name is: " + name);
		Assert.assertEquals(name, "India");
		
	}
	
	@Test (priority=2)
	public void getRequestFindCapital() throws JSONException {
		
		String capital = jsonResponse.getJSONObject(1).getString("capital"); // Going to fetch the Country's Capital and validate the same.
		System.out.println("It's Capital: " + capital);
		Assert.assertEquals(capital, "New Delhi");
		
	}
	
			
	@Test (priority=3)
	public void getRequestFindRegion () throws JSONException{
		
		String region = jsonResponse.getJSONObject(1).getString("region"); // Going to fetch the Continent of the Country and validate the same.
		System.out.println("Belongs to continents of: " + region);
		Assert.assertEquals(region, "Asia");
		
	}
	
	@Test (priority=4)
	public void getRequestFindCurrencies () throws JSONException{
		
		
		JsonPath jsonPath = new JsonPath(resp.asString());
		Object currencies = jsonPath.get("currencies[1].name");
		System.out.println("Currencies which in Use : " + currencies + " (INR)"); // Going to fetch the Currencies data and validate the same.
		Assert.assertEquals(currencies.toString(), "[Indian rupee]");
		
	}
	
	@Test (priority=5)
	public void getRequestFindPopulation () throws JSONException{
		
		String population = jsonResponse.getJSONObject(1).getString("population"); // Going to fetch Country's population and validate the same.
		System.out.println("and Having total Population of : " + population);
		Assert.assertEquals(population, "1295210000");
		System.out.println("");
	}
	
	
}