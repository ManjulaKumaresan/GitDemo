package Demo;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.*;


public class SpecBuilderTest {

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
	
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key ","qaclick123")
	.setContentType(ContentType.JSON).build();
	
	
	
RequestSpecification res=given().spec(req).body(p);
ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200)
.expectContentType(ContentType.JSON).build();

Response response=res.when().get("maps/api/place/add/json").then()
.spec(resspec).extract().response();

}

}

