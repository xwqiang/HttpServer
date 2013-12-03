package com.server.connection.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.server.test.Config;

public class StandardRequestServiceImp extends IRequestService {
	private String path;
//	private OutputStream out;
	public StandardRequestServiceImp(Socket socket) {
		super(socket);
		path = Config.getConfig().getPath();
		try {
			out = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doRequest(){
		File file = new File(path + fileName);
		if (!file.isDirectory()) {
			if (!file.exists()) {
				file = new File(path + Config.getConfig().getDefaultPage());
			}
		} else {
			file = new File(path + Config.getConfig().getDefaultPage());
		}
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = is.read(data)) != -1) {
				out.write(data, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					out.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					is = null;
				}
			}
		}

	}

}
