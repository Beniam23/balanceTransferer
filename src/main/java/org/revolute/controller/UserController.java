package org.revolute.controller;

import org.revolute.domain.User;
import org.revolute.service.UserService;
import static spark.Spark.*;
import static org.revolute.util.JsonUtil.*;

public class UserController {
	
	public UserController(final UserService userService) {
			 
			get("/users", (req, res) -> userService.getAllUsers(), json());
		     
			get("/user/:id", (req, res) -> {
				  User user = userService.getUser(req.params(":id"));
				  if (user != null) 
				    return user;
				  
				  res.status(400);
				  res.body("user not found !!");
				  return res.body();
				}, json());
			
			
		    post("/user", (req, res) -> {
			    	User user = userService.createUser(req.queryParams("name"), req.queryParams("address"));
			    	userService.addUser(user);
			    	if(user !=null)
			    		return user.getId();
			    	
			    	res.status(400);
			    	res.body("user could not be created !!");
			    	return res.body();
		    },json());
		    
		    
		    put("/user", (req , res) -> {
		    		User user = userService.updateUser(req.queryParams("id"), req.queryParams("name"), req.queryParams("address"));
		    		if(user != null)
		    			return user;
		    	
		    		res.status(400);
		    		res.body("user details could not be updated !!");
		    		return res.body();
			}, json());
		    
	}

}
