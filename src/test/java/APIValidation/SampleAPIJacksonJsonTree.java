package APIValidation;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleAPIJacksonJsonTree {
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		String path="./src/test/resources/payloads/reqrescreateuser.json";
		String responseBody="";
		ObjectMapper mapper=new ObjectMapper();
		String payLoad=mapper.writeValueAsString(new File(path));
		RequestSpecification request=RestAssured.given().log().all()
				.body(payLoad);
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
		Map<Object,Object> jsonMap=mapper.readValue(responseBody, new TypeReference<Map<Object,Object>>(){ });
		System.out.println(" ID Created===>"+jsonMap.get("id").toString());

	}

}
