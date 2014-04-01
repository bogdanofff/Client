package com.coldharbour.model;

import com.coldharbour.view.UI;

public interface IChatModel {
	
	public void send(String message);
	
//	public void connect(User user);
	
	public void auth(User user);
	
	public void disconnect();
	
//	public void setUI(UI ui);
	
	public void register(String login, String pwd, String rPwd);
	
//	public void forgotten();
	
}
