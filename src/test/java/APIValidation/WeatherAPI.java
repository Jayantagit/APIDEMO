package APIValidation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherAPI {

	public static void main(String[] args) {

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
		List<Map<String, Object>> weatherMap = response.jsonPath().getList("weather");
		for (Map<String, Object> m : weatherMap) {
			System.out.println(m);
		}
		String resData = response.getBody().asString();
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(resData, JsonObject.class);
		String humidityData = jsonObject.getAsJsonObject().getAsJsonObject("main").get("humidity").toString();
		System.out.println(humidityData);
		System.out.println(jsonObject.getAsJsonObject().getAsJsonArray("weather").get(0).getAsJsonObject().get("id"));
		System.out.println("=============");
		String idVal=JsonPath.read(resData, "$.weather[?(@.description=='clear sky')].id").toString();
		System.out.println(idVal);
		

	}

}
