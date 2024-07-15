package Test_Rest_Assured;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import java.io.File;
import io.restassured.path.json.*;

public class Bug_Test {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://manjushanthi353.atlassian.net/";
		String createIssueResponse=given().log().all().header("Content-Type","application/json")
		.header("Authorization","Basic bWFuanVzaGFudGhpMzUzQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjByUUJleHJjTVhud3Ezdm5YaUpVQnFEUVpLdHFPMnlqNnFTWTRsYUJNMlVVMEFTNmZGaWxlQkV2RWZLS2lfeXFTYldmMHlob1ZHNnhzY0Y1U2pfeGpuQ1ZmaHdtWjdmUW10ckliYU5oTmg3QjhoM0VlOEZNWThKSG9UN0ZfVHMzdDZjdVFvMnRSSUpVbl9zODY5N2s0TmUxcmdLNjBBSVMtRWRNSTdpSXF2cHM9Qjk4MTk0NTQ=")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"SCRUM\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Web Links is not working- Automation\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").log().all().post("rest/api/2/issue").then().assertThat()
		.statusCode(201).extract().response().asString();
		
		JsonPath js=new JsonPath(createIssueResponse);
		String issueId=js.getString("id");
		System.out.println(issueId);
		
		//Add Attachment
		given().headers("X-Atlassian-Token","no-check").pathParam("key",issueId)
		.headers("Authorization","Basic bWFuanVzaGFudGhpMzUzQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjByUUJleHJjTVhud3Ezdm5YaUpVQnFEUVpLdHFPMnlqNnFTWTRsYUJNMlVVMEFTNmZGaWxlQkV2RWZLS2lfeXFTYldmMHlob1ZHNnhzY0Y1U2pfeGpuQ1ZmaHdtWjdmUW10ckliYU5oTmg3QjhoM0VlOEZNWThKSG9UN0ZfVHMzdDZjdVFvMnRSSUpVbl9zODY5N2s0TmUxcmdLNjBBSVMtRWRNSTdpSXF2cHM9Qjk4MTk0NTQ=")
		.header("Content-Type","multipart/form-data; boundary=<calculated when request is sent>")
		.multiPart("file",new File("C:/Pictures/Screenshot 2024-07-05 140821.png")).log().all()
		.post("rest/api/2/issue/{key}/attachments")
		.then().assertThat().statusCode(200);
		
		//delete issue
		given().pathParam("key", issueId)
		.header("Authorization","Basic bWFuanVzaGFudGhpMzUzQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjByUUJleHJjTVhud3Ezdm5YaUpVQnFEUVpLdHFPMnlqNnFTWTRsYUJNMlVVMEFTNmZGaWxlQkV2RWZLS2lfeXFTYldmMHlob1ZHNnhzY0Y1U2pfeGpuQ1ZmaHdtWjdmUW10ckliYU5oTmg3QjhoM0VlOEZNWThKSG9UN0ZfVHMzdDZjdVFvMnRSSUpVbl9zODY5N2s0TmUxcmdLNjBBSVMtRWRNSTdpSXF2cHM9Qjk4MTk0NTQ=")
		.delete("rest/api/2/issue/{key}").then().assertThat().statusCode(204);

	}

}
