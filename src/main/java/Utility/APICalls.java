package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class APICalls {

	private FileReader fileReader = null;
	private Gson gson = null;
	private JsonObject jsonObject = null;

	public JsonObject readJsonDataFromFileInPath(String path) {
		try {
			fileReader = new FileReader(new File(path));
			gson=new Gson();
			jsonObject = gson.fromJson(fileReader, JsonObject.class);
		} catch (FileNotFoundException e) {

		}
		return jsonObject;

	}
	public Headers getHeaders()
	{
		List<Header> headersList=new ArrayList<Header>();
		headersList.add(new Header("Content-Type","application/json"));
		headersList.add(new Header("Accept","application/json"));
		return new Headers(headersList);	
		
	}

}
