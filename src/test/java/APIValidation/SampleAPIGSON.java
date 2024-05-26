package APIValidation;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import Utility.APICalls;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleAPIGSON {
	
	public static void main(String[] args) {
		String path="./src/test/resources/payloads/reqrescreateuser.json";
		String responseBody="";
		Gson gson = null;
		APICalls apiCalls=new APICalls();
		JsonObject jsonObject=apiCalls.readJsonDataFromFileInPath(path);
		RequestSpecification request=RestAssured.given().log().all()
				.body(jsonObject, ObjectMapperType.GSON);
		Response response=request.log().all().post("https://reqres.in/api/users");
		int statusCode=response.getStatusCode();
		System.out.println(statusCode);
		if(statusCode==201)
		{
			responseBody=response.getBody().asString();
			System.out.println(responseBody);
		}
		else
		{
			System.out.println("No response..");
		}
		System.out.println(" ID Created===>"+response.jsonPath().get("id").toString());
		Map<Object,Object> responseMap=response.jsonPath().getMap("");
		System.out.println("ID Created==>"+responseMap.get("id"));
		gson=new Gson();
		JsonObject responseObj=gson.fromJson(responseBody, JsonObject.class);
		String id=responseObj.getAsJsonObject().get("id").toString();
		System.out.println("ID Created==>"+id);
		System.out.println("=========Jayway Implementation=======");
		String Id=JsonPath.parse(responseBody).read("$.id").toString();
		System.out.println("ID Created==>"+Id);

	}


}
