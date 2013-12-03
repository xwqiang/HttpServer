package com.server.connection.request;



/**
 * StandardHttpMethodRequest类来处理请求
 * @author xwqiang
 *
 */
public class HttpRequestProxy extends IRequestService{
	private IRequestService _service;
    private ActiveRequest _active_object;
    public HttpRequestProxy(IRequestService _service) {
        this._service = _service;
        _active_object = new ActiveRequest();
    }
	public void doRequest(){
		MethodRequest mr = new StandardHttpMethodRequest(_service);
		_active_object.enqueue(mr);
	}
}
