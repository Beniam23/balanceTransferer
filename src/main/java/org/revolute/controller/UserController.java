package org.revolute.controller;

import org.revolute.domain.User;
import org.revolute.service.UserService;
import static spark.Spark.*;
import static org.revolute.util.JsonUtil.*;

/**
 * @author BINIAM GEBREYESUS	
 * @since 17/08/17 
 * @version 1.0 
 * */
public class UserController {
	
	public UserController(final UserService userService) {
			 
			/*
			 * GET request to /users responds with a list of all users in JSON format 
			 * */
			get("/users", (req, res) -> userService.getAllUsers(), json());
		    
			
			/*
			 * GET request to /user/:id responds with user if found 
			 * */
			get("/user/:id", (req, res) -> {
				  User user = userService.getUser(req.params(":id"));
				  if (user != null) 
				    return user;
				  
				  res.status(400);
				  res.body("message : user not found");
				  return res.body();
				}, json());
			
			
			/*
			 * POST request to /user with name and amount tries to create user and responds with the following :-
			 * 
			 * if successful :- user id
			 * else :- user could not created message
			 *  
			 * */
		    post("/user", (req, res) -> {
			    	User user = userService.createUser(req.queryParams("name"), req.queryParams("address"));
			    	userService.addUser(user);
			    	if(user !=null)
			    		return user.getId();
			    	
			    	res.status(400);
			    	res.body("message : user could not be created");
			    	return res.body();
		    },json());
		    
		    
		    /*
			 * PUT request to /user with id, name and amount tries to update user and responds with the following :-
			 * 
			 * if successful :- user
			 * else :- user could not updated message
			 *  
			 * */
		    put("/user", (req , res) -> {
		    		User user = userService.updateUser(req.queryParams("id"), req.queryParams("name"), req.queryParams("address"));
		    		if(user != null)
		    			return user;
		    	
		    		res.status(400);
		    		res.body(" message : user details could not be updated");
		    		return res.body();
			}, json());
		    
	}

}
