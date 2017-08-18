package org.revolute.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.revolute.util.JsonUtil.isJSONValid;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AccountControllerTest {
	
	String URL ;
	Inet4Address IP_ADDRESS;
	
	@Before
	public void beforeTest() {
		try {
			IP_ADDRESS = (Inet4Address) Inet4Address.getLocalHost();
			URL = "http://" + IP_ADDRESS.getHostAddress() + ":4567";
		} catch (UnknownHostException e) {
			fail("failed to optain host ip-address");
		}
	} 
	
	@Test
	public void getAllUsersAccounts_ExpectedListOfUsersInJson() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet( URL + "/accounts" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		assertTrue(isJSONValid(responseBody));
		
	}
	
	@Test
	public void getUserBiniamSavingAccount_expectedBiniamUserInJson() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet( URL + "/account/biniamIdSavingAccount" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();

		assertEquals("biniamIdSavingAccount",jsonObject.get("AccountId").getAsString());
	}
	
	@Test
	public void Transfer5000FromBiniamClassicAccountToDanialSavingAccount_Expected400InsufficientBalanceMessage() 
	throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=2000" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertTrue(responseBody.contains("InsufficientBalanceException"));
	}
	
	@Test
	public void Transfer500FromBiniamSavingAccountToDanialSavingAccount_Expected400ActionProhabitedMessage() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdSavingAccount&recieverId=danielIdSavingAccount&amount=500" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertTrue(responseBody.contains("ActionProhibitedException"));
	}
	
	@Test
	public void Transfer200FromBiniamClassicAccountToDanialSavingAccount_ExpectedStatusCode200() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=5" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertTrue(responseBody.contains("succesfully transfered"));
	}
	
}
