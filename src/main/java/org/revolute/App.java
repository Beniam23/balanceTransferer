package org.revolute;

import org.revolute.controller.AccountController;
import org.revolute.controller.UserController;
import org.revolute.domain.AccountType;
import org.revolute.domain.User;
import org.revolute.service.UserService;

/**
 * @author BINIAM GEBREYESUS	
 * @since 16/08/17 
 * @version 1.0 
 * */
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
 
    		new UserController(userService);
    		new AccountController(userService);
    	
    }
    
   
}
