package org.revolute.controller;

import static org.revolute.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;
import org.revolute.domain.Account;
import org.revolute.exception.ActionProhabitedException;
import org.revolute.exception.InsufficientBalanceException;
import org.revolute.service.UserService;

public class AccountController {

	public AccountController(final UserService userService) {
		
		get("/accounts", (req, res) -> userService.getAllUsersAccounts(), json());
		
		get("/account/:id", (req,res) -> {
			Account account = userService.getAccount(req.params(":id"));
			if(account!=null)
				return account;
			
			res.status(400);
			res.body("Account not found !!");
			return res.body();
		},json());
		
		post("/account/transfer", (req , res) -> {
			Account sender = userService.getAccount(req.queryParams("senderId"));
			Account reciever = userService.getAccount(req.queryParams("recieverId"));
			
			try {
				double amount = Double.parseDouble(req.queryParams("amount"));
				sender.transfer(amount,reciever);
				return "succesfully transfered !!";
			}catch (NumberFormatException e) {
				res.status(500);
				res.body("unexpected datatype found !!");
			}catch (InsufficientBalanceException | ActionProhabitedException e) {
				res.status(400);
				res.body("{ " + "message : " + e.getMessage() + "}");
			}
		
			return res.body();	
		},json());
		
	}
	
}
