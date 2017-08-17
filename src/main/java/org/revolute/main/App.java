package org.revolute.main;

import org.revolute.controller.UserController;
import org.revolute.service.UserService;

import com.google.gson.Gson;

public class App 
{
	
    public static void main( String[] args )
    {
    	
    		UserService userService = new UserService();
    		
    		userService.addUser(userService.createUser("biniam" , "London"));
    		userService.addUser(userService.createUser("daniel" , "NewYork"));
    		
    		Gson gson = new Gson();
        String json = gson.toJson(userService.getAllUsers());
        System.out.println(json);
    		
    		new UserController(userService);
    	
    		//get("/hello", (req, res) -> "Hello World");
    	
    	 	/*get(new Route("/users/:id") {
         
    	 		@Override
    	 		public Object handle(Request request, Response response) {
    	 			return  "User: username=test, email=test@test.net";
    	 		}     
    	 	});*/
    }
}
