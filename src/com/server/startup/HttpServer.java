package com.server.startup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.server.connection.request.HttpRequestProxy;
import com.server.connection.request.IRequestService;
import com.server.connection.request.StandardRequestServiceImp;

public class HttpServer {  
	Socket socket = null;
	public void startUp(){
		try {
			ServerSocket server = new ServerSocket(8888);  
			System.out.println("服务启动，端口：8888");
			while (true) {  
			    socket = server.accept(); 
			    IRequestService service = new StandardRequestServiceImp(socket);
			    IRequestService proxy = new  HttpRequestProxy(service);
			    proxy.doRequest();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
    public static void main(String[] args) {  
    	HttpServer server = new HttpServer();
    	server.startUp();
    }
}  