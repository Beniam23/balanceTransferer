package org.revolute.controller;

import static org.revolute.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;
import org.revolute.domain.Account;
import org.revolute.exception.ActionProhibitedException;
import org.revolute.exception.InsufficientBalanceException;
import org.revolute.service.UserService;

/**
 * @author BINIAM GEBREYESUS	
 * @since 17/08/17 
 * @version 1.0 
 * */
public class AccountController {

	public AccountController(final UserService userService) {
		
		/*
		 * GET request to /accounts responds with a list of all accounts in JSON format 
		 * */
		get("/accounts", (req, res) -> userService.getAllUsersAccounts(), json());
		
		
		/*
		 * GET request to /account/:id responds with account if found 
		 * */
		get("/account/:id", (req,res) -> {
			Account account = userService.getAccount(req.params(":id"));
			if(account!=null)
				return account;
			
			res.status(400);
			res.body("message : Account not found !!");
			return res.body();
		},json());
		
		
		/*
		 * POST request to /account/transfer with amount and reciever's accountId tries to transfer amount and responds with the following :-
		 * 
		 * if the sender account is Saving account :- Action prohibited message 
		 * 
		 * if the sender does have enough balance :- insufficient balance message
		 * 
		 * if successful :- successfully transfered message 
		 * */
		post("/account/transfer", (req , res) -> {
			Account sender = userService.getAccount(req.queryParams("senderId"));
			Account reciever = userService.getAccount(req.queryParams("recieverId"));
			
			try {
				double amount = Double.parseDouble(req.queryParams("amount"));
				sender.transfer(amount,reciever);
				return "message : succesfully transfered !!";
			}catch (NumberFormatException e) {
				res.status(500);
				res.body("message : unexpected datatype found !!");
			}catch (InsufficientBalanceException | ActionProhibitedException e) {
				res.status(400);
				res.body("message : " + e.getMessage() + "");
			}
		
			return res.body();	
		},json());
		
	}
	
}
