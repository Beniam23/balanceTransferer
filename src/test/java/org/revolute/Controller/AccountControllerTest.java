package org.revolute.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.revolute.util.JsonUtil.isJSONValid;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.Header;
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
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Before
	public void beforeTest() throws ClientProtocolException, IOException {
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
	
	public LocalDateTime getLastModifiedDate(Header[] headers) {
		
		LocalDateTime lastModified = null;
		for (Header header : headers) {
			if(header.getName().equals("Last-Modified")) {
				lastModified = LocalDateTime.parse(header.getValue(), formatter);
				break;
			}
		}
		
		return lastModified;
	}
	
	@Test
	public void Transfer2000FromBiniamClassicAccountToDanialSavingAccount_Expected400InsufficientBalanceMessage() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest getRequest = new HttpGet( URL + "/account/biniamIdSavingAccount" );
		HttpResponse getResponse = HttpClientBuilder.create().build().execute( getRequest );
		LocalDateTime lastModified = getLastModifiedDate(getResponse.getAllHeaders());
		
		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=2000" );
		request.addHeader("If-Unmodified-Since",  lastModified.format(formatter));
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertTrue(responseBody.contains("InsufficientBalanceException"));
	}
	
	@Test
	public void MakeTwoTransferRequestOf5FromBiniamClassicAccountTODanialSavingAccount_ExpectedTheSecondRequestToFail400InsufficientBalanceMessage() 
		throws ClientProtocolException, IOException, InterruptedException {
		
		HttpUriRequest getRequest = new HttpGet( URL + "/account/biniamIdSavingAccount" );
		HttpResponse getResponse = HttpClientBuilder.create().build().execute( getRequest );
		LocalDateTime lastModified = getLastModifiedDate(getResponse.getAllHeaders());
		
		
		Runnable t1 = () -> { 	try {
			HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=5" );
			request.addHeader("If-Unmodified-Since",  lastModified.format(formatter));
			HttpResponse response = HttpClientBuilder.create().build().execute( request );
			assertEquals(200,response.getStatusLine().getStatusCode());
			String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
			assertTrue(responseBody.contains("succesfully transfered"));
		} catch (Exception e) {
			e.printStackTrace();
		} };
		
		
	    Runnable t2 = () -> { 	try {
	    		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=2000" );
	    		request.addHeader("If-Unmodified-Since",  lastModified.format(formatter));
			HttpResponse response = HttpClientBuilder.create().build().execute( request );
			assertEquals(400,response.getStatusLine().getStatusCode());
			String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(responseBody);
			
		} catch (Exception e) {
			e.printStackTrace();
		} };
	    
	    new Thread(t1).start();
	    new Thread(t2).start();
		
	}
	 
	@Test
	public void Transfer500FromBiniamSavingAccountToDanialSavingAccount_Expected400ActionProhabitedMessage() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest getRequest = new HttpGet( URL + "/account/biniamIdSavingAccount" );
		HttpResponse getResponse = HttpClientBuilder.create().build().execute( getRequest );
		LocalDateTime lastModified = getLastModifiedDate(getResponse.getAllHeaders());
		
		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdSavingAccount&recieverId=danielIdSavingAccount&amount=500" );
		request.addHeader("If-Unmodified-Since",  lastModified.format(formatter));
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(400,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertTrue(responseBody.contains("ActionProhibitedException"));
	}
	
	@Test
	public void Transfer5FromBiniamClassicAccountToDanialSavingAccount_ExpectedStatusCode200() 
	throws ClientProtocolException, IOException {
		
		HttpUriRequest getRequest = new HttpGet( URL + "/account/biniamIdSavingAccount" );
		HttpResponse getResponse = HttpClientBuilder.create().build().execute( getRequest );
		LocalDateTime lastModified = getLastModifiedDate(getResponse.getAllHeaders());
		
		HttpUriRequest request = new HttpPost( URL + "/account/transfer?senderId=biniamIdClassicAccount&recieverId=danielIdSavingAccount&amount=5" );
		request.addHeader("If-Unmodified-Since",  lastModified.format(formatter));
		HttpResponse response = HttpClientBuilder.create().build().execute( request );
		
		assertEquals(200,response.getStatusLine().getStatusCode());
		String responseBody =  EntityUtils.toString(response.getEntity(), "UTF-8");
		
		assertTrue(responseBody.contains("succesfully transfered"));
	}
	
}
