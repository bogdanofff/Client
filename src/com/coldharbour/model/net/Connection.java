package com.coldharbour.model.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class Connection {
	private final int PORT = 8189;
	private final String HOST = "localhost";
	private Socket socket;
	private Scanner in;
	private PrintWriter out;

	public Connection() {

	}

	public Socket open() throws UnknownHostException, IOException {
		socket = new Socket(HOST, PORT);
		in = new Scanner(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		return socket;
	}
	
	public void send(String message) {
		if (out != null) {
			out.println(message);
			out.flush();
		}
	}
	
	public String read() {
		String msg = "";
		if (in != null) {
			msg = in.nextLine();
		}
		return msg;
	}
	
	public void close() {
		if (out != null & in != null) {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
