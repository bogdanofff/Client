package com.coldharbour.controllers;

public interface IController {
	
	public void login();
	
//	public void openChat();
	
	public void send(String mesasge);
	
	public void disconnect();
	
	public void register(String login, String pwd, String rPwd);
	
	public void forgotten();
	
	public void openRegUI();


}
