package com.server.connection.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public abstract class IRequestService {
	protected long LAST_ACCESS_TIME = System.currentTimeMillis();
	protected Socket socket;
	protected String fileName;
	protected OutputStream out;
	private BufferedReader br ;
	public IRequestService(){
	}
	public IRequestService(Socket socket) {
		this.socket = socket;
		process();
	}
	private final void process(){
		try {
			br = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream()));
			String command = null;
			while ((command = br.readLine()) != null) {
				System.out.println("浏览器的指令:" + command);
				if (command.length() < 3) {
					break;
				}
				String result = command.substring(0, 3);
				if (result.equalsIgnoreCase("GET")) {
					int begin = command.indexOf("/") + 1;
					int end = command.lastIndexOf(" ");
					fileName = command.substring(begin, end);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public abstract void doRequest();
	
	@Override
	public void finalize(){
		try {
			super.finalize();
			out.close();
			br.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
