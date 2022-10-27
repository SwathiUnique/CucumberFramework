package com.baseTekarch.tests;

import org.hamcrest.Matchers;

import com.test.Utilities.projectUtilities;
import com.test.pojos.CreatePojo;
import com.test.pojos.DeletePojo;
import com.test.pojos.LoginPojo;
import com.test.pojos.LoginResponsePojo;
import com.test.pojos.ReadPojo;
import com.test.pojos.UpdatePojo;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ReusableMethods {
	public static String url;
	public static String username;
	public static String password;
	public static projectUtilities fileObject = new projectUtilities();

	public static String getUrl() {
		url = fileObject.getPropertyValue("urikey","loginCredentials"); 
		return url;
	}
	public static void getloginData(){
		username = fileObject.getPropertyValue("validUsernamekey","loginCredentials");
		password =fileObject.getPropertyValue("validPasswordkey","loginCredentials");	
	}
	public static LoginPojo setLoginData() {
		LoginPojo loginDetails = new LoginPojo();
		getloginData();
		loginDetails.setUsername(username);
		loginDetails.setPassword(password);
		return loginDetails;
	}
	public static LoginResponsePojo getLoginData() {
		LoginResponsePojo resLogin =  new LoginResponsePojo();
		return resLogin;
	}
	public static CreatePojo setCreateData() {
		String accNo = fileObject.getPropertyValue("accountno","createDetails");
		String depNo = fileObject.getPropertyValue("departmentno","createDetails");
		String sal = fileObject.getPropertyValue("salary","createDetails");
		String pCode = fileObject.getPropertyValue("pincode","createDetails");
		CreatePojo createDetails = new CreatePojo();
		createDetails.setAccountno(accNo);
		createDetails.setDepartmentno(depNo);
		createDetails.setSalary(sal);
		createDetails.setPincode(pCode);
		return createDetails;
	}
	public static void getReadData(Response response) {
		String accNo = fileObject.getPropertyValue("accountno","createDetails");
		ReadPojo[] readDetails = response.as(ReadPojo[].class); 
		for(int i=0;i<readDetails.length;i++) {
			if((readDetails[i].getAccountno()).equalsIgnoreCase(accNo)) {
				fileObject.setProperty("departmentno", "1","updateDetails");
				fileObject.setProperty("userid",readDetails[i].getUserid(),"updateDetails");
				fileObject.setProperty("id",readDetails[i].getId(),"updateDetails");
				break;
			}
		}
	}
	public static UpdatePojo updateData() {
		String depNo = fileObject.getPropertyValue("departmentno","updateDetails");

		String id = fileObject.getPropertyValue("id","updateDetails");
		String userid = fileObject.getPropertyValue("userid","updateDetails");
		UpdatePojo updateData = new UpdatePojo();
		updateData.setDepartmentno(depNo);

		updateData.setId(id);
		updateData.setUserid(userid);
		return updateData ;
	}
	public static DeletePojo deleteData() {
		String id = fileObject.getPropertyValue("id","updateDetails");
		String userid = fileObject.getPropertyValue("userid","updateDetails");
		DeletePojo deleteDetails = new DeletePojo();
		deleteDetails.setId(id);
		deleteDetails.setUserid(userid);
		return deleteDetails ;
	}
	public static void verifyStatusCode(Response response, int statusCode) {
		response.then().statusCode(statusCode);
	}
	public static void validateStatusCode(Response response, String status) {
		response.then().body("status", Matchers.equalTo(status));
	}
	public static void validateContentType(Response response,String contentType) {
		response.then().contentType(ContentType.JSON);
	}
	public static void validateLoginSchema(Response response, String schemaFilename) {
		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilename));
	}
	public static void validateReadDataSchema(Response response,String schemaFilename) {
		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilename));
	}
	public static void logHeaders(Response response) {
		response.then().log().headers();
	}
}
