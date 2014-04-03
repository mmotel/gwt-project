package com.geekblog.page.server;

import java.util.ArrayList;
import java.util.List;

import com.geekblog.page.client.LoginService;
import com.geekblog.page.shared.models.BlogUser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	
	private List<BlogUser> usersDbStub = new ArrayList<BlogUser>();

	public LoginServiceImpl(){
			usersDbStub.add(new BlogUser("mateusz", "superhaslo123"));
			System.out.println(usersDbStub.size());
	}
	
	private static final long serialVersionUID = 1L;

	public Boolean checkUser(String login, String passwd) {

		
		for(BlogUser bu : usersDbStub){
			if ( bu.getLogin().compareTo(login) == 0 && bu.getPasswd().compareTo(passwd) == 0){
				return true;
			}
		}
		
		return false;
	}
	
	

}
