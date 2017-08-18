package org.revolute.util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

/**
 * @author BINIAM GEBREYESUS	
 * @since 17/08/17 
 * @version 1.0 
 * */
public class JsonUtil {
	 
	  public static String toJson(Object object) {
	    return new Gson().toJson(object);
	  }
	 
	  public static ResponseTransformer json() {
	    return JsonUtil::toJson;
	  }
	  
	  public static boolean isJSONValid(String jsonInString) {
	      try {
	          new Gson().fromJson(jsonInString, Object.class);
	          return true;
	      } catch(com.google.gson.JsonSyntaxException ex) { 
	          return false;
	      }
	  }
	  
}
