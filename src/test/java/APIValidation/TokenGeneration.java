package APIValidation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import Utility.APICalls;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class TokenGeneration {

	public static void main(String[] args) 
	{	
		RestAssured.baseURI="https://reqres.in";
		String path="./src/test/resources/payloads/token.json";		
		Gson gson = null;
		APICalls apiCalls=new APICalls();
		JsonObject jsonObject=apiCalls.readJsonDataFromFileInPath(path);
		Response response=RestAssured.given().log().all().headers(apiCalls.getHeaders())
		.body(jsonObject, ObjectMapperType.GSON)
		.when().post("/api/login")
		.then().assertThat().statusCode(200)
		.extract().response();
		System.out.println(response.jsonPath().getString("token"));

	}

}
