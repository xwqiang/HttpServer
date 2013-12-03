package com.server.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {  
    public static void main(String[] args) {  
        try {  
            ServerSocket server = new ServerSocket(8888);  
            System.out.println("服务启动，端口：8888");
            while (true) {  
                Socket socket = server.accept();  
                HttpSession session = new HttpSession(socket);  
                new Thread(session).start();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  