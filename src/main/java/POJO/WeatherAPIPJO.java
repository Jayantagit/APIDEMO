package POJO;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@NoArgsConstructor
public class WeatherAPIPJO {
	
	private List<Map<String,Object>> weather;
	private Coord coord;
	//private Map<String,Object> main;
	private JsonNode main;


}
