package APIValidation;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleAPIJackson {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		String path = "./src/test/resources/payloads/reqrescreateuser.json";
		String responseBody = "";
		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new Gson();
		String payLoad = mapper.writeValueAsString(new File(path));
		RequestSpecification request = RestAssured.given().log().all().body(payLoad);
		Response response = request.log().all().post("https://reqres.in/api/users");
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		if (statusCode == 201) {
			responseBody = response.getBody().asString();
			System.out.println(responseBody);
		} else {
			System.out.println("No response..");
		}
		JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
		JsonElement jsonElement = gson.toJsonTree(jsonObject);

		System.out.println("ID from GSON is : " + jsonElement.getAsJsonObject().get("id").toString());
		JsonNode node = mapper.readTree(responseBody);
		String idJackson = node.get("id").asText();
		System.out.println("ID from Jackson is : " + idJackson);

	}

}
