package org.revolute.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.revolute.util.JsonUtil.isJSONValid;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AccountControllerTest {
	
	@Test
	public void getAllUsersAccounts_ExpectedListOfUsersInJson() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet( "http://192.168.1.65:4567/accounts" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		assertTrue(isJSONValid(responseBody));
		
	}
	
	@Test
	public void getUserBiniamSavingAccount_expectedBiniamUserInJson() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://192.168.1.65:4567/account/biniamIdSavingAccount" );
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
		
		HttpUriRequest request = new HttpGet("http://192.168.1.65:4567/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=5000" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void Transfer500FromBiniamSavingAccountToDanialSavingAccount_Expected400ActionProhabitedMessage() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://192.168.1.65:4567/account/transfer?senderId=biniamIdSavingAccount&recieverId=danielIdSavingAccount&amount=500" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println(responseBody);
	}
	
	@Test
	public void Transfer200FromBiniamClassicAccountToDanialSavingAccount_ExpectedStatusCode200() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://192.168.1.65:4567/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=200" );
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
	}
	
}
