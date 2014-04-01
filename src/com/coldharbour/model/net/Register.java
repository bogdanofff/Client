package com.coldharbour.model.net;


public class Register {
	String login;
	String password;
	
	public Register() {
		super();
	}
	
	public boolean reg(Connection connect) {
		connect.send("registerStart");
		connect.send(login);
		connect.send(password);
		String msg = connect.read();
		
		if (msg.equals("OK")){
			return true;
		} else
			return false;
	}
	
	public boolean validate(String login, String pwd, String rPwd) {
		boolean state = true;
		if (login == null | pwd == null | rPwd == null) {
			System.out.println("����� ��� ������ null");
			return state = false;
		}
		
		if (login.equals("")) {
			System.out.println("����� �� ����� ���� ������");
			return state = false;
		}
		
		if (checkForbiddenChar(login)) {
			System.out.println("����� ���������� ����������� �������");
			return state = false;
		}
		
		
		if (pwd.equals("")) {
			System.out.println("������ �� ����� ���� ������");
			return state = false;
		}
		
		if (checkForbiddenChar(pwd)) {
			System.out.println("������ ��������� ����������� �������");
			return state = false;
		}
		
		if (!pwd.equals(rPwd)) {
			System.out.println("������ �� ���������");
			return state = false;
		}
		
		if (state) {
			this.login = login;
			this.password = pwd;
		}
		
		return state;
	}
	
	private boolean checkForbiddenChar(String msg) {
		//TODO ����������� �������� �� ���� �������
		return false;
	}

}
