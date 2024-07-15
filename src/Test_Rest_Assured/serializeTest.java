package Test_Rest_Assured;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import Pojo.AddPlace;
import Pojo.Location;
import java.util.*;


public class serializeTest {

	public static void main(String[] args) {
		
			RestAssured.get("https://rahulshettyacademy.com/");
			
			AddPlace p=new AddPlace();
			p.setAccuracy(50);
			p.setName("Frontline house");
			p.setPhone_number("(+91) 983 893 3937");
			p.setAddress("29, side layout, cohen 09");
			p.setWebsite("http://google.com");
			p.setLanguage("French-IN");
			List<String> myList=new ArrayList<String>();
			myList.add("shoe park");
			myList.add("shop");
			p.setTypes(myList);
			Location l=new Location();
			l.setLat(-38.383494);
			l.setLng(33.427362);
			
			p.setLocation(l);
			
			
		Response res=given().queryParam("key ","qaclick123").body(p)
		.when().get("maps/api/place/add/json").then()
		.assertThat().statusCode(200).extract().response();

	}

}
