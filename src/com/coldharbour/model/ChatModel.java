package com.coldharbour.model;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import org.eclipse.swt.widgets.Display;

import com.coldharbour.controllers.Controller;
import com.coldharbour.model.net.Authentication;
import com.coldharbour.model.net.Connection;
import com.coldharbour.model.net.IncomingMessages;
import com.coldharbour.model.net.Register;
import com.coldharbour.model.observer.Observer;
import com.coldharbour.view.ChatUI;
import com.coldharbour.view.UI;

public class ChatModel implements IChatModel {
	private Controller controller;
	private Connection connect;
	private User user;
	private ChatUI chat;
	private IncomingMessages im;

	public ChatModel(Controller controller) {
		this.controller = controller;
	}

	private void connect() {
		try {
			connect = new Connection();
			connect.open();
		} catch (ConnectException e) {
			controller.showError("Сервер не найден");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (new Authentication(user, connect).authenticate()) {
//			this.user = user;
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					chat = new ChatUI(controller);
//					im = new IncomingMessages(connect, chat);
//					new Thread(im).start();
//					chat.initialize();
//				}
//			}).start();
			// controller.closeAuthUI();
//		} else {
//			controller.showError("Не верный логин или пароль");
//			connect.close();
//		}
	}

	@Override
	public void send(String message) {
		connect.send(message);
	}

	@Override
	public void disconnect() {
		// TODO переписать дисконект, работает неправильно
		connect.send("0xfff");
	}

	@Override
	public void register(String login, String pwd, String rPwd) {
		// TODO Auto-generated method stub
		Register register = new Register();
		if (register.validate(login, pwd, rPwd)) {
			if (connect == null) {
				connect();
			}
			if (register.reg(connect)) {
				System.out.println("Регистрация прошла успешно!");
			}
		}
	}

	@Override
	public void auth(User user) {
		
		if (connect == null) {
			connect();
		}

		if (new Authentication(user, connect).authenticate()) {
			this.user = user;
			new Thread(new Runnable() {
				@Override
				public void run() {
					chat = new ChatUI(controller);
					im = new IncomingMessages(connect, chat);
					new Thread(im).start();
					chat.initialize();
				}
			}).start();
		} else {
			controller.showError("Не верный логин или пароль");
			connect.close();
		}
	}
}
