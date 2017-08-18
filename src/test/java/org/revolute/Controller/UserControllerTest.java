package org.revolute.Controller;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import static org.revolute.util.JsonUtil.isJSONValid;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.revolute.service.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserControllerTest {

	String URL ;
	Inet4Address IP_ADDRESS;
	UserService userService;
	
	@Before
	public void beforeTest() {
		
		userService = new UserService();
		
		try {
			IP_ADDRESS = (Inet4Address) Inet4Address.getLocalHost();
			URL = "http://" + IP_ADDRESS.getHostAddress() + ":4567";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAllUsers_ExpectedListOfUsersInJson() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet( URL + "/users" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		assertTrue(isJSONValid(responseBody));
	}
	
	@Test
	public void getUserBiniam_expectedBiniamUserInJson() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet( URL + "/user/biniamId" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();

		assertEquals("biniam",jsonObject.get("Name").getAsString());
	}
	
	
	
	@Test
	public void postUser_ExpectedStatusCode200() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpPost( URL + "/user?name=elsa&address=London" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}

	@Test
	public void updateUser_ExpectedStatusCode200() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpPut( URL + "/user?id=danielId&name=daniel&address=London" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
	}
	
}
