package Test_Rest_Assured;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;

import Pojo.GetCourse;
import Pojo.api;
import Pojo.webAutomation;
import io.restassured.path.json.JsonPath;

public class O_Auth_Client_Credentials {

	public static void main(String[] args) {
		
		String[] webcourse= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String Essential_credential=given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust").when().log().all()
		.post("oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(Essential_credential);
		String Acces_token=js.get("access_token");
		System.out.println(Acces_token);
		
		//get course details
		GetCourse gc =given().log().all().queryParam("access_token", Acces_token).when().log().all()
		.get("oauthapi/getCourseDetails")
		.as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		
		List<api> Apicourses=gc.getCourses().getApi();
		
//		for(int i=0;i<Apicourses.size();i++) {
//			if(Apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Wenservices testing)")){
//				System.out.println(Apicourses.get(i).getPrice());
//				
//			}
		int sum=0;
		for(int i=0;i<Apicourses.size();i++) {
			System.out.println(Apicourses.get(i).getCourseTitle());
			String price=Apicourses.get(i).getPrice();
			int rupees=Integer.valueOf(price);
			sum+=rupees;
					
			
		}
		System.out.println(sum);
		
		ArrayList<String> a=new ArrayList<String>();
		List <webAutomation> webcourse1=gc.getCourses().getWebAutomation();
		
		for(int j=0;j<webcourse1.size();j++) {
			System.out.println(webcourse1.get(j).getCourseTitle());
			a.add(webcourse1.get(j).getCourseTitle());
						
		}
		List<String> expectedList=Arrays.asList(webcourse);
	Assert.assertEquals(expectedList, a);
		
		
		
		
		}
	

	}

