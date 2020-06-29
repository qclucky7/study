package com.qingchen.study.vlife;

import org.springframework.http.HttpStatus;

/**
 * 服务器异常类
 * 
 * @author nibaogang
 * 
 */
public class ErrorCodeException extends RuntimeException {
	private static final long serialVersionUID = 1779571984263011273L;
	private ErrorCode code;
	
	public ErrorCodeException(ErrorCode code, Throwable t) {
		super(code.name(), t);
		this.code = code;
	}

	public ErrorCodeException(ErrorCode code) {
		super(code.name());
		this.code = code;
	}
	
	public ErrorCode getCode() {
		return code;
	}

}
