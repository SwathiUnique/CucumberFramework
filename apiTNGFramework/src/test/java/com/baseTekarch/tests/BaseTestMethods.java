package com.baseTekarch.tests;

import org.testng.annotations.BeforeClass;
import com.test.Utilities.TekExtentReports;
import com.test.constants.EndURIs;
import com.test.pojos.LoginResponsePojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class BaseTestMethods {
    
	public static Response response = null;
	public static TekExtentReports report = null;
	public static String token=null;
	public static String userID = null;
	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI=(ReusableMethods.getUrl());
	}	
	public static Response loginMethod() {
		Response response = RestAssured
				.given()
				.body(ReusableMethods.setLoginData())
				.contentType(ContentType.JSON)
				.when()
			
				.post(EndURIs.LOGIN);
		LoginResponsePojo object = new LoginResponsePojo();
		LoginResponsePojo[] list = response.as(LoginResponsePojo[].class);
		for(int i=0;i<list.length;i++) {
			object.setToken(list[i].getToken());
			object.setUserid(list[i].getUserid());
		}
		token = response.jsonPath().getString("[0].token");
		
		return response;
	}
	public static Response createMethod() {
		Header header = new Header("token",token);
		Response response = RestAssured 
				.given()
				.header(header)
				.contentType(ContentType.JSON)
				.body(ReusableMethods.setCreateData())
				.when()
				.post(EndURIs.ADD_DATA);
		return response;
	}
	public static Response readMethod() {
		Header header = new Header("token",token);
		Response response = RestAssured
				.given()
				.header(header)
				.when()
				.get(EndURIs.READ_DATA);
		ReusableMethods.getReadData(response);
		return response;
		}	
		public static Response updateMethod() {
			Header header = new Header("token",token);
			Response response = RestAssured 
					.given()
					.header(header)
					.contentType(ContentType.JSON)
					.body(ReusableMethods.updateData())
					.when()
					.put(EndURIs.UPDATE_DATA);   
			return response;
			}
		public static Response deleteMethod() {
			Header header = new Header("token",token);
			Response response = RestAssured 
					.given().log().all(true)
					.header(header)
					.contentType(ContentType.JSON)
					.body(ReusableMethods.deleteData())
					.when()
					.delete(EndURIs.DELETE_DATA); 
					return response;
		}

	}
