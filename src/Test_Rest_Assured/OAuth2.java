package Test_Rest_Assured;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.*;
public class OAuth2 {

	public static void main(String[] args) {
		
		
		String accessTokenResponse=given().log().all().queryParam("code", "")
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js=new JsonPath(accessTokenResponse);
		String accessToken=js.getString("access_token");
	
		String Response=given().log().all().queryParam("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(Response);

	}

}
