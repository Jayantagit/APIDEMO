package APIValidation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import POJO.WeatherAPIPJO;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Jackson {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		String appKey = "99eac3f03c2f6e9486ce0e6643201e81";
		RestAssured.baseURI = "https://api.openweathermap.org";
		RequestSpecification requesSpec = RestAssured.given().log().all();
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("lat", "44.3");
		queryParams.put("lon", "10.99");
		queryParams.put("appid", appKey);
		requesSpec.queryParams(queryParams);
		requesSpec.header(new Header("Content-Type", "application/json"));
		Response response = requesSpec.when().get("/data/2.5/weather");
		System.out.println(response.getBody().prettyPrint());
		String resBody = response.getBody().asString();
		WeatherAPIPJO weatherRes = response.as(WeatherAPIPJO.class);
		String id = weatherRes.getWeather().get(0).get("id").toString();
		System.out.println("===========================");
		System.out.println(" ID is " + id);
		double lat = weatherRes.getCoord().getLat();
		System.out.println("Latititidue " + lat);
		double temp = weatherRes.getMain().get("temp").asDouble();
		System.out.println("Temperature is: " + temp);

		System.out.println("===========================");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(resBody);
		System.out.println("Cloud ==>all value :" + jsonNode.get("clouds").get("all"));

		System.out.println("===========================");
		TypeReference<Map<String, Object>> ref = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> weatherResVal = mapper.readValue(response.getBody().asString(), ref);
		System.out.println("Weather Details is: " + weatherResVal );

	}

}
