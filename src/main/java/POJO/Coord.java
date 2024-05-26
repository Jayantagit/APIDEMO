package POJO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@NoArgsConstructor
public class Coord {
	
	private double lon;
	private double lat;

}
