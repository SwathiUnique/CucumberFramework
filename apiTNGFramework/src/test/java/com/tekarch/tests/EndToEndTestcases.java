package com.tekarch.tests;

import org.testng.annotations.Test;

import com.baseTekarch.tests.BaseTestMethods;
import com.baseTekarch.tests.ReusableMethods;

import io.restassured.response.Response;

public class EndToEndTestcases extends BaseTestMethods{
	@Test(priority = 1, enabled = true)
	public static void login_TC01(){
		Response response = loginMethod();
		ReusableMethods.verifyStatusCode(response,201);
		ReusableMethods.validateContentType(response,"contentType");
		ReusableMethods.validateLoginSchema(response, "loginSchema.json");
		ReusableMethods.logHeaders(response);	
	}
	@Test(priority = 2, enabled = true)
	public static void create_TC02(){
		Response response = createMethod();
		ReusableMethods.verifyStatusCode(response,201);
		ReusableMethods.validateStatusCode(response, "success");
		ReusableMethods.validateContentType(response,"contentType");	
		ReusableMethods.logHeaders(response);	
	}
	@Test(priority = 3, enabled = true)
	public static void read_TC03(){
		Response response = readMethod();
		ReusableMethods.verifyStatusCode(response,200);
		ReusableMethods.validateContentType(response,"contentType");
		//ReusableMethods.validateReadDataSchema(response, "readSchema.json");//data too huge to convert from json to schema
		ReusableMethods.logHeaders(response);	
	}
	@Test(priority = 4, enabled = true)
	public static void update_TC04(){
		Response response = updateMethod();
		ReusableMethods.verifyStatusCode(response,200);
		ReusableMethods.validateStatusCode(response, "success");
		ReusableMethods.validateContentType(response,"contentType");	
		ReusableMethods.logHeaders(response);
	}
	@Test(priority = 5, enabled = true)
	public static void delete_TC05(){
		Response response = deleteMethod();
		ReusableMethods.verifyStatusCode(response,200);
		ReusableMethods.validateStatusCode(response, "success");
		ReusableMethods.validateContentType(response,"contentType");	
		ReusableMethods.logHeaders(response);
	}
}
