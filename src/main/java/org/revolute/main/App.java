package org.revolute.main;

import org.revolute.controller.AccountController;
import org.revolute.controller.UserController;
import org.revolute.domain.AccountType;
import org.revolute.domain.User;
import org.revolute.service.UserService;

public class App 
{
	
    public static void main( String[] args )
    {
    	
    		UserService userService = new UserService();
    		
    		User biniam = userService.createUser("biniam" , "London");
    		biniam.addAccount(AccountType.SAVING,100000.00);
    		biniam.addAccount(AccountType.CLASSIC,1000.00);
    		userService.addUser(biniam);
    		
    		User poorDaniel = userService.createUser("daniel" , "NewYork");
    		poorDaniel.addAccount(AccountType.SAVING,0.00);
    		poorDaniel.addAccount(AccountType.CLASSIC,100.00);
    		userService.addUser(poorDaniel);
 
    		
    		/*Gson gson = new Gson();
        String json = gson.toJson(userService.getAllUsers());
        System.out.println(json);*/
    		
    		new UserController(userService);
    		new AccountController(userService);
    	
    		//get("/hello", (req, res) -> "Hello World");
    	
    	 	/*get(new Route("/users/:id") {
         
    	 		@Override
    	 		public Object handle(Request request, Response response) {
    	 			return  "User: username=test, email=test@test.net";
    	 		}     
    	 	});*/
    }
}
