package org.revolute.controller;

import org.revolute.service.UserService;


import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {
	
	
	public UserController(final UserService userService) {
		     
		    get("/users", new Route() {
		
		      @Override
		      public Object handle(Request request, Response response) {
		        System.out.println(request.body());
		    	  	return userService.getAllUsers();
		      }
		      
		    });
		    
		    //TODO : to be implemented
		    get("/user", new Route() {
				
			      @Override
			      public Object handle(Request request, Response response) {
			    	  	return null;
			      }
			      
			});
		    
		  //TODO : to be implemented
		    post("/user", new Route() {
				
			      @Override
			      public Object handle(Request request, Response response) {
			    	  	return null;
			      }
			      
			});
		    
		  //TODO : to be implemented
		    put("/user", new Route() {
				
			      @Override
			      public Object handle(Request request, Response response) {
			    	  	return null;
			      }
			      
			});
		    
		    
	}

}
