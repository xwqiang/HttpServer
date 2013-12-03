package com.server.connection.request;

/**
 * MethodRequest的基本实现，调用service来处理请求
 * @author Administrator
 *
 */
public class StandardHttpMethodRequest implements MethodRequest{
	private IRequestService _service;
	public StandardHttpMethodRequest(IRequestService _service) {
        this._service = _service;
    }
	public void call() {
		this._service.doRequest();
	}
}
